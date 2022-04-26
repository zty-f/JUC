package com.zty.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version V1.0
 * @ClassName: com.zty.stream.User.java
 * @Copyright swpu
 * @author: zty-f
 * @date: 2022-04-26 19:40
 * @Description: 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private int age;
}
