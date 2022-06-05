package club.javafamily.nf.request.tags;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ButtonMdTagContentItem extends BaseTextTagContentItem<LarkMdTagContentItem> {

   private static final String DEFAULT_BUTTON_TYPE = "default";

   private String url;
   private String type = DEFAULT_BUTTON_TYPE;
   private Object value;

   public ButtonMdTagContentItem() {
      this.tag = "button";
   }

   public ButtonMdTagContentItem(String text, String url) {
      this(text, url, DEFAULT_BUTTON_TYPE);
   }

   public ButtonMdTagContentItem(String text, String url, String type) {
      this(new LarkMdTagContentItem(text), url, type, null);
   }

   public ButtonMdTagContentItem(LarkMdTagContentItem text,
                                 String url,
                                 String type,
                                 Object value)
   {
      this();
      this.text = text;
      this.url = url;
      this.type = Objects.toString(type, DEFAULT_BUTTON_TYPE);
      this.value = value;
   }
}
