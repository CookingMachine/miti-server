package com.miti.server.check;

public class UserChecker {
    public boolean userChecker(String userName, String password) {
        if ((userName != null && !userName.isEmpty())
                && (password != null && !password.isEmpty()))
            return true;
        return false;
    }
}
