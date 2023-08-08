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
public class ContentRequestContent implements Serializable {
    /**
     * 文本内容
     */
    protected String content;
}
