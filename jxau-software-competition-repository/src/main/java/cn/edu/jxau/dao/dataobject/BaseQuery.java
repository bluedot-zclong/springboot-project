package cn.edu.jxau.dao.dataobject;

import lombok.*;

import java.io.Serializable;

/**
 * @author：zclong
 * @date：2019/2/1
 */
@ToString
@AllArgsConstructor
public class BaseQuery implements Serializable {
    private static final long serialVersionUID = -3815754274049938418L;

    private static final Integer DEFAULT_PAGE_SIZE = 20;

    private static final Integer DEFAULT_FIRST_PAGE = 1;

    @Getter
    @Setter
    private String ownSign;

    @Setter
    public Integer pageSize;

    @Setter
    public Integer currentPage;

    public BaseQuery() {

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
