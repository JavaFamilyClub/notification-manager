package club.javafamily.nf.request;

import lombok.Data;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@Data
public abstract class BaseFeiShuNotifyRequest implements NotifyRequest {

   /**
    * 消息类型
    * @return
    */
   public abstract String getMsg_type();
}
