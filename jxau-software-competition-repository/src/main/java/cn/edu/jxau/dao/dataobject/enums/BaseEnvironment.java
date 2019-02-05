package cn.edu.jxau.dao.dataobject.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 环境标识
 *
 * @author：zclong
 * @date：2019/2/1
 */
public enum BaseEnvironment {

    DEV("dev"),

    DAILY("daily"),

    STAGING("staging"),

    PROD("prod");

    private String name;

    private BaseEnvironment(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
