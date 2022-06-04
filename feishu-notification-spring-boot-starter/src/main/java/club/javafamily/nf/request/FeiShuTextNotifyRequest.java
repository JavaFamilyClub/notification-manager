package club.javafamily.nf.request;

import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeiShuTextNotifyRequest extends NotifyRequest {
   @Builder.Default
   private String msg_type = "text";

   private FeiShuTextContent content;

   public static FeiShuTextNotifyRequest of(String content) {
      final FeiShuTextContent textContent = new FeiShuTextContent(content);

      return FeiShuTextNotifyRequest.builder()
         .content(textContent)
         .build();
   }

   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class FeiShuTextContent implements Serializable {
      private String text;
   }
}
