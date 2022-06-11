package club.javafamily.nf.request.card.feed;

import club.javafamily.nf.enums.CardBtnOrientationEnum;
import club.javafamily.nf.request.content.TitleTextRequestContent;
import club.javafamily.nf.request.link.LinkRequestContent;
import club.javafamily.nf.request.tags.CardBtn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description feed card 消息体
 */
@Data
public class FeedCardRequestContent implements Serializable {

    /**
     * feed links
     * @warn LinkRequestContent 只有 title, messageURL, picURL 生效
     */
    private LinkRequestContent[] links;

    public FeedCardRequestContent() {
    }

    public FeedCardRequestContent(LinkRequestContent[] links) {
       this.links = links;
    }

}
