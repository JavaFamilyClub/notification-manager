package club.javafamily.nf.conf;

import club.javafamily.autoconfigre.cache.config.JavaFamilyCacheAutoConfiguration;
import club.javafamily.autoconfigre.resttemplate.config.RestTemplateAutoConfiguration;
import club.javafamily.nf.properties.FeiShuProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description
 */
@EnableConfigurationProperties(FeiShuProperties.class)
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
public class FeiShuNotificationAutoConfiguration {

   private final FeiShuProperties properties;

   public FeiShuNotificationAutoConfiguration(FeiShuProperties feiShuProperties)
   {
      this.properties = feiShuProperties;
   }

   /**
    * 将通知组件的抑制 ttl 生效于 Cache 组件. {@link club.javafamily.autoconfigre.cache.config.CacheCustomizer}
    * @return 用于修改 cache ttl 的 Customizer
    */
   @Bean
   public JavaFamilyCachePropertiesCustomizer javaFamilyCacheCustomizer() {
      return new JavaFamilyCachePropertiesCustomizer(properties);
   }

}
