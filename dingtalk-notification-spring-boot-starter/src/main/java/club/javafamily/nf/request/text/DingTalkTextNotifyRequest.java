package club.javafamily.nf.request.text;

import club.javafamily.nf.request.DingTalkNotifyRequest;
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

    private TextRequestContent content;

    public static DingTalkTextNotifyRequest of(String content, boolean atAll, String...atUserPhones) {
        final TextRequestContent textContent = new TextRequestContent(content);

        final DingTalkTextNotifyRequest request = new DingTalkTextNotifyRequest();
        request.setContent(textContent);
        request.buildAtConf(atAll, atUserPhones);

        return request;
    }

    @Override
    public String getMsgType() {
        return "text";
    }

}
