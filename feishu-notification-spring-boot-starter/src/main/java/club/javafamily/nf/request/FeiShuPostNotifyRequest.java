package club.javafamily.nf.request;

import club.javafamily.nf.request.tags.BaseTextTagContentItem;
import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 富文本消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeiShuPostNotifyRequest extends FeiShuNotifyRequest {

   private FeiShuPostContent content;

   public FeiShuPostNotifyRequest() {
      super("post");
   }

   public FeiShuPostNotifyRequest(FeiShuPostContent content) {
      this();
      this.content = content;
   }

   public static FeiShuPostNotifyRequest of(String title, BaseTextTagContentItem...items) {
      return FeiShuPostNotifyRequest.of(title, new BaseTextTagContentItem[][] { items });
   }

   public static FeiShuPostNotifyRequest of(String title, BaseTextTagContentItem[][] items) {
      final FeiShuPostContent requestContent
         = new FeiShuPostContent(new FeiShuPostContentPost(
            new FeiShuPostContentPostLocale(title, items)
      ));

      final FeiShuPostNotifyRequest request = new FeiShuPostNotifyRequest();
      request.setContent(requestContent);

      return request;
   }

   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class FeiShuPostContent implements Serializable {
      private FeiShuPostContentPost post;
   }

   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class FeiShuPostContentPost implements Serializable {
      private FeiShuPostContentPostLocale zh_cn;
   }

   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class FeiShuPostContentPostLocale implements Serializable {
      private String title;
      private BaseTextTagContentItem[][] content;
   }
}
