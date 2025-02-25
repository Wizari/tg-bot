package com.gmail.wizaripost.tgbot.db.repository;

import com.gmail.wizaripost.tgbot.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByTelegramId(Long telegramId);

}
