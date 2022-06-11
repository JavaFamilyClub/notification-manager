package club.javafamily.nf.request;

import club.javafamily.nf.request.tags.AtTag;
import lombok.Data;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description
 */
@Data
public abstract class DingTalkNotifyRequest implements NotifyRequest {

   /**
    * 消息类型
    * @return
    */
   public abstract String getMsgtype();

   /**
    * @ 人员配置
    */
   protected AtTag at;

   /**
    * 构造 at 配置
    */
   public void buildAtConf(boolean atAll, String[] atUserPhones) {
      at = new AtTag();
      at.setAtAll(atAll);
      at.setAtMobiles(atUserPhones);
   }
}
