package com.smartbuy.Smart.Buy.controllers;

import com.smartbuy.Smart.Buy.dao.UserDao;
import com.smartbuy.Smart.Buy.models.User;
import com.smartbuy.Smart.Buy.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User userLogged = userDao.obtainUserCredentials(user);
        if (userLogged != null) {
            String tokenJwt =jwtUtil.create(String.valueOf(userLogged.getId()), userLogged.getEmail());
            return tokenJwt;
        }
        return "Authentication error";
    }
}
