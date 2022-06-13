package club.javafamily.nf.service;

import club.javafamily.nf.enums.NotifySupportTypeEnum;
import club.javafamily.nf.request.NotifyRequest;

import java.util.EnumSet;

/**
 * @author Jack Li
 * @date 2022/6/12 下午6:20
 * @description 通知处理器
 */
public interface NotifyHandler<NR extends NotifyRequest, RESPONSE> {

    /**
     * 是否通过该 Handler 进行通知
     * @param param
     * @return
     */
    default boolean isAccept(Object param) {
        if(param instanceof NotifySupportTypeEnum) {
            return selfType() == param;
        }
        else if(param instanceof EnumSet) {
            return ((EnumSet<?>) param).contains(selfType());
        }

        return false;
    }

    /**
     * 自身的类型
     * @return NotifySupportTypeEnum
     */
    NotifySupportTypeEnum selfType();

    /**
     * 通知
     * @param request 请求参数
     */
    RESPONSE notify(NR request) throws Exception;
}
