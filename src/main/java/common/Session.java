package common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created on 2019/6/28
 *
 * @author qing.huang
 */
@Data
@AllArgsConstructor
public class Session {

    private String userId;

    private String username;

}
