package com.smartbuy.Smart.Buy.dao;

import com.smartbuy.Smart.Buy.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

    EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        String users = "From User";
        return entityManager.createQuery(users).getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void createUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User obtainUserCredentials(User user) {
        String query = "FROM User WHERE email = :email OR username = :username";
        List<User> userResult = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .setParameter("username", user.getUsername())
                .getResultList();

        if (userResult.isEmpty()){
            return null;
        }

        String passwordHashed = userResult.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, user.getPassword())){
            return userResult.get(0);
        }
        return null;
    }
}
