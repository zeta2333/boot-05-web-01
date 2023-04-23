package usts.pycro.boot.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import usts.pycro.boot.bean.Person;

import java.util.Date;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-23 10:12 AM
 */
@Controller
public class ResponseTestController {

    @ResponseBody //--RequestResponseBodyMethodProcessor ---> messageConverter
    @GetMapping("/fileTest")
    public FileSystemResource file(){
        //文件以这样的方式返回，看是谁处理的（messageConverter）
        return null;
    }

    @GetMapping("/test/person")
    @ResponseBody
    public Person getPerson() {
        Person person = new Person();
        person.setAge(23);
        person.setBirth(new Date());
        person.setUserName("Pycro");
        return person;
    }
}
