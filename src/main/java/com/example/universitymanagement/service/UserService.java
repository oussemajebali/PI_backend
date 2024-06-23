package com.example.universitymanagement.service;

import java.security.Principal;
import java.util.List;

import com.example.universitymanagement.entity.ChangePasswordRequest;
import com.example.universitymanagement.entity.Role;
import com.example.universitymanagement.entity.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.universitymanagement.entity.User;
import com.example.universitymanagement.repository.UserRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final String AVATAR_DIR = "avatars/";

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int Id) {
        return userRepository.findById(Id).orElse(null);
    }

    public User saveUser(String name, String lastName, int age, String email, String password, String role, MultipartFile avatar) {
        String avatarPath = saveAvatarFile(avatar);
        User user = User.builder()
                .name(name)
                .lastName(lastName)
                .age(age)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.valueOf(role))
                .avatar(avatarPath)
                .build();
        return userRepository.save(user);
    }

    private String saveAvatarFile(MultipartFile avatar) {
        if (avatar.isEmpty()) {
            return null;
        }

        try {
            byte[] bytes = avatar.getBytes();
            Path path = Paths.get(AVATAR_DIR + avatar.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store avatar file", e);
        }
    }

    public void deleteUser(int Id) {
        userRepository.deleteById(Id);
    }

    public User updateUserData(String email, UserUpdate updatedUser) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setName(StringUtils.hasText(updatedUser.getName()) ? updatedUser.getName() : user.getName());
        user.setAge(updatedUser.getAge() > 0 ? updatedUser.getAge() : user.getAge());
        user.setLastName(StringUtils.hasText(updatedUser.getLastName()) ? updatedUser.getLastName() : user.getLastName());

        // Update fields as necessary
        return userRepository.save(user);
    }
}
