package usts.pycro.boot.controller;

import org.springframework.web.bind.annotation.*;
import usts.pycro.boot.bean.Person;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-17 8:53 AM
 */
@RestController
public class ParameterTestController {

    /**
     * 数据绑定：页面提交的请求数据（GET、POST）都可以和对象属性进行绑定
     * @param person
     * @return
     */
    @PostMapping("/saveUser")
    public Person saveUser(Person person) {
        return person;
    }

    //   /car/2/owner/李四
    @GetMapping("/car/{id}/owner/{username}")
    public Map<?, ?> testPathVariable(
            @PathVariable("id") Integer id,
            @PathVariable("username") String username,
            @PathVariable Map<String, String> pv,
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader Map<String, String> headers,
            @RequestParam("age") Integer age,
            @RequestParam("interest") List<String> interest,
            @RequestParam Map<String, Object> params,
            @CookieValue("name") String name,
            @CookieValue("name") Cookie cookie
    ) {
        Map<String, Object> map = new HashMap<>();
        //map.put("id", id);
        //map.put("name", name);
        //map.put("pv", pv);
        //map.put("userAgent", userAgent);
        //map.put("headers", headers);
        //map.put("age", age);
        //map.put("interest", interest);
        //map.put("params", params);
        map.put("name", name);
        System.out.println(cookie.getName() + " ===> " + cookie.getValue());
        return map;
    }

    @PostMapping("/save")
    public Map<?, ?> testPost(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    //1.语法 /cars/sell;low=34;brand=byd,audi,yd
    //2.SpringBoot默认是禁用了矩阵变量的功能
    //  手动开启：对于路径的处理，用urlPathHelper进行解析
    //  removeSemicolonContent（移除分号内容）与矩阵变量相关，默认为true
    //3.矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map<?, ?> carsSell(
            @MatrixVariable("low") Integer low,
            @MatrixVariable("brand") List<String> brand,
            @PathVariable("path") String path
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    //  /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map<?, ?> boss(
            @MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
            @MatrixVariable(value = "age", pathVar = "empId") Integer empAge,
            @PathVariable("bossId") Integer bossId,
            @PathVariable("empId") Integer empId
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        map.put("bossId", bossId);
        map.put("empId", empId);
        return map;
    }
}
