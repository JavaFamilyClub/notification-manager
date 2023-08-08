package club.javafamily.nf.request.card;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class QyWechatCardRequestContentJump implements Serializable {

    /**
     * 跳转链接类型，0或不填代表不是链接，1 代表跳转url，2 代表跳转小程序
     */
    private int type;

    /**
     * 跳转链接样式的文案内容，建议不超过13个字
     */
    private String title;

    /**
     * 跳转链接的url，jump_list.type是1时必填
     */
    private String url;

    /**
     * 跳转链接的小程序的appid，jump_list.type是2时必填
     */
    private String appid;

    /**
     * 跳转链接的小程序的pagepath，jump_list.type是2时选填
     */
    private String pagepath;

}
