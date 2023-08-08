package club.javafamily.nf.request.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ImageRequestContent implements Serializable {
    /**
     * 图片编码
     */
    protected String base64;

    /**
     * 图片内容（base64编码前）的md5值
     */
    protected String md5;
}
