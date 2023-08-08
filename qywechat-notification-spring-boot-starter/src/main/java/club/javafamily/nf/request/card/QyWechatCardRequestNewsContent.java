package club.javafamily.nf.request.card;

import club.javafamily.nf.request.content.TitleDescContent;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@ToString
public class QyWechatCardRequestNewsContent extends QyWechatCardRequestContent {

    public QyWechatCardRequestNewsContent() {
        this.card_type = "news_notice";
    }

    /**
     * 图片样式
     */
    protected QyWechatCardRequestContentCardImage card_image;

    /**
     * 左图右文样式
     */
    protected QyWechatCardRequestContentImageTextArea image_text_area;

    /**
     * 卡片二级垂直内容，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过4
     */
    protected List<TitleDescContent> vertical_content_list;


}
