package common;

import io.netty.util.AttributeKey;

/**
 * Created on 2019/6/27
 *
 * @author qing.huang
 */
public interface Attribute {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
