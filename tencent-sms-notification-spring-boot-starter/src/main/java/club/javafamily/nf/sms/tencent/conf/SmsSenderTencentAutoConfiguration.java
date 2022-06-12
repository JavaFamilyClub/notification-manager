package club.javafamily.nf.sms.tencent.conf;

import club.javafamily.nf.sms.tencent.properties.SmsTencentProperties;
import club.javafamily.nf.sms.tencent.service.TencentSmsNotifyHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jack Li
 * @date 2022/3/3 3:45 下午
 * @description
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SmsTencentProperties.class)
public class SmsSenderTencentAutoConfiguration {

   private final SmsTencentProperties properties;

   public SmsSenderTencentAutoConfiguration(SmsTencentProperties properties) {
      this.properties = properties;
   }

   @Bean
   public TencentSmsNotifyHandler tencentSmsNotifyHandler() {
      return new TencentSmsNotifyHandler(properties);
   }
}
