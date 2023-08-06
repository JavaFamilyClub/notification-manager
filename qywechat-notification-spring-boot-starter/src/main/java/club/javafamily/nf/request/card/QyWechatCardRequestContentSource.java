package club.javafamily.nf.request.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class QyWechatCardRequestContentSource implements Serializable {

    /**
     * 来源图片的url
     */
    private String icon_url;

    /**
     * 来源图片的描述，建议不超过13个字
     */
    private String desc;

    /**
     * 来源文字的颜色，目前支持：0(默认) 灰色，1 黑色，2 红色，3 绿色
     */
    private String desc_color;

}
