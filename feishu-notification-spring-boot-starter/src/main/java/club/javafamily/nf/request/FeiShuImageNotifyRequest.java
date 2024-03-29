package club.javafamily.nf.request;

import lombok.*;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeiShuImageNotifyRequest extends FeiShuNotifyRequest {

   private FeiShuImageContent content;

   public FeiShuImageNotifyRequest() {
      super("image");
   }

   public FeiShuImageNotifyRequest(FeiShuImageContent content) {
      this();
      this.content = content;
   }

   public static FeiShuImageNotifyRequest of(InputStream in) {
      final FeiShuImageStreamContent textContent = new FeiShuImageStreamContent(in);

      final FeiShuImageNotifyRequest request = new FeiShuImageNotifyRequest();
      request.setContent(textContent);

      return request;
   }

   public static FeiShuImageNotifyRequest of(String imageKey) {
      final FeiShuImageContent textContent = new FeiShuImageContent(imageKey);

      final FeiShuImageNotifyRequest request = new FeiShuImageNotifyRequest();
      request.setContent(textContent);

      return request;
   }

   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class FeiShuImageContent implements Serializable {
      protected String image_key;
   }

   @EqualsAndHashCode(callSuper = true)
   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class FeiShuImageStreamContent extends FeiShuImageContent {
      private InputStream imageStream;
   }
}
