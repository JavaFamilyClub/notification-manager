package club.javafamily.nf.request.tags;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActionTagContentItem extends AbstractTagContentItem {

   private List<TagContentItem> actions;

   public ActionTagContentItem() {
      super("action");
   }

   public ActionTagContentItem(List<TagContentItem> actions) {
      this();
      this.actions = actions;
   }

   public ActionTagContentItem(String buttonText, String url) {
      this();
      this.actions = Collections.singletonList(
         new ButtonMdTagContentItem(buttonText, url));
   }

   public ActionTagContentItem(String buttonText,
                               String url,
                               String buttonType)
   {
      this();
      this.actions = Collections.singletonList(
         new ButtonMdTagContentItem(buttonText, url, buttonType));
   }

}
