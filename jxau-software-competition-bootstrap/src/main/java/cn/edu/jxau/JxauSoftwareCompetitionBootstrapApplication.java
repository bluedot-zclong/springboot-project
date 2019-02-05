package cn.edu.jxau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "cn.edu.jxau"
})
@Controller
@ServletComponentScan
public class JxauSoftwareCompetitionBootstrapApplication {

    @GetMapping("/health")
    public @ResponseBody
    String health() {
        return "success";
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("now", new Date());
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(JxauSoftwareCompetitionBootstrapApplication.class, args);
    }

}

