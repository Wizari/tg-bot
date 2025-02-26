package com.gmail.wizaripost.tgbot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data // Генерирует геттеры, сеттеры, toString, equals и hashCode
@NoArgsConstructor // Генерирует конструктор по умолчанию
@AllArgsConstructor // Генерирует конструктор со всеми полями
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "telegramId", nullable = false)
    private Long telegramId;

    // Переопределяем equals и hashCode для корректной работы с JPA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(1756406093);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations = new ArrayList<>();

    // Метод для добавления локации
    public void addLocation(Location location) {
        locations.add(location);
        location.setUser(this);
    }

    // Метод для удаления локации
    public void removeLocation(Location location) {
        locations.remove(location);
        location.setUser(null);
    }
}