package com.gmail.wizaripost.tgbot.db.repository;

import com.gmail.wizaripost.tgbot.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
