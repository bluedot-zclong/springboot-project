package cn.edu.jxau.dao.dataobject;

/**
 * 区域标识
 *
 * @author：zclong
 * @date：2019/2/1
 */
public class BaseRegion {
    private final String id;

    public BaseRegion(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
