package com.yinseo.turbineInsightWeb.controller;

import com.yinseo.turbineInsightWeb.entity.User;
import com.yinseo.turbineInsightWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class BusinessController {

    private final UserService userService;

    @Autowired
    public BusinessController(UserService userService) {
        this.userService = userService;
    }

    // ID 존재 확인 (로그인 용도)
    @PostMapping("/checkUserId")
    public ResponseEntity<String> checkUserId(@RequestBody String userId) {
        Optional<User> user = userService.getUserById(userId);

        if (user.isPresent()) {
            return new ResponseEntity<>("OK", HttpStatus.OK);  // ID가 존재하면 OK 응답
        } else {
            return new ResponseEntity<>("User ID not found", HttpStatus.NOT_FOUND);  // 존재하지 않으면 404
        }
    }

}
