package club.javafamily.nf.request.card;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class QyWechatCardRequestContentQuoteArea implements Serializable {

    /**
     * 引用文献样式区域点击事件，0或不填代表没有点击事件，1 代表跳转url，2 代表跳转小程序
     */
    private int type;

    /**
     * 点击跳转的url，quote_area.type是1时必填
     */
    private String url;

    /**
     * 点击跳转的小程序的appid，quote_area.type是2时必填
     */
    private String appid;

    /**
     * 点击跳转的小程序的pagepath，quote_area.type是2时选填
     */
    private String pagepath;

    /**
     * 引用文献样式的标题
     */
    private String title;

    /**
     * 引用文献样式的引用文案
     */
    private String quote_text;
}
