package club.javafamily.nf.conf;

import club.javafamily.nf.properties.DingTalkProperties;
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
public class DingTalkNotifyHandlerConf {

   private final DingTalkProperties properties;
   private final RestTemplate restTemplate;
   private final InhibitPolicy inhibitPolicy;

   public DingTalkNotifyHandlerConf(DingTalkProperties properties,
                                    RestTemplate restTemplate,
                                    @Autowired(required = false) InhibitPolicy inhibitPolicy)
   {
      this.properties = properties;
      this.restTemplate = restTemplate;
      this.inhibitPolicy = inhibitPolicy;
   }

   /**
    * 定义飞书通知处理器
    * @return 如何启用通知, 返回 {@link DingTalkNotifyHandler}
    * 否则返回 {@link NoOpDingTalkNotifyHandler}
    */
   @Bean
   public DingTalkNotifyHandler dingTalkNotifyHandler() {
      if(properties.getEnabled() == null || properties.getEnabled()) {
         return new DingTalkNotifyHandler(properties, restTemplate, inhibitPolicy);
      }

      return new NoOpDingTalkNotifyHandler(properties);
   }
}
