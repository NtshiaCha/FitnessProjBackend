package com.ex.services;

import com.ex.dao.UserDAO;
import com.ex.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Scope("prototype")
@Transactional(
        isolation = Isolation.REPEATABLE_READ,
        propagation = Propagation.REQUIRED
)
public class UserService {
    UserDAO dao;

    @Autowired
    public void setDao(UserDAO dao){
        this.dao = dao;
    }

    /*
    Basic DAO methods
     */
    public String authenticate(String userName, String password){
        User checkUser = dao.getByUsername(userName);
        if(password.equals(checkUser.getPassword())){
            return "/home";
        }
        return "/login";
    }

    public User getById(int i){
        return dao.getById(i);
    }
    public User getByUsername(String username){ return dao.getByUsername(username);}
    public User getByEmail(String email){
        return dao.getByEmail(email);
    }
    public User getById(Integer i){
        return (User)dao.getById(i);
    }
    public List<User> getAll(){
        return (ArrayList<User>)dao.getAll();
    }
    public int save(User user){
        return (int)dao.save(user);
    }
    void delete(User user){
        dao.delete(user);
    }
    void update(User user){
        dao.update(user);
    }
}
