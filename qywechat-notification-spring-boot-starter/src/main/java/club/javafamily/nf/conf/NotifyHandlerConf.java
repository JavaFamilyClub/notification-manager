package club.javafamily.nf.conf;

import club.javafamily.nf.properties.QyWechatProperties;
import club.javafamily.nf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/8/21 下午9:23
 * @description
 */
@Configuration(proxyBeanMethods = false)
public class NotifyHandlerConf {

   private final QyWechatProperties properties;
   private final RestTemplate restTemplate;
   private final InhibitPolicy inhibitPolicy;

   public NotifyHandlerConf(QyWechatProperties properties,
                            RestTemplate restTemplate,
                            @Autowired(required = false) InhibitPolicy inhibitPolicy)
   {
      this.properties = properties;
      this.restTemplate = restTemplate;
      this.inhibitPolicy = inhibitPolicy;
   }

   /**
    * 定义飞书通知处理器
    * @return 如何启用通知, 返回 {@link QyWechatNotifyHandler}
    * 否则返回 {@link NoOpQyWechatNotifyHandler}
    */
   @Bean
   public QyWechatNotifyHandler qyWechatNotifyHandler() {
      if(properties.getEnabled() == null || properties.getEnabled()) {
         return new QyWechatNotifyHandler(properties, restTemplate, inhibitPolicy);
      }

      return new NoOpQyWechatNotifyHandler(properties);
   }
}
