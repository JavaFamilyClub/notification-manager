package club.javafamily.nf.request.tags;

import lombok.Data;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@Data
public class BaseTextTagContentItem <T>  extends AbstractTagContentItem {
   protected T text;

   public BaseTextTagContentItem() {
      super("text");
   }

   public BaseTextTagContentItem(T text) {
      this();
      this.text = text;
   }

   public BaseTextTagContentItem(String tag, T text) {
      super(tag);
      this.text = text;
   }
}
