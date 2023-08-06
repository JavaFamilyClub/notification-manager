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
public class QyWechatCardRequestContentCardAction implements Serializable {

    /**
     * 卡片跳转类型，1 代表跳转url，2 代表打开小程序。text_notice模版卡片中该字段取值范围为[1,2]
     */
    private String type;

    /**
     * 跳转事件的url，card_action.type是1时必填
     */
    private String url;

    /**
     * 跳转事件的小程序的appid，card_action.type是2时必填
     */
    private String appid;

    /**
     * 跳转事件的小程序的pagepath，card_action.type是2时选填
     */
    private String pagepath;

}
