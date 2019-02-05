package cn.edu.jxau.service.bo;

import cn.edu.jxau.common.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author：zclong
 * @date：2019/2/2
 */
@ToString
@Data
@Builder
public class UserQueryBO extends BaseRequest {
    private static final long serialVersionUID = 1642597362286850161L;

    private String name;

    private String password;

    private Date effectiveFrom;

    private Date effectiveTo;

}
