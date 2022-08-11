package club.javafamily.nf.properties;

import club.javafamily.nf.enums.SecurityConfTypeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:42
 * @description 飞书通知属性配置
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
@ConfigurationProperties(prefix = "javafamily.notify.feishu")
public class FeiShuProperties extends WebHookProperties {
    /**
     * 上传图片地址
     */
    private String uploadImageUrl = "https://open.feishu.cn/open-apis/im/v1/images";
}
