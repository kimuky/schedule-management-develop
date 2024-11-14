package com.example.schedulemanagementdevelop.repository;

import com.example.schedulemanagementdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
