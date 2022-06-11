package club.javafamily.nf.request.markdown;

import club.javafamily.nf.request.DingTalkNotifyRequest;
import club.javafamily.nf.request.content.TitleTextRequestContent;
import club.javafamily.nf.request.link.LinkRequestContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:35
 * @description MarkDown 消息
 * @warn @成员必须在text内容里要有@人的手机号
 *
 * 目前的 MarkDown 之支持如下语法:
 * <code>
 * 标题
 * # 一级标题
 * ## 二级标题
 * ### 三级标题
 * #### 四级标题
 * ##### 五级标题
 * ###### 六级标题
 *
 * 引用
 * > A man who stands for nothing will fall for anything.
 *
 * 文字加粗、斜体
 * **bold**
 * *italic*
 *
 * 链接
 * [this is a link](http://name.com)
 *
 * 图片
 * ![](http://name.com/pic.jpg)
 *
 * 无序列表
 * - item1
 * - item2
 *
 * 有序列表
 * 1. item1
 * 2. item2
 * </code>
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DingTalkMarkDownRequest extends DingTalkNotifyRequest {

    private TitleTextRequestContent markdown;

    public static DingTalkMarkDownRequest of(String title, String content, boolean atAll) {
        return DingTalkMarkDownRequest.of(title, content, atAll, null);
    }

    public static DingTalkMarkDownRequest of(String title, String content,
                                             boolean atAll, String...atUserPhones)
    {
        final TitleTextRequestContent textContent
                = new TitleTextRequestContent(title, content);

        final DingTalkMarkDownRequest request = new DingTalkMarkDownRequest();
        request.setMarkdown(textContent);
        request.buildAtConf(atAll, atUserPhones);

        return request;
    }

    @Override
    public String getMsgtype() {
        return "markdown";
    }

}