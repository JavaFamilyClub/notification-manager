package club.javafamily.nf.request.card;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class QyWechatCardRequestContentCardImage implements Serializable {

    /**
     * 图片的url
     */
    private String url;

    /**
     * 图片的宽高比，宽高比要小于2.25，大于1.3，不填该参数默认1.3
     */
    @Builder.Default
    private float aspect_ratio = 1.3F;

}
