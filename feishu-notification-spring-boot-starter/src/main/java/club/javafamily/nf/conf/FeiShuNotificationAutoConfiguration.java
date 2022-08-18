package club.javafamily.nf.conf;

import club.javafamily.autoconfigre.cache.service.CacheOperator;
import club.javafamily.autoconfigre.resttemplate.config.RestTemplateAutoConfiguration;
import club.javafamily.autoconfigre.cache.config.JavaFamilyCacheAutoConfiguration;
import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.service.FeiShuNotifyHandler;
import club.javafamily.nf.service.InhibitRule;
import club.javafamily.nf.service.NoOpFeiShuNotifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
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
     RestTemplateAutoConfiguration.class,
     JavaFamilyCacheAutoConfiguration.class
})
@Import(InhibitNotifyConf.class)
public class FeiShuNotificationAutoConfiguration {

   private final FeiShuProperties properties;
   private final RestTemplate restTemplate;
   private final InhibitRule inhibitRule;
   private final CacheOperator cacheOperator;

   public FeiShuNotificationAutoConfiguration(FeiShuProperties feiShuProperties,
                                              RestTemplate restTemplate,
                                              CacheOperator cacheOperator,
                                              @Autowired(required = false) InhibitRule inhibitRule)
   {
      this.properties = feiShuProperties;
      this.restTemplate = restTemplate;
      this.inhibitRule = inhibitRule;
      this.cacheOperator = cacheOperator;
   }

   @Bean
   public FeiShuNotifyHandler feiShuNotifyHandler() {
      if(properties.getEnabled() == null || properties.getEnabled()) {
         return new FeiShuNotifyHandler(properties, restTemplate, inhibitRule, cacheOperator);
      }

      return new NoOpFeiShuNotifyHandler(properties);
   }

}
