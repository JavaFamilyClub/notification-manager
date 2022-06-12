package club.javafamily.nf.sms.tencent.enums;

/**
 * @author Jack Li
 * @date 2022/6/13 上午1:15
 * @description
 */
public enum ResourceTypeEnum {

   /**
    * 本地文件. {@link java.io.File}
    */
   LOCALE_FILE,

   /**
    * 本地文件的路径. {@link String}
    */
   LOCALE_FILE_PATH,

   /**
    * url. {@link String}
    */
   URL,

   /**
    * 流. {@link java.io.InputStream}
    */
   STREAM,

   /**
    * Byte array. {@link Byte[]}
    */
   BYTE_ARRAY

}
