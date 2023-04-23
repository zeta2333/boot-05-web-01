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

    /**
     * 1、浏览器发请求直接返回 xml   [application/xmL]    jacksonXmlConverter
     * 2、如果是ajax请求返回  json  [ application/json]  jacksonJsonConverter
     * 3、如果某app发请求，返回自定义协议数据[application/x-pycro] xxxConverter
     *      属性值1;属性值2;
     * 步骤:
     * 1、添加自定义的MessageConverter进系统底层
     * 2、系统底层就会统计出所有MessageConverter能操作哪些类型
     * 3、客户端内容协商[pycro--->pycro]
     * @return
     */
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
