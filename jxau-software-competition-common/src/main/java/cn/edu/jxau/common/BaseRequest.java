package cn.edu.jxau.common;

import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author：zclong
 * @date：2019/2/2
 */
@ToString
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 7101306582413617228L;

    private static final Integer DEFAULT_PAGE_SIZE = 20;

    private static final Integer DEFAULT_FIRST_PAGE = 1;

    @Setter
    public Integer pageSize;

    @Setter
    public Integer currentPage;

    public BaseRequest() {

    }

    public Integer getPageSize() {
        if (this.pageSize == null) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        return this.pageSize;
    }

    public Integer getCurrentPage() {
        if (this.currentPage == null) {
            this.currentPage = DEFAULT_FIRST_PAGE;
        }
        return this.currentPage;
    }

    public Integer getPageFirstItem() {
        int cPage = this.getCurrentPage();
        if (cPage == 1) {
            return 0;
        } else {
            --cPage;
            int pgSize = this.getPageSize();
            return pgSize * cPage;
        }
    }
}
