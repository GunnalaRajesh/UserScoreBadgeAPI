package com.example.CustomerAPI.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.CustomerAPI.entity.User;
import com.example.CustomerAPI.repository.UserRepository;

@RestController
@RequestMapping("/leaderboard")
public class UserController {
    

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByScoreDesc();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody User user) {
        if (user.getUserId() == null || user.getUsername() == null) {
            throw new InvalidRequestException("User ID and Username are required");
        }
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    @PutMapping("/{userId}")
    public User updateUserScore(@PathVariable String userId, @RequestParam int score) {
        if (score < 0 || score > 100) {
            throw new InvalidRequestException("Score must be between 0 and 100");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setScore(score);
        user.setBadges(assignBadges(score));
        return userRepository.save(user);
    }


    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }


    private Set<String> assignBadges(int score) {
        Set<String> badges = new HashSet<>();
        if (score >= 1 && score < 30) {
            badges.add("Code Ninja");
        }
        if (score >= 30 && score < 60) {
            badges.add("Code Champ");
        }
        if (score >= 60) {
            badges.add("Code Master");
        }
        return badges;
    }
}



@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
