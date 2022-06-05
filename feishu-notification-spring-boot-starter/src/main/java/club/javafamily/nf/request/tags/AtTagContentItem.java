package club.javafamily.nf.request.tags;

import lombok.*;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtTagContentItem implements TagContentItem  {

   private String user_id;

   @Override
   public String getTag() {
      return "at";
   }
}
