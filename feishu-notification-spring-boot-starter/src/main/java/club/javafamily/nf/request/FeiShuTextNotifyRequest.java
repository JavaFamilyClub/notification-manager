package club.javafamily.nf.request;

import club.javafamily.nf.request.content.TextRequestContent;
import lombok.*;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeiShuTextNotifyRequest extends FeiShuNotifyRequest {

   private TextRequestContent content;

   public FeiShuTextNotifyRequest() {
      super("text");
   }

   public FeiShuTextNotifyRequest(TextRequestContent content) {
      this();
      this.content = content;
   }

   public static FeiShuTextNotifyRequest of(String content) {
      final TextRequestContent textContent = new TextRequestContent(content);

      final FeiShuTextNotifyRequest request = new FeiShuTextNotifyRequest();
      request.setContent(textContent);

      return request;
   }

}
