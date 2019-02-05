package cn.edu.jxau.web.controller.base;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zclong
 * @since 2019/2/3
 */
@Slf4j
public abstract class BaseController {

    /**
     * 获取用户id
     *
     * @param request
     * @return
     */
    protected String getUserId(HttpServletRequest request) {
        return "";
    }
}
