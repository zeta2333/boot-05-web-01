package usts.pycro.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
            @RequestAttribute("msg") String msg,
            @RequestAttribute("code") Integer code,
            HttpServletRequest request
    ) {
        Map<String, Object> map = new HashMap<>();
        Object msg1 = request.getAttribute("msg");
        Object code1 = request.getAttribute("code");
        map.put("reqMethod_msg", msg1);
        map.put("annotation_msg", msg);
        map.put("reqMethod_code", code1);
        map.put("annotation_code", code);
        return map;
    }
}
