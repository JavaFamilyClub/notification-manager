package club.javafamily.nf.request;

import club.javafamily.nf.request.card.QyWechatCardRequestContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QyWechatCardNotifyRequest extends QyWechatNotifyRequest {

   private QyWechatCardRequestContent template_card;

   public QyWechatCardNotifyRequest() {
      super("template_card");
   }

   public QyWechatCardNotifyRequest(QyWechatCardRequestContent content) {
      this();
      this.template_card = content;
   }

}
