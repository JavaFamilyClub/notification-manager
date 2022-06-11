package club.javafamily.nf.request.card.single;

import club.javafamily.nf.enums.CardBtnOrientationEnum;
import club.javafamily.nf.request.content.TitleTextRequestContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description link 消息体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SingleCardRequestContent extends TitleTextRequestContent {

    /**
     * 单个按钮的标题。
     * @warn 设置此项和singleURL后，btns无效
     */
    private String singleTitle;

    /**
     * 点击消息跳转的URL，打开方式如下：
     *
     * 1. 移动端，在钉钉客户端内打开
     * 2. PC端
     * 3. 默认侧边栏打开
     *
     * 希望在外部浏览器打开，请参考
     * https://open.dingtalk.com/document/orgapp-server/message-link-description
     * 在PC客户端点击消息中的URL链接时，希望控制链接的打开方式，可以使用以下方式：
     * dingtalk://dingtalkclient/page/link?url=http%3A%2F%2Fwww.dingtalk.com&pc_slide=true
     */
    private String singleURL;

    /**
     * Optional
     * 0：按钮竖直排列
     * 1：按钮横向排列
     */
    private Integer btnOrientation;

    public SingleCardRequestContent() {
    }

    public SingleCardRequestContent(String title, String text, String singleTitle, String singleURL) {
        super(title, text);
        this.singleTitle = singleTitle;
        this.singleURL = singleURL;
    }

    public SingleCardRequestContent(String title,
                                    String text,
                                    String singleTitle,
                                    String singleURL,
                                    CardBtnOrientationEnum btnOrientationEnum)
    {
        super(title, text);
        this.singleTitle = singleTitle;
        this.singleURL = singleURL;
        this.btnOrientation = btnOrientationEnum != null ? btnOrientationEnum.ordinal() : null;
    }
}
