package club.javafamily.nf.conf;

import club.javafamily.nf.service.InhibitRule;
import club.javafamily.nf.service.inhibit.ToStringInhibitRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "javafamily.notify.feishu.inhibit-enabled", havingValue = "true")
public class InhibitNotifyConf {

    @Bean
    @ConditionalOnMissingBean
    public InhibitRule inhibitRule() {
        return new ToStringInhibitRule();
    }

}
