package com.mysql.redis.repository;

import com.mysql.redis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
