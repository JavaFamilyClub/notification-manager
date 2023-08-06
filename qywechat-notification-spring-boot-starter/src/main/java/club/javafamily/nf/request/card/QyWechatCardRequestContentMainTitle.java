package club.javafamily.nf.request.card;

import club.javafamily.nf.request.content.TitleDescContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class QyWechatCardRequestContentMainTitle extends TitleDescContent {

    /**
     * 来源文字的颜色，目前支持：0(默认) 灰色，1 黑色，2 红色，3 绿色
     */
    private String desc_color;

}
