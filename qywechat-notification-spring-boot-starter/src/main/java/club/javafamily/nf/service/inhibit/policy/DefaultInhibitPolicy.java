package club.javafamily.nf.service.inhibit.policy;

import club.javafamily.autoconfigre.cache.service.CacheOperator;
import club.javafamily.nf.po.InhibitCachePo;
import club.javafamily.nf.request.NotifyRequest;
import club.javafamily.nf.service.InhibitPolicy;
import club.javafamily.nf.service.InhibitRule;
import org.springframework.util.StringUtils;

/**
 * 默认的抑制策略
 * 全部组件支持后应该放在 <tt>notification-common</tt> module
 */
public class DefaultInhibitPolicy implements InhibitPolicy {

    private final InhibitRule rule;
    private final CacheOperator cacheOperator;

    public DefaultInhibitPolicy(InhibitRule rule, CacheOperator cacheOperator) {
        this.rule = rule;
        this.cacheOperator = cacheOperator;
    }

    /**
     * 是否抑制
     *
     * @param request
     * @return <code>true</code> 代表当前通知被抑制
     */
    @Override
    public boolean isInhibited(NotifyRequest request) {
        String identity = null;

        if(rule != null) {
            identity = rule.inhibitIdentity(request);

            return StringUtils.hasText(identity) && cacheOperator.getValue(identity) != null;
        }

        return false;
    }

    /**
     * 抑制完成
     *
     * @param request 请求
     */
    @Override
    public void completeInhibited(NotifyRequest request, String response) {
        String identity = null;

        if (rule != null) {
            identity = rule.inhibitIdentity(request);

            if (identity != null) {
                cacheOperator.setValue(identity, InhibitCachePo.builder()
                        .request(request)
                        .response(response)
                        .build());
            }
        }
    }
}
