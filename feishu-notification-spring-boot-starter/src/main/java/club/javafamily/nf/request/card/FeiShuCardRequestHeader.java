package club.javafamily.nf.request.card;

import club.javafamily.nf.request.tags.PlainTextTagContentItem;
import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/5 下午6:18
 * @description
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeiShuCardRequestHeader implements Serializable {
   private PlainTextTagContentItem title;
}
