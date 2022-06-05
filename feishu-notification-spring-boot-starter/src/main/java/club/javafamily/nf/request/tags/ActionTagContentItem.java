package club.javafamily.nf.request.tags;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@Data
public class ActionTagContentItem implements TagContentItem {

   private List<TagContentItem> actions;

   public ActionTagContentItem() {
   }

   public ActionTagContentItem(List<TagContentItem> actions) {
      this.actions = actions;
   }

   public ActionTagContentItem(String buttonText, String url) {
      this.actions = Collections.singletonList(
         new ButtonMdTagContentItem(buttonText, url));
   }

   public ActionTagContentItem(String buttonText,
                               String url,
                               String buttonType)
   {
      this.actions = Collections.singletonList(
         new ButtonMdTagContentItem(buttonText, url, buttonType));
   }

   @Override
   public String getTag() {
      return "action";
   }
}
