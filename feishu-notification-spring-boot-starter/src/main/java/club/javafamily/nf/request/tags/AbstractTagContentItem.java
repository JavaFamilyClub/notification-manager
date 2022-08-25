package club.javafamily.nf.request.tags;

/**
 * @author Jack Li
 * @date 2022/8/25 上午9:25
 * @description
 */
public abstract class AbstractTagContentItem implements TagContentItem {

   protected String tag;

   public AbstractTagContentItem() {
   }

   public AbstractTagContentItem(String tag) {
      this.tag = tag;
   }

   @Override
   public String getTag() {
      return tag;
   }
}
