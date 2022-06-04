package club.javafamily.nf.request.post;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LinkPostTagContentItem extends PostTagContentItem {
   private String href;

   public LinkPostTagContentItem() {
      this.tag = "a";
   }

   public LinkPostTagContentItem(String text, String href) {
      this();
      this.text = text;
      this.href = href;
   }
}
