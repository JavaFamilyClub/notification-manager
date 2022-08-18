package club.javafamily.nf.service.inhibit;

import club.javafamily.nf.request.NotifyRequest;
import club.javafamily.nf.service.InhibitRule;
import org.springframework.util.ObjectUtils;

public class ToStringInhibitRule implements InhibitRule {
    /**
     * 获取抑制唯一标识
     * @param request 通知请求
     * @return inhibit unique identity
     */
    @Override
    public String inhibitIdentity(NotifyRequest request) {
        return ObjectUtils.getDisplayString(request);
    }
}
