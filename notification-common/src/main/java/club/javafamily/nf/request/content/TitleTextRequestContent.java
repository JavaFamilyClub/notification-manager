package club.javafamily.nf.request.content;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description link 消息体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TitleTextRequestContent extends TextRequestContent {

    private String title;

    public TitleTextRequestContent() {
    }

    public TitleTextRequestContent(String title, String text) {
        super(text);
        this.title = title;
    }

}
