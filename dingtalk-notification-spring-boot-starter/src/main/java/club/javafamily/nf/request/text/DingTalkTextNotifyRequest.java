package club.javafamily.nf.request.text;

import club.javafamily.nf.request.DingTalkNotifyRequest;
import club.javafamily.nf.request.content.ContentTextRequestContent;
import club.javafamily.nf.request.content.TextRequestContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DingTalkTextNotifyRequest extends DingTalkNotifyRequest {

    private ContentTextRequestContent text;

    public static DingTalkTextNotifyRequest of(String content) {
        return DingTalkTextNotifyRequest.of(content, false, null);
    }

    public static DingTalkTextNotifyRequest of(String text, boolean atAll, String...atUserPhones) {
        final ContentTextRequestContent content = new ContentTextRequestContent(text);

        final DingTalkTextNotifyRequest request = new DingTalkTextNotifyRequest();
        request.setText(content);
        request.buildAtConf(atAll, atUserPhones);

        return request;
    }

    @Override
    public String getMsgtype() {
        return "text";
    }

}
