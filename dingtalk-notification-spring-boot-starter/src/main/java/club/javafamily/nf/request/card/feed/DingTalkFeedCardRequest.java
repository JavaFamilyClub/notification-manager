package club.javafamily.nf.request.card.feed;

import club.javafamily.nf.enums.CardBtnOrientationEnum;
import club.javafamily.nf.request.DingTalkNotifyRequest;
import club.javafamily.nf.request.link.LinkRequestContent;
import club.javafamily.nf.request.tags.CardBtn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DingTalkFeedCardRequest extends DingTalkNotifyRequest {

    private FeedCardRequestContent feedCard;

    public static DingTalkFeedCardRequest of(LinkRequestContent...links) {
        final FeedCardRequestContent content = new FeedCardRequestContent(links);

        DingTalkFeedCardRequest request = new DingTalkFeedCardRequest();
        request.setFeedCard(content);

        return request;
    }

    @Override
    public String getMsgType() {
        return "feedCard";
    }

}
