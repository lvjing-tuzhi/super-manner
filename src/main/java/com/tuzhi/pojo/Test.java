package com.tuzhi.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**测试lombok
 * @program: superManager
 * @description:
 * @author: 兔子
 * @create: 2021-11-14 22:39
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private String name;
    private String address;
    private String age;
}
