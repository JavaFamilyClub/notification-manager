package club.javafamily.nf.request.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@SuperBuilder(toBuilder = true)
public class TitleDescContent implements Serializable {
    /**
     * 标题
     */
    protected String title;

    /**
     * 描述
     */
    protected String desc;

    public static TitleDescContent of(String title, String desc) {
        return new TitleDescContent(title, desc);
    }
}
