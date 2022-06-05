package club.javafamily.nf.request.tags;

import lombok.Data;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@Data
public class PlainTextTagContentItem implements TagContentItem {
   protected String tag = "plain_text";
   protected String content;

   public PlainTextTagContentItem() {
   }

   public PlainTextTagContentItem(String text) {
      this.content = text;
   }

   public PlainTextTagContentItem(String tag, String text) {
      this.tag = tag;
      this.content = text;
   }
}
