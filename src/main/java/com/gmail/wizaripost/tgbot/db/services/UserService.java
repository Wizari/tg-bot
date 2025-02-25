package com.gmail.wizaripost.tgbot.db.services;


import com.gmail.wizaripost.tgbot.db.repository.UserRepository;
import com.gmail.wizaripost.tgbot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    public boolean haveUserByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId).isPresent();
    }

    public User getUserByTelegramId(Long telegramId) {
        return userRepository.findByTelegramId(telegramId).orElse(null);
    }

//    public boolean haveUserByTelegramId(Long telegramId) {
//        for (User user : userRepository.findAll()) {
//            if (telegramId.equals(user.getTelegramId())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public User getUserByTelegramId(Long telegramId) {
//        for (User user : userRepository.findAll()) {
//            if (telegramId.equals(user.getTelegramId())) {
//                return user;
//            }
//        }
//        return null;
//    }
}
