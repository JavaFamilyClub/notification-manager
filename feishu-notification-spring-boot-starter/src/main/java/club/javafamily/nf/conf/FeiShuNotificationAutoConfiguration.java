package club.javafamily.nf.conf;

import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.properties.WebHookProperties;
import club.javafamily.nf.service.FeiShuNotifyHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(FeiShuProperties.class)
public class FeiShuNotificationAutoConfiguration {

   private final FeiShuProperties feiShuProperties;
   private final RestTemplate restTemplate;

   public FeiShuNotificationAutoConfiguration(FeiShuProperties feiShuProperties,
                                              RestTemplate restTemplate)
   {
      this.feiShuProperties = feiShuProperties;
      this.restTemplate = restTemplate;
   }

   @Bean
   public FeiShuNotifyHandler feiShuNotifyHandler() {
      return new FeiShuNotifyHandler(feiShuProperties, restTemplate);
   }

}
