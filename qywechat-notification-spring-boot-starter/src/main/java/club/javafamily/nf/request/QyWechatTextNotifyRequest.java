package club.javafamily.nf.request;

import club.javafamily.nf.request.content.MentionedContentRequestContent;
import lombok.*;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QyWechatTextNotifyRequest extends QyWechatNotifyRequest {

   private MentionedContentRequestContent text;

   public QyWechatTextNotifyRequest() {
      super("text");
   }

   public QyWechatTextNotifyRequest(MentionedContentRequestContent content) {
      this();
      this.text = content;
   }

   public static QyWechatTextNotifyRequest of(String content) {
      final MentionedContentRequestContent textContent = new MentionedContentRequestContent(content);

      final QyWechatTextNotifyRequest request = new QyWechatTextNotifyRequest();
      request.setText(textContent);

      return request;
   }

}
