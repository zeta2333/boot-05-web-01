package usts.pycro.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-17 9:45 AM
 */
@Controller
public class RequestController {
    @GetMapping("/goto")
    public String gotoPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功了...");
        request.setAttribute("code", 200);
        return "forward:/success"; //转发到 /success 请求
    }

    @GetMapping("/success")
    @ResponseBody
    public Map<?, ?> success(
            @RequestAttribute(value = "msg",required = false) String msg,
            @RequestAttribute(value = "code",required = false) Integer code,
            HttpServletRequest request
    ) {
        Map<String, Object> map = new HashMap<>();
        Object msg1 = request.getAttribute("msg");
        Object code1 = request.getAttribute("code");
        map.put("reqMethod_msg", msg1);
        map.put("annotation_msg", msg);
        map.put("reqMethod_code", code1);
        map.put("annotation_code", code);
        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");
        map.put("hello",hello);
        map.put("world",world);
        map.put("message",message);
        return map;
    }

    @GetMapping("/params")
    public String testParam(
            Map<String, Object> map,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        map.put("hello", "world666");
        model.addAttribute("world", "hello666");
        request.setAttribute("message", "HelloWorld");
        Cookie cookie = new Cookie("c1", "v1");
        //cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/success";
    }
}
