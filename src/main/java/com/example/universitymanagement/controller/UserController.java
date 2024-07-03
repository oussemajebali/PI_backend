package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.ChangePasswordRequest;
import com.example.universitymanagement.entity.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.universitymanagement.entity.User;
import com.example.universitymanagement.service.UserService;

import java.security.Principal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/adduser")
    public User createUser(@RequestParam("name") String name,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("age") int age,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("role") String role,
                           @RequestParam("avatar") MultipartFile avatar) {
        return userService.saveUser(name, lastName, age, email, password, role, avatar);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updatePersonalData(@PathVariable int id,@RequestBody UserUpdate updatedUser) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(userService.updateUserData(user.getEmail(), updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
