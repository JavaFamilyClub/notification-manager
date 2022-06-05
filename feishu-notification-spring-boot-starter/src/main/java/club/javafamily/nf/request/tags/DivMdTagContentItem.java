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
public class DivMdTagContentItem extends BaseTextTagContentItem<LarkMdTagContentItem> {

   public DivMdTagContentItem() {
      this.tag = "div";
   }

   public DivMdTagContentItem(String text) {
      this();
      this.text = new LarkMdTagContentItem(text);
   }


   public DivMdTagContentItem(LarkMdTagContentItem text) {
      this();
      this.text = text;
   }

}
