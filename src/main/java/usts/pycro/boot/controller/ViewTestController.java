package usts.pycro.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-23 10:43 PM
 */
@Controller
public class ViewTestController {
    @GetMapping("/pycro")
    public String pycro(Model model){
        //model中添加属性，会被共享到请求域中
        model.addAttribute("msg","hello, thymeleaf");
        model.addAttribute("link","https://www.bilibili.com");
        return "success";
    }
}
