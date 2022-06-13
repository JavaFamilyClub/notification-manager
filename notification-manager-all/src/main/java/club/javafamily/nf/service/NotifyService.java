package club.javafamily.nf.service;

import club.javafamily.nf.enums.NotifySupportTypeEnum;
import club.javafamily.nf.request.NotifyRequest;
import org.springframework.util.CollectionUtils;

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
     * @param request 请求参数
     * @return 通知返回, 由于异步执行, 因此返回顺序与 {@param accepts} 的顺序不一定一致
     */
    public Object notify(NotifySupportTypeEnum accept, NotifyRequest request) {
        return notify(EnumSet.of(accept), request);
    }

    /**
     * 通知
     * @param accepts 接受的通知处理器
     * @param request 请求参数
     * @return 通知返回, 由于异步执行, 因此返回顺序与 {@param accepts} 的顺序不一定一致
     */
    public Object notify(EnumSet<NotifySupportTypeEnum> accepts, NotifyRequest request) {
        List<NotifyHandler> processHandlers = handlers.stream()
                .filter(handler -> handler.isAccept(accepts))
                .collect(Collectors.toList());

        if(CollectionUtils.isEmpty(processHandlers)) {
            throw new UnsupportedOperationException("没有可用的通知处理器!");
        }

        return processHandlers.parallelStream()
                .map(notifyHandler -> {
                    try {
                        return notifyHandler.notify(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return null;
                })
                .collect(Collectors.toList());
    }

}
