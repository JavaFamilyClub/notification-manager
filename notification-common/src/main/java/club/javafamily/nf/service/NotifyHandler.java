package club.javafamily.nf.service;

import club.javafamily.nf.request.NotifyRequest;

public interface NotifyHandler<NR extends NotifyRequest> {

    /**
     * 通知
     * @param request 请求参数
     */
    String notify(NR request);
}
