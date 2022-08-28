package club.javafamily.nf.request.tags;

import lombok.*;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AtTagContentItem extends AbstractTagContentItem {

   private String user_id;

   public AtTagContentItem() {
      super("at");
   }

   public AtTagContentItem(String user_id) {
      this();
      this.user_id = user_id;
   }
}
