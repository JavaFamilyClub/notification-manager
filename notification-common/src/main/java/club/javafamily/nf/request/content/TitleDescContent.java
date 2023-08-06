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
public class TitleDescContent implements Serializable {
    /**
     * 标题
     */
    protected String title;

    /**
     * 描述
     */
    protected String desc;
}
