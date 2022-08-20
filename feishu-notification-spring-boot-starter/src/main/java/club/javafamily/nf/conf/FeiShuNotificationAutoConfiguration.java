package club.javafamily.nf.conf;

import club.javafamily.autoconfigre.resttemplate.config.RestTemplateAutoConfiguration;
import club.javafamily.autoconfigre.cache.config.JavaFamilyCacheAutoConfiguration;
import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.service.FeiShuNotifyHandler;
import club.javafamily.nf.service.InhibitPolicy;
import club.javafamily.nf.service.NoOpFeiShuNotifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(FeiShuProperties.class)
@AutoConfigureAfter({
     RestTemplateAutoConfiguration.class
})
@AutoConfigureBefore({
     /**
      * 需要修改 Cache ttl, 因此需要在 {@link JavaFamilyCacheAutoConfiguration} 之后
      */
     JavaFamilyCacheAutoConfiguration.class
})
@Import(InhibitNotifyConf.class)
public class FeiShuNotificationAutoConfiguration {

   private final FeiShuProperties properties;
   private final RestTemplate restTemplate;
   private final InhibitPolicy inhibitPolicy;

   public FeiShuNotificationAutoConfiguration(FeiShuProperties feiShuProperties,
                                              RestTemplate restTemplate,
                                              @Autowired(required = false) InhibitPolicy inhibitPolicy)
   {
      this.properties = feiShuProperties;
      this.restTemplate = restTemplate;
      this.inhibitPolicy = inhibitPolicy;
   }

   /**
    * 定义飞书通知处理器
    * @return 如何启用通知, 返回 {@link FeiShuNotifyHandler}
    * 否则返回 {@link NoOpFeiShuNotifyHandler}
    */
   @Bean
   public FeiShuNotifyHandler feiShuNotifyHandler() {
      if(properties.getEnabled() == null || properties.getEnabled()) {
         return new FeiShuNotifyHandler(properties, restTemplate, inhibitPolicy);
      }

      return new NoOpFeiShuNotifyHandler(properties);
   }

   /**
    * 将通知组件的抑制 ttl 生效于 Cache 组件. {@link club.javafamily.autoconfigre.cache.config.CacheCustomizer}
    * @return 用于修改 cache ttl 的 Customizer
    */
   @Bean
   public JavaFamilyCacheCustomizer javaFamilyCacheCustomizer() {
      return new JavaFamilyCacheCustomizer(properties);
   }

}
