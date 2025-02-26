package com.gmail.wizaripost.tgbot.db.repository;

import com.gmail.wizaripost.tgbot.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByTelegramId(Long telegramId);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.locations WHERE u.telegramId = :telegramId")
    User findByTelegramIdWithLocations(@Param("telegramId") Long telegramId);

}
