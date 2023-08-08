package club.javafamily.nf.request.card;

import club.javafamily.nf.request.content.TitleDescContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class QyWechatCardRequestContent implements Serializable {

    /**
     * 卡片类型
     */
    protected String card_type;

    public QyWechatCardRequestContent() {
        this.card_type = "text_notice";
    }

    /**
     * 卡片来源样式信息，不需要来源样式可不填写
     */
    protected QyWechatCardRequestContentSource source;

    /**
     * 模版卡片的主要内容，包括一级标题和标题辅助信息
     */
    protected TitleDescContent main_title;

    /**
     * 关键数据样式
     */
    protected TitleDescContent emphasis_content;

    /**
     * 引用文献样式，建议不与关键数据共用
     */
    protected QyWechatCardRequestContentQuoteArea quote_area;

    /**
     * 二级普通文本，建议不超过112个字。模版卡片主要内容的一级标题main_title.title和二级普通文本sub_title_text必须有一项填写
     */
    protected String sub_title_text;

    /**
     * 二级标题+文本列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过6
     */
    protected List<QyWechatCardRequestContentHorizontal> horizontal_content_list;

    /**
     * 跳转指引样式的列表，该字段可为空数组，但有数据的话需确认对应字段是否必填，列表长度不超过3
     */
    protected List<QyWechatCardRequestContentJump> jump_list;

    /**
     * 整体卡片的点击跳转事件，text_notice模版卡片中该字段为必填项
     */
    protected QyWechatCardRequestContentCardAction card_action;

}
