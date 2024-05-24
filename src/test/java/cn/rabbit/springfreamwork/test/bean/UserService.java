package cn.rabbit.springfreamwork.test.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserService implements IUserService {


    private String name;

    public void queryUserInfo(){
        System.out.println("查询用户信息:"+name);
    }

    @Override
    public void sayHello() {
        System.out.println("hello world!");
    }
}
