package club.javafamily.nf.sms.ucloud.conf;

import club.javafamily.nf.sms.ucloud.properties.SmsUCloudProperties;
import club.javafamily.nf.sms.ucloud.service.UCloudSmsNotifyHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jack Li
 * @date 2022/3/3 3:45 下午
 * @description
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SmsUCloudProperties.class)
public class SmsSenderUCloudAutoConfiguration {

   private final SmsUCloudProperties properties;

   public SmsSenderUCloudAutoConfiguration(SmsUCloudProperties properties) {
      this.properties = properties;
   }

   @Bean
   public UCloudSmsNotifyHandler uCloudSmsNotifyHandler() {
      return new UCloudSmsNotifyHandler(properties);
   }
}
