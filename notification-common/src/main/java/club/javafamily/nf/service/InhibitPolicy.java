package club.javafamily.nf.service;

import club.javafamily.nf.request.NotifyRequest;

/**
 * 抑制策略
 */
public interface InhibitPolicy {

    /**
     * 是否抑制
     * @return <code>true</code> 代表当前通知被抑制
     */
    boolean isInhibited(NotifyRequest request);

    /**
     * 抑制完成
     * @param request 请求
     */
    void completeInhibited(NotifyRequest request, String response);

}
