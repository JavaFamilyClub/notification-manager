package club.javafamily.nf.conf;

import club.javafamily.autoconfigre.cache.config.JavaFamilyCacheAutoConfiguration;
import club.javafamily.nf.properties.QyWechatProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description
 */
@EnableConfigurationProperties(QyWechatProperties.class)
@AutoConfigureAfter({
     RestTemplateAutoConfiguration.class
})
@AutoConfigureBefore({
     /**
      * 需要修改 Cache ttl, 因此需要在 {@link JavaFamilyCacheAutoConfiguration} 之后
      */
     JavaFamilyCacheAutoConfiguration.class
})
@Import({ InhibitNotifyConf.class, NotifyHandlerConf.class })
public class QyWechatNotificationAutoConfiguration {

   private final QyWechatProperties properties;

   public QyWechatNotificationAutoConfiguration(QyWechatProperties qyWechatProperties)
   {
      this.properties = qyWechatProperties;
   }

   /**
    * 将通知组件的抑制 ttl 生效于 Cache 组件. {@link club.javafamily.autoconfigre.cache.config.CacheCustomizer}
    * @return 用于修改 cache ttl 的 Customizer
    */
   @Bean
   @ConditionalOnMissingBean
   public QyWechatJavaFamilyCachePropertiesCustomizer javaFamilyCacheCustomizer() {
      return new QyWechatJavaFamilyCachePropertiesCustomizer(properties);
   }

}
