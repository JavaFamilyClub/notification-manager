package club.javafamily.nf.request;

import club.javafamily.nf.request.content.ImageRequestContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

   public static QyWechatImageNotifyRequest of(String base64, String md5) {
      final ImageRequestContent textContent = new ImageRequestContent(base64, md5);

      final QyWechatImageNotifyRequest request = new QyWechatImageNotifyRequest();
      request.setImage(textContent);

      return request;
   }

}
