package club.javafamily.nf.sms.tencent.conf;

import club.javafamily.nf.sms.tencent.properties.EmailProperties;
import club.javafamily.nf.sms.tencent.service.EmailNotifyHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jack Li
 * @date 2022/3/3 3:45 下午
 * @description
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(EmailProperties.class)
public class EmailNotifyAutoConfiguration {

   private final EmailProperties properties;

   public EmailNotifyAutoConfiguration(EmailProperties properties) {
      this.properties = properties;
   }

   @Bean
   public EmailNotifyHandler tencentSmsNotifyHandler() {
      return new EmailNotifyHandler(mailSender, properties);
   }
}
