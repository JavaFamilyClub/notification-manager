package club.javafamily.nf.request.content;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MentionedContentRequestContent extends ContentRequestContent {
    /**
     * 提醒列表: ["wangqing","@all"]
     */
    protected List<String> mentioned_list;

    /**
     * 提醒电话:["13800001111","@all"]
     */
    protected List<String> mentioned_mobile_list;

    public MentionedContentRequestContent(String content) {
        super(content);
    }
}
