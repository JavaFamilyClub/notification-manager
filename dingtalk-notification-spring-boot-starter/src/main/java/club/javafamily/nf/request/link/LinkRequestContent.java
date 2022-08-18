package club.javafamily.nf.request.link;

import club.javafamily.nf.request.content.TextRequestContent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description link 消息体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class LinkRequestContent extends TextRequestContent {

    private String title;

    /**
     * link
     */
    private String messageUrl;

    /**
     * link view image url. (Optional)
     */
    private String picUrl;

    public LinkRequestContent() {
    }

    public LinkRequestContent(String title, String text, String messageUrl, String picUrl) {
        super(text);
        this.title = title;
        this.messageUrl = messageUrl;
        this.picUrl = picUrl;
    }

    public LinkRequestContent(String title, String messageUrl, String picUrl) {
        this.title = title;
        this.messageUrl = messageUrl;
        this.picUrl = picUrl;
    }
}
