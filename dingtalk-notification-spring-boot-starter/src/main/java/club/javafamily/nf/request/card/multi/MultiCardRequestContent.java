package club.javafamily.nf.request.card.multi;

import club.javafamily.nf.enums.CardBtnOrientationEnum;
import club.javafamily.nf.request.content.TitleTextRequestContent;
import club.javafamily.nf.request.tags.CardBtn;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description link 消息体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MultiCardRequestContent extends TitleTextRequestContent {

    /**
     * 单个按钮的标题。
     * @warn 设置此项和singleURL后，btns无效
     */
    private CardBtn[] btns;

    /**
     * Optional
     * 0：按钮竖直排列
     * 1：按钮横向排列
     */
    private Integer btnOrientation;

    public MultiCardRequestContent() {
    }

    public MultiCardRequestContent(String title, String text, CardBtn[] btns) {
        this(title, text, btns, null);
    }

    public MultiCardRequestContent(String title,
                                   String text,
                                   CardBtn[] btns,
                                   CardBtnOrientationEnum btnOrientationEnum)
    {
        super(title, text);
        this.btns = btns;
        this.btnOrientation = btnOrientationEnum != null ? btnOrientationEnum.ordinal() : null;
    }
}
