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
public class LarkMdTagContentItem extends PlainTextTagContentItem {

   public LarkMdTagContentItem() {
      this.tag = "lark_md";
   }

   public LarkMdTagContentItem(String content) {
      this();
      this.content = content;
   }

}
