package club.javafamily.nf.request.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class NewsRequestContentItem implements Serializable {
    /**
     * 图文消息-标题: 不超过128个字节，超过会自动截断
     */
    protected String title;

    /**
     * 图文消息-描述: 不超过512个字节，超过会自动截断
     */
    protected String description;

    /**
     * 图文消息-跳转链接
     */
    protected String url;

    /**
     * 图文消息-图片地址
     */
    protected String picurl;
}
