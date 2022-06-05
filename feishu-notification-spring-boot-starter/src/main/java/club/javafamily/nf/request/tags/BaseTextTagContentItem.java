package club.javafamily.nf.request.tags;

import lombok.Data;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@Data
public class BaseTextTagContentItem <T> implements TagContentItem {
   protected String tag = "text";
   protected T text;

   public BaseTextTagContentItem() {
   }

   public BaseTextTagContentItem(T text) {
      this.text = text;
   }

   public BaseTextTagContentItem(String tag, T text) {
      this.tag = tag;
      this.text = text;
   }
}
