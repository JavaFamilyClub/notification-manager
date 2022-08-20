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

   @Bean
   public FeiShuNotifyHandler feiShuNotifyHandler() {
      if(properties.getEnabled() == null || properties.getEnabled()) {
         return new FeiShuNotifyHandler(properties, restTemplate, inhibitPolicy);
      }

      return new NoOpFeiShuNotifyHandler(properties);
   }

}
