package com.smartbuy.Smart.Buy.controllers;

import com.smartbuy.Smart.Buy.dao.UserDao;
import com.smartbuy.Smart.Buy.models.User;
import com.smartbuy.Smart.Buy.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validateToken(String token) {
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

    @RequestMapping(value="users")
    public List<User> getUsers(@RequestHeader(value="Authorization") String token) {
        if (!validateToken(token)) {return null;}
        return userDao.getUsers();
    }

    @RequestMapping(value="users", method= RequestMethod.POST)
    public void createUser(@RequestBody User user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userDao.createUser(user);
    }

    @RequestMapping(value="users/{id}", method=RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id) {
        if (!validateToken(token)) {return;}
        userDao.deleteUser(id);
    }
}
