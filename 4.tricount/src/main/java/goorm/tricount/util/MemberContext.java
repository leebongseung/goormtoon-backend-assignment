package goorm.tricount.util;

import goorm.tricount.domain.entity.user.Users;

public class MemberContext {
    private static final ThreadLocal<Users> usersThreadLocal = new ThreadLocal<>();

    public static void setCurrentUsers(Users users){
        usersThreadLocal.set(users);
    }

    public static Users getCurrentUsers(Users users){
        return usersThreadLocal.get();
    }
}
