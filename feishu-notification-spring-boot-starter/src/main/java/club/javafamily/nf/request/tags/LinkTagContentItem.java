package club.javafamily.nf.request.tags;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LinkTagContentItem extends BaseTextTagContentItem {
   private String href;

   public LinkTagContentItem() {
      this.tag = "a";
   }

   public LinkTagContentItem(String text, String href) {
      this();
      this.text = text;
      this.href = href;
   }
}
