package cn.edu.jxau.web.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zclong
 * @since 2019/2/3
 */
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO implements Serializable {
    private static final long serialVersionUID = 7624373420954455072L;

    private String id;

    private String name;

    private String password;
}
