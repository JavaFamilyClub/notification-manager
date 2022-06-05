package club.javafamily.nf.request.card;

import club.javafamily.nf.request.tags.TagContentItem;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jack Li
 * @date 2022/6/5 下午6:18
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeiShuCardRequestContent implements Serializable {

   @Builder.Default
   private FeiShuCardRequestConfig config = new FeiShuCardRequestConfig();

   private List<TagContentItem> elements;

   private FeiShuCardRequestHeader header;
}
