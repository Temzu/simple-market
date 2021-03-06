package com.temzu.market.repositories;

import com.temzu.market.model.entities.Role;
import com.temzu.market.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
