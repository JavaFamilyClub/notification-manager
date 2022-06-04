package club.javafamily.nf.properties;

import club.javafamily.nf.enums.SecurityConfTypeEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 飞书通知属性配置
 */
@Data
@Component
@EnableConfigurationProperties(FeiShuProperties.class)
@ConfigurationProperties(prefix = "club.javafamily.nf.feishu")
public class FeiShuProperties {
    /**
     * WehbHook url
     */
    private String hookUrl;

    /**
     * 安全设置
     */
    @Builder.Default
    private SecurityConfTypeEnum securityType = SecurityConfTypeEnum.KEYWORD;

    /**
     * 关键词
     */
    private String keyWord;
}
