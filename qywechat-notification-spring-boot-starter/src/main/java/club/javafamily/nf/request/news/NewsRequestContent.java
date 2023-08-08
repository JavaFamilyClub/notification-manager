package club.javafamily.nf.request.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class NewsRequestContent implements Serializable {
    /**
     * 图文消息: 一个图文消息支持1到8条图文
     */
    protected List<NewsRequestContentItem> articles;

    public NewsRequestContent(NewsRequestContentItem..._articles) {
        this.articles = Arrays.asList(_articles);
    }
}
