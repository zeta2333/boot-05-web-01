package usts.pycro.boot.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-11 6:33 PM
 */
@RestController
public class HelloController {
    @RequestMapping("/wind.jpg")
    public String hello(){
        return "aaaa";
    }
    @RequestMapping("index")
    public String index(){
        return "123";
    }

    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE-Pycro";
    }

    @PutMapping("/user")
    public String putUser(){
        return "PUT-Pycro";
    }

    @PostMapping("/user")
    public String postUser(){
        return "POST-Pycro";
    }

    @GetMapping("/user")
    public String getUser(){
        return "GET-Pycro";
    }

    //扩展点：如何把_method改成自定义的名称
}
