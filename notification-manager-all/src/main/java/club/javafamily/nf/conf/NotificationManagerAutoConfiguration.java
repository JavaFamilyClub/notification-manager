package club.javafamily.nf.conf;

import club.javafamily.nf.service.NotifyHandler;
import club.javafamily.nf.service.NotifyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(NotifyHandler.class)
public class NotificationManagerAutoConfiguration {

    private final List<NotifyHandler> handlers;

    public NotificationManagerAutoConfiguration(List<NotifyHandler> handlers) {
        this.handlers = handlers;
    }

    @Bean
    public NotifyService notifyService() {
        return new NotifyService(handlers);
    }
}
