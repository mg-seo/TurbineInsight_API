package com.yinseo.turbineInsightWeb.repository;

import com.yinseo.turbineInsightWeb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

