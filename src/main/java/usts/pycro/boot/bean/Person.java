package usts.pycro.boot.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-17 6:09 PM
 */
@Data
public class Person {
    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;
}
