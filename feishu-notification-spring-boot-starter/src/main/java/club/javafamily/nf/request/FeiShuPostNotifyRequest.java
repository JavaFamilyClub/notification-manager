package club.javafamily.nf.request;

import club.javafamily.nf.request.post.PostTagContentItem;
import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeiShuPostNotifyRequest extends FeiShuNotifyRequest {

   private FeiShuPostContent content;

   public static FeiShuPostNotifyRequest of(String title, PostTagContentItem...items) {
      return FeiShuPostNotifyRequest.of(title, new PostTagContentItem[][] { items });
   }

   public static FeiShuPostNotifyRequest of(String title, PostTagContentItem[][] items) {
      final FeiShuPostContent requestContent
         = new FeiShuPostContent(new FeiShuPostContentPost(
            new FeiShuPostContentPostLocale(title, items)
      ));

      final FeiShuPostNotifyRequest request = new FeiShuPostNotifyRequest();
      request.setContent(requestContent);

      return request;
   }

   @Override
   public String getMsg_type() {
      return "post";
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
      private PostTagContentItem[][] content;
   }
}
