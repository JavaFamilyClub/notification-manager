package club.javafamily.nf.request.link;

import club.javafamily.nf.request.DingTalkNotifyRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description Link 消息
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DingTalkLinkRequest extends DingTalkNotifyRequest {

    private LinkRequestContent content;

    public static DingTalkLinkRequest of(String title, String content, String messageUrl) {
        return DingTalkLinkRequest.of(title, content, messageUrl, null);
    }

    public static DingTalkLinkRequest of(String title, String content, String messageUrl,
                                         String picUrl)
    {
        final LinkRequestContent textContent
                = new LinkRequestContent(title, content, messageUrl, picUrl);

        final DingTalkLinkRequest request = new DingTalkLinkRequest();
        request.setContent(textContent);

        return request;
    }

    @Override
    public String getMsgType() {
        return "link";
    }

}