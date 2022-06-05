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
@ConfigurationProperties(prefix = "javafamily.notify.feishu")
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

    /**
     * 上传图片地址
     */
    @Builder.Default
    private String uploadImageUrl = "https://open.feishu.cn/open-apis/im/v1/images";
}
