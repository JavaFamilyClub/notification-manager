package club.javafamily.nf.request.post;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@Data
public class PostTagContentItem implements Serializable {
   protected String tag = "text";
   protected String text;

   public PostTagContentItem() {
   }

   public PostTagContentItem(String text) {
      this.text = text;
   }

   public PostTagContentItem(String tag, String text) {
      this.tag = tag;
      this.text = text;
   }
}
