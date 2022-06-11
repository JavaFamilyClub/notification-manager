package club.javafamily.nf.request.tags;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description @ 人员配置
 */
@Data
public class CardBtn implements Serializable {
    /**
     * Button 文本
     */
    private String title;

    /**
     * Button 链接
     */
    private String actionURL;
}
