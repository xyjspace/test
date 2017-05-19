package entity;

import lombok.Data;

/**
 * Created by hasee on 2017/5/19.
 */
@Data
public class User {

    String name;

    String account;

    String password;

    /**
     * 用户身份，0为会员，1为普通用户。
     */
    String identity;

    String balance;

}
