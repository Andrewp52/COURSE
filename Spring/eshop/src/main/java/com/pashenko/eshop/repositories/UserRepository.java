package com.pashenko.eshop.repositories;

import com.pashenko.eshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
    User getFirstByLogin(String login);
}
