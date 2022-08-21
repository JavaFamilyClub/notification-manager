package club.javafamily.nf.conf;

import club.javafamily.autoconfigre.cache.config.CachePropertiesCustomizer;
import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;
import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.properties.Inhibit;

/**
 * 修改抑制 Cache 的 ttl.
 * 如果 <tt>javafamily-cache</tt> 和 <tt>feishu-notification-spring-boot-starter</tt> 同时配置,
 * 则 <tt>feishu-notification-spring-boot-starter</tt> 生效
 */
public class JavaFamilyCachePropertiesCustomizer implements CachePropertiesCustomizer {

    private final FeiShuProperties properties;

    public JavaFamilyCachePropertiesCustomizer(FeiShuProperties properties) {
        this.properties = properties;
    }

    @Override
    public void customize(JavaFamilyCacheProperties javaFamilyCacheProperties) {
        Inhibit inhibit = properties.getInhibit();

        if(inhibit.getEnabled() != null && inhibit.getEnabled() && inhibit.getTtl() != null) {
            javaFamilyCacheProperties.setTimeToLive(inhibit.getTtl());
        }
    }
}
