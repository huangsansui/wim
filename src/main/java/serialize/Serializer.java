package serialize;

import serialize.impl.JSONSerializer;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转化成二进制
     *
     * @param object
     * @return
     */
    byte[] serializer(Object object);

    /**
     * 二进制转换为java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserializer(Class<T> clazz, byte[] bytes);
}
