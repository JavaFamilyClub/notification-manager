package club.javafamily.nf.request;

import club.javafamily.nf.request.content.ContentRequestContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QyWechatMarkdownNotifyRequest extends QyWechatNotifyRequest {

   private ContentRequestContent markdown;

   public QyWechatMarkdownNotifyRequest() {
      super("markdown");
   }

   public QyWechatMarkdownNotifyRequest(ContentRequestContent content) {
      this();
      this.markdown = content;
   }

   /**
    * 构造器
    * @param content markdown内容，最长不超过4096个字节，必须是utf8编码
    * @return QyWechatMarkdownNotifyRequest
    */
   public static QyWechatMarkdownNotifyRequest of(String content) {
      final ContentRequestContent textContent = new ContentRequestContent(content);

      final QyWechatMarkdownNotifyRequest request = new QyWechatMarkdownNotifyRequest();
      request.setMarkdown(textContent);

      return request;
   }

}
