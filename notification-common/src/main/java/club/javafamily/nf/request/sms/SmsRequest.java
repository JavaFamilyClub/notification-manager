package club.javafamily.nf.request.sms;

import club.javafamily.nf.request.NotifyRequest;
import lombok.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Jack Li
 * @date 2022/6/12 下午6:10
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsRequest implements NotifyRequest {

   /**
    * request endpoint
    */
   private String endPoint;

   /**
    * 命名空间 id
    * 项目、SDK 等
    */
   private String namespaceId;
   /**
    * 公钥
    */
   private String secretId;

   /**
    * 私钥
    */
   private String secretKey;

   /**
    * 短信签名
    */
   private String sign;

   /**
    * 模板 ID
    */
   protected String templateId;

   /**
    * 短信参数
    */
   protected List<String> params;

   /**
    * 短信接受者
    */
   protected List<String> receiveUsers;

   @Builder.Default
   protected Integer timeout = 60;

   public List<String> getSafeReceiveUsers() {
      return new CopyOnWriteArrayList<>(receiveUsers);
   }
}
