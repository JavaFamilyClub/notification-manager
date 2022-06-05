package club.javafamily.nf.request.tags;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:41
 * @description
 */
public interface TagContentItem extends Serializable {

   /**
    * 标签名
    * @return 标签名
    */
   String getTag();

}
