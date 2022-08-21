package club.javafamily.nf.service;

import club.javafamily.nf.request.NotifyRequest;

/**
 * 抑制规则
 */
public interface InhibitRule {

    /**
     * 抑制响应
     */
    String INHIBIT_RESPONSE = "Inhibit request";

    /**
     * 获取抑制唯一标识
     * @param request 通知请求
     * @return inhibit unique identity
     */
    String inhibitIdentity(NotifyRequest request);

}
