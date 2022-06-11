package club.javafamily.nf.properties;

import club.javafamily.nf.enums.SecurityConfTypeEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:42
 * @description 飞书通知属性配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "javafamily.notify.dingtalk")
public class DingTalkProperties extends WebHookProperties {
}
