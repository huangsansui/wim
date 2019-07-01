package common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019/6/28
 *
 * @author qing.huang
 */
public class UserInfoUtil {

    private static Map<String, String> users = new HashMap<>();

    static {

        users.put("sansui", "316hq8239880");
        users.put("huangqing", "316hq8239880");
        users.put("yangyang", "123456");
    }

    public static String getPassword(String username) {
        return users.get(username);
    }
}
