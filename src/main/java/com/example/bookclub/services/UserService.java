package com.example.bookclub.services;

import com.example.bookclub.models.LoginUser;
import com.example.bookclub.models.User;
import com.example.bookclub.repositories.UserRepository;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User register(User newUser, BindingResult result) {
        Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
        if (optionalUser.isPresent()){
            result.rejectValue("email", "unique", "Email is already registered");
        }
        if (!newUser.getPassword().equals(newUser.getConfirm())){
            result.rejectValue("confirm", "matches", "Passwords do not match");
        }
        if (result.hasErrors()){
            return null;
        }
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        return userRepo.save(newUser);
    }
    public User login(LoginUser newLogin, BindingResult result) {
        Optional<User> optionalUser = userRepo.findByEmail(newLogin.getEmail());
        if(!optionalUser.isPresent()){
            result.rejectValue("email", "unique", "Please register first");
        }
        if(result.hasErrors()){
            return null;
        }
        User user = optionalUser.get();
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())){
            result.rejectValue("password", "Matches", "Invalid Password");
        }
        if (result.hasErrors()){
            return null;
        }
        return user;
    }
}
