package club.javafamily.nf.request.card;

import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/5 下午6:18
 * @description
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeiShuCardRequestConfig implements Serializable {

   /**
    * 是否允许卡片被转发
    */
   @Builder.Default
   private boolean enable_forward = true;

   /**
    * 是否为共享卡片。
    * true：是共享卡片，更新卡片的内容对所有收到这张卡片的人员可见。
    * false：是独享卡片，即仅操作用户可见卡片的更新内容。
    */
   @Builder.Default
   private boolean update_multi = false;

}
