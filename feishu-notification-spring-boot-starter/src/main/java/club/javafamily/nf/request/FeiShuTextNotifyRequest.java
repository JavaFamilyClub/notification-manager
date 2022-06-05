package club.javafamily.nf.request;

import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeiShuTextNotifyRequest extends FeiShuNotifyRequest {

   private FeiShuTextContent content;

   public static FeiShuTextNotifyRequest of(String content) {
      final FeiShuTextContent textContent = new FeiShuTextContent(content);

      final FeiShuTextNotifyRequest request = new FeiShuTextNotifyRequest();
      request.setContent(textContent);

      return request;
   }

   @Override
   public String getMsg_type() {
      return "text";
   }

   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class FeiShuTextContent implements Serializable {
      private String text;
   }
}
