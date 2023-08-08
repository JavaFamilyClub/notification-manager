package club.javafamily.nf.request.card;

import club.javafamily.nf.request.content.TitleDescContent;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@SuperBuilder(toBuilder = true)
public class QyWechatCardRequestContentImageTextArea extends TitleDescContent {

    /**
     * 左图右文样式区域点击事件，0或不填代表没有点击事件，1 代表跳转url，2 代表跳转小程序
     */
    private int type;

    /**
     * 点击跳转的url，image_text_area.type是1时必填
     */
    private String url;

    /**
     * 点击跳转的小程序的appid，必须是与当前应用关联的小程序，image_text_area.type是2时必填
     */
    private String appid;

    /**
     * 点击跳转的小程序的pagepath，quote_area.type是2时选填
     */
    private String pagepath;

    /**
     * 左图右文样式的图片url
     */
    private String image_url;

}
