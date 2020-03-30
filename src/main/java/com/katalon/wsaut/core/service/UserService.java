package com.katalon.wsaut.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.katalon.wsaut.core.entity.Gender;
import com.katalon.wsaut.core.entity.User;
import com.katalon.wsaut.core.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getById(long id) {
        return userRepository.findOne(id);
    }
    
    public User getByBodyContent(User user) {
    	
        return userRepository.findOne(user.getId());
    }

    public List<User> list(Gender gender, Integer age) {
        if (gender == null && age == null) {
            return userRepository.findAll();
        } else if (gender != null) {
            return userRepository.findByGender(gender);
        } else if (age != null) {
            return userRepository.findByAgeGreaterThanEqual(age);
        } else {
            return userRepository.findByGenderAndAgeGreaterThanEqual(gender, age);
        }
    }

    public User create() {
        User newUser = createNewUser("","" , Gender.UNKNOWN, 0, null);
        return userRepository.save(newUser);
    }
    
    public User create(User user) {
        User newUser = createNewUser(
                user.getUsername(),
                user.getPassword(),
                user.getGender(),
                user.getAge(),
                user.getAvatar()
        );
        return userRepository.save(newUser);
    }

    public User create(String username,
                       String password,
                       Gender gender,
                       int age,
                       byte[] avatar) {
        User newUser = createNewUser(username, password, gender, age, avatar);
        return userRepository.save(newUser);
    }

    private User createNewUser(String username,
                               String password,
                               Gender gender,
                               int age,
                               byte[] avatar) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setGender(gender);
        newUser.setAge(age);
        newUser.setAvatar(avatar);

        return newUser;
    }

//    public User update(User user) {
//        return userRepository.save(user);
//    }

    public void delete(long id) {
        userRepository.delete(id);
    }

    public User updateAge(long id, int age) {
        User user = userRepository.findOne(id);
        if (user != null) {
            user.setAge(age);
            return userRepository.save(user);
        }
        return null;
    }
}
