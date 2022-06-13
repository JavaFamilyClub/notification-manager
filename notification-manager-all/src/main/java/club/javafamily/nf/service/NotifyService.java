package club.javafamily.nf.service;

import club.javafamily.nf.enums.NotifySupportTypeEnum;
import club.javafamily.nf.request.NotifyRequest;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description
 */
public class NotifyService {

    private final List<NotifyHandler> handlers;

    public NotifyService(List<NotifyHandler> handlers) {
        this.handlers = handlers;

        if(CollectionUtils.isEmpty(handlers)) {
            throw new UnsupportedOperationException("没有可用的通知处理器!");
        }
    }

    /**
     * 通知
     * @param accept 接受的通知处理器
     * @param requests 请求参数
     * @return 通知返回
     */
    public Object notify(NotifySupportTypeEnum accept, NotifyRequest...requests) {
        NotifyHandler processHandler = handlers.stream()
                .filter(handler -> handler.isAccept(accept))
                .findFirst()
                .orElse(null);

        if(processHandler == null) {
            throw new UnsupportedOperationException("没有可用的通知处理器!");
        }

        return Arrays.stream(requests)
                .map(request -> {
                    try {
                        return processHandler.notify(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return null;
                })
                .collect(Collectors.toList());
    }

}
