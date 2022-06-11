package club.javafamily.nf.conf;

import club.javafamily.nf.properties.DingTalkProperties;
import club.javafamily.nf.service.DingTalkNotifyHandler;
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
@EnableConfigurationProperties(DingTalkProperties.class)
public class DingTalkNotificationAutoConfiguration {

   private final DingTalkProperties properties;
   private final RestTemplate restTemplate;

   public DingTalkNotificationAutoConfiguration(DingTalkProperties properties,
                                                RestTemplate restTemplate)
   {
      this.properties = properties;
      this.restTemplate = restTemplate;
   }

   @Bean
   public DingTalkNotifyHandler feiShuNotifyHandler() {
      return new DingTalkNotifyHandler(properties, restTemplate);
   }

}
