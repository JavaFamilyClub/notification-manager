package club.javafamily.nf.request;

import club.javafamily.nf.request.news.NewsRequestContent;
import club.javafamily.nf.request.news.NewsRequestContentItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 图文消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QyWechatNewsNotifyRequest extends QyWechatNotifyRequest {

   private NewsRequestContent news;

   public QyWechatNewsNotifyRequest() {
      super("news");
   }

   public QyWechatNewsNotifyRequest(NewsRequestContent content) {
      this();
      this.news = content;
   }

   /**
    * 构造器
    * @param title 图文消息-标题: 不超过128个字节，超过会自动截断
    * @param description 图文消息-描述: 不超过512个字节，超过会自动截断
    * @param url 图文消息-跳转链接
    * @param picurl 图文消息-图片地址
    * @return QyWechatNotifyRequest
    */
   public static QyWechatNewsNotifyRequest of(String title, String description, String url, String picurl) {
      NewsRequestContentItem item = new NewsRequestContentItem(title, description, url, picurl);
      final NewsRequestContent textContent = new NewsRequestContent(item);

      final QyWechatNewsNotifyRequest request = new QyWechatNewsNotifyRequest();
      request.setNews(textContent);

      return request;
   }

}
