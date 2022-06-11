package club.javafamily.nf.request.card.multi;

import club.javafamily.nf.enums.CardBtnOrientationEnum;
import club.javafamily.nf.request.DingTalkNotifyRequest;
import club.javafamily.nf.request.tags.CardBtn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DingTalkMultiBtnCardRequest extends DingTalkNotifyRequest {

    private MultiCardRequestContent actionCard;

    public static DingTalkMultiBtnCardRequest of(String title, String text,
                                                 CardBtn...btns)
    {
        return DingTalkMultiBtnCardRequest.of(title, text, null, btns);
    }

    public static DingTalkMultiBtnCardRequest of(String title, String text,
                                                 CardBtnOrientationEnum btnOrientationEnum,
                                                 CardBtn...btns)
    {
        final MultiCardRequestContent content
                = new MultiCardRequestContent(title, text, btns, btnOrientationEnum);

        DingTalkMultiBtnCardRequest request = new DingTalkMultiBtnCardRequest();
        request.setActionCard(content);

        return request;
    }

    @Override
    public String getMsgtype() {
        return "actionCard";
    }

}
