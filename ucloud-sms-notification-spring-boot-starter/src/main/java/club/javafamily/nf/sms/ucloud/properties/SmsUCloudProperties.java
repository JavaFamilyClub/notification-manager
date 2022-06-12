package club.javafamily.nf.sms.ucloud.properties;

import club.javafamily.nf.properties.SmsTemplateInfo;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;
import java.util.Objects;

/**
 * @author Jack Li
 * @date 2022/3/7 2:49 下午
 * @description
 */
@Data
@ConfigurationProperties(prefix = "javafamily.notify.sms.ucloud")
public class SmsUCloudProperties {
   @Builder.Default
   private String appUrl = "https://api.ucloud.cn";
   private String projectId;
   private String publicKey;
   private String privateKey;

   // 短信签名
   private String sign;

   @Builder.Default
   private Integer timeout = 60;

   public static final String DEFAULT = "default";

   private Map<String, SmsTemplateInfo> config;

   public SmsTemplateInfo findConfig() {
      return findConfig(null);
   }

   public SmsTemplateInfo findConfig(String key) {
      key = Objects.toString(key, DEFAULT);

      return config.get(key);
   }
}
