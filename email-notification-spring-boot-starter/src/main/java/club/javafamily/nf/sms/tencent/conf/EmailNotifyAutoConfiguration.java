package club.javafamily.nf.sms.tencent.conf;

import club.javafamily.nf.sms.tencent.service.EmailNotifyHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author Jack Li
 * @date 2022/3/3 3:45 下午
 * @description
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean({
        JavaMailSender.class,
        MailProperties.class
})
public class EmailNotifyAutoConfiguration {

   private final MailProperties mailProperties;
   private final JavaMailSender javaMailSender;

   public EmailNotifyAutoConfiguration(MailProperties mailProperties,
                                       JavaMailSender javaMailSender)
   {
      this.mailProperties = mailProperties;
      this.javaMailSender = javaMailSender;
   }

   @Bean
   public EmailNotifyHandler emailNotifyHandler() {
      return new EmailNotifyHandler(javaMailSender, mailProperties);
   }
}
