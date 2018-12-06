package com.faisaljarkass.demo.services;

import com.faisaljarkass.demo.domains.MyUser;
import com.faisaljarkass.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private List<MyUser> listOfUsers = new ArrayList<>();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        setupDummyUsers();
    }

    @Override
    public MyUser getUser(String username, String password) {
        //Java 8 way using Lambda
        final MyUser user = listOfUsers.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findAny()
                .orElse(null);

        //return user;
        return null;
    }

    private void setupDummyUsers() {
        String[] arrRole = {"user", "admin"};
        MyUser user = new MyUser(1l, "test", "test", arrRole);
        listOfUsers.add(user);
        String[] arrRole2 = {"user"};
        MyUser user2 = new MyUser(2l, "test2", "test2", arrRole2);
        listOfUsers.add(user2);
    }

}
