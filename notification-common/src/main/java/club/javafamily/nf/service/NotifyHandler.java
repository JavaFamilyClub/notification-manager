package club.javafamily.nf.service;

import club.javafamily.nf.request.NotifyRequest;

/**
 * @author Jack Li
 * @date 2022/6/12 下午6:20
 * @description 通知处理器
 */
public interface NotifyHandler<NR extends NotifyRequest, RESPONSE> {

    /**
     * 通知
     * @param request 请求参数
     */
    RESPONSE notify(NR request) throws Exception;
}
