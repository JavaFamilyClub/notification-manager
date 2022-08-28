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
public class PlainTextTagContentItem extends AbstractTagContentItem {
   protected String content;

   public PlainTextTagContentItem() {
      super("plain_text");
   }

   public PlainTextTagContentItem(String text) {
      this();
      this.content = text;
   }

   public PlainTextTagContentItem(String tag, String text) {
      super(tag);
      this.content = text;
   }
}
