package com.yinseo.turbineInsightWeb.service;

import com.yinseo.turbineInsightWeb.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(String userId);
}
