package club.javafamily.nf.request.card.single;

import club.javafamily.nf.enums.CardBtnOrientationEnum;
import club.javafamily.nf.request.DingTalkNotifyRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DingTalkSingleBtnCardRequest extends DingTalkNotifyRequest {

    private SingleCardRequestContent actionCard;

    public static DingTalkSingleBtnCardRequest of(String title, String text,
                                                  String singleTitle, String singleURL)
    {
        return DingTalkSingleBtnCardRequest.of(title, text, singleTitle, singleURL, null);
    }

    public static DingTalkSingleBtnCardRequest of(String title, String text,
                                                  String singleTitle, String singleURL,
                                                  CardBtnOrientationEnum btnOrientationEnum)
    {
        final SingleCardRequestContent content
                = new SingleCardRequestContent(title, text, singleTitle, singleURL, btnOrientationEnum);

        DingTalkSingleBtnCardRequest request = new DingTalkSingleBtnCardRequest();
        request.setActionCard(content);

        return request;
    }

    @Override
    public String getMsgtype() {
        return "actionCard";
    }

}
