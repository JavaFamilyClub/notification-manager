package club.javafamily.nf.request.card;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class QyWechatCardRequestContentHorizontal implements Serializable {

    /**
     * 模版卡片的二级标题信息内容支持的类型，1是url，2是文件附件，3 代表点击跳转成员详情
     */
    @Builder.Default
    private int type = 1;

    /**
     * 二级标题，建议不超过5个字
     */
    private String keyname;

    /**
     * 二级文本，如果horizontal_content_list.type是2，该字段代表文件名称（要包含文件类型），建议不超过26个字
     */
    private String value;

    /**
     * 链接跳转的url，horizontal_content_list.type是1时必填
     */
    private String url;

    /**
     * 附件的media_id，horizontal_content_list.type是2时必填
     */
    private String media_id;

    /**
     * 成员详情的userid，horizontal_content_list.type是3时必填
     */
    private String userid;
}
