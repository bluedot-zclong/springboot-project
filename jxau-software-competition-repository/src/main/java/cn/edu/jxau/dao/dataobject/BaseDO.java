package cn.edu.jxau.dao.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author：zclong
 * @date：2019/2/1
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseDO implements Serializable {
    private static final long serialVersionUID = -3643338773447144228L;

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String ownSign;

    private String regionSign;
}
