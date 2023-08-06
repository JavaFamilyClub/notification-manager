package club.javafamily.nf.request;

import lombok.Data;

/**
 * @author Jack Li
 * @date 2023/8/5 下午10:51
 * @description 请求消息实体
 */
@Data
public abstract class QyWechatNotifyRequest implements NotifyRequest {

   private static final long serialVersionUID = 1L;

   protected String msgtype;

   public QyWechatNotifyRequest() {
   }

   public QyWechatNotifyRequest(String msgtype) {
      this.msgtype = msgtype;
   }

   /**
    * 消息类型
    * @return
    */
   public String getMsgtype() {
      return msgtype;
   }
}
