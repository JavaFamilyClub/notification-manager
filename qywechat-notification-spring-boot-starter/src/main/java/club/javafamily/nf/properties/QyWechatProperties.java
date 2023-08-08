package club.javafamily.nf.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:42
 * @description 企业微信通知属性配置
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
@ConfigurationProperties(prefix = "javafamily.notify.qywechat")
public class QyWechatProperties extends WebHookProperties {
    /**
     * 上传文件地址
     */
    private String uploadUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/upload_media";

    /**
     * 获取密钥
     * @return 密钥
     */
    public String findKey() {
        // TODO
        return null;
    }
}
