package club.javafamily.nf.properties;

import club.javafamily.nf.enums.SecurityConfTypeEnum;
import lombok.Data;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:42
 * @description 飞书通知属性配置
 */
@Data
public class WebHookProperties {
    /**
     * 是否启用通知
     */
    protected Boolean enabled = true;

    /**
     * WehbHook url
     */
    protected String hookUrl;

    /**
     * 安全设置
     */
    protected SecurityConfTypeEnum securityType = SecurityConfTypeEnum.KEYWORD;

    /**
     * 关键词
     */
    protected String keyWord;

    /**
     * 启用抑制
     */
    protected Inhibit inhibit = new Inhibit();
}
