package club.javafamily.nf.conf;

import club.javafamily.autoconfigre.cache.service.CacheOperator;
import club.javafamily.nf.service.InhibitPolicy;
import club.javafamily.nf.service.InhibitRule;
import club.javafamily.nf.service.inhibit.policy.DefaultInhibitPolicy;
import club.javafamily.nf.service.inhibit.rule.ToStringInhibitRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "javafamily.notify.qywechat.inhibit", name = "enabled", havingValue = "true")
public class InhibitNotifyConf {

    private final CacheOperator cacheOperator;

    public InhibitNotifyConf(CacheOperator cacheOperator) {
        this.cacheOperator = cacheOperator;
    }

    @Bean
    @ConditionalOnMissingBean
    public InhibitRule inhibitRule() {
        return new ToStringInhibitRule();
    }

    @Bean
    @ConditionalOnMissingBean
    public InhibitPolicy inhibitPolicy(@Autowired(required = false) InhibitRule inhibitRule) {
        return new DefaultInhibitPolicy(inhibitRule, cacheOperator);
    }

}
