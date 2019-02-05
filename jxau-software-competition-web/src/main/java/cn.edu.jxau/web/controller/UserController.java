package cn.edu.jxau.web.controller;

import cn.edu.jxau.service.IUserService;
import cn.edu.jxau.service.bo.UserBO;
import cn.edu.jxau.service.bo.UserQueryBO;
import cn.edu.jxau.web.VO.UserVO;
import cn.edu.jxau.web.VO.convert.UserVConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author zclong
 * @date 2018/11/03
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/test")
    public String test(Model model) {
        UserQueryBO userQueryBO = UserQueryBO.builder().password("123456").build();
        List<UserBO> userBOList = userService.queryUserListByPage(userQueryBO);
        if (userBOList == null || userBOList.size() <= 0) {
            log.info("userBOList is null");
        }
        List<UserVO> userVOList = UserVConvert.INSTANCE.doListToVOList(userBOList);
        model.addAttribute("users", userVOList);
        log.info("user/test");
        return "user";
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("now", new Date());
        return "index";
    }
}
