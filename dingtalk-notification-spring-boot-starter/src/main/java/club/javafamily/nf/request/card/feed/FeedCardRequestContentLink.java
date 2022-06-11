package club.javafamily.nf.request.card.feed;

import club.javafamily.nf.request.content.TextRequestContent;
import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description link 消息体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedCardRequestContentLink implements Serializable {

    private String title;

    /**
     * link
     */
    private String messageURL;

    /**
     * link view image url. (Optional)
     */
    private String picURL;

}
