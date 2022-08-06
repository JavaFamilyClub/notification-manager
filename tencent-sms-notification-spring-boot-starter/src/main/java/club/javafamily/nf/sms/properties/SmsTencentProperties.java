package club.javafamily.nf.sms.properties;

import club.javafamily.nf.constant.NotificationConstant;
import club.javafamily.nf.properties.SmsTemplateInfo;
import club.javafamily.nf.request.sms.SmsRequest;
import club.javafamily.utils.common.MessageException;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

/**
 * @author Jack Li
 * @date 2022/3/7 2:49 下午
 * @description
 */
@Data
@ConfigurationProperties(prefix = "javafamily.notify.sms.tencent")
public class SmsTencentProperties {
   private String appUrl = "sms.tencentcloudapi.com";
   private String sdkAppId;
   private String secretId;
   private String secretKey;

   private String sign;

   private Integer timeout = 60;

   private Map<String, SmsTemplateInfo> config;

   public SmsTemplateInfo findConfig() {
      return findConfig(null);
   }

   public SmsTemplateInfo findConfig(String key) {
      key = Objects.toString(key, NotificationConstant.DEFAULT);

      return config.get(key);
   }

   public SmsRequest buildRequest(String template,
                                  List<String> receiveUsers,
                                  List<String> params)
   {
      final SmsTemplateInfo templateInfo = findConfig(template);

      if(templateInfo == null) {
         throw new MessageException("未配置模板: " + template);
      }

      return SmsRequest.builder()
         .endPoint(getAppUrl())
         .namespaceId(getSdkAppId())
         .secretId(getSecretId())
         .secretKey(getSecretKey())
         .templateId(templateInfo.getTemplateId())
         .sign(getSign())
         .params(params)
         .receiveUsers(receiveUsers)
         .timeout(getTimeout())
         .build();
   }
}
