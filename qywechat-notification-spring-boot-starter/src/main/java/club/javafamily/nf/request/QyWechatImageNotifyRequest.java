package club.javafamily.nf.request;

import club.javafamily.nf.request.content.ImageRequestContent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.util.security.MD5Encoder;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QyWechatImageNotifyRequest extends QyWechatNotifyRequest {

   private ImageRequestContent image;

   public QyWechatImageNotifyRequest() {
      super("image");
   }

   public QyWechatImageNotifyRequest(ImageRequestContent content) {
      this();
      this.image = content;
   }

   public static String encryptMD5(byte[] data) {
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         md.update(data);
         byte[] digest = md.digest();
         StringBuilder sb = new StringBuilder();
         for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
         }
         return sb.toString();
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   public static QyWechatImageNotifyRequest of(byte[] data) {
      String base64 = Base64.getEncoder().encodeToString(data);
      String md5 = encryptMD5(data);

      return QyWechatImageNotifyRequest.of(base64, md5);
   }

   public static QyWechatImageNotifyRequest of(String base64, String md5) {
      final ImageRequestContent textContent = new ImageRequestContent(base64, md5);

      final QyWechatImageNotifyRequest request = new QyWechatImageNotifyRequest();
      request.setImage(textContent);

      return request;
   }

}
