package com.example.universitymanagement.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.universitymanagement.entity.ChangePasswordRequest;
import com.example.universitymanagement.entity.Role;
import com.example.universitymanagement.entity.User;
import com.example.universitymanagement.entity.UserUpdate;
import com.example.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Cloudinary cloudinary;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(String name, String lastName, int age, String email, String password, String role, MultipartFile avatar) {
        String avatarUrl = uploadAvatarToCloudinary(avatar);
        User user = User.builder()
                .name(name)
                .lastName(lastName)
                .age(age)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.valueOf(role))
                .avatar(avatarUrl)
                .build();
        return userRepository.save(user);
    }

    private String uploadAvatarToCloudinary(MultipartFile avatar) {
        if (avatar.isEmpty()) {
            return null;
        }
        try {
            Map uploadResult = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload avatar to Cloudinary", e);
        }
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User updateUserData(String email, UserUpdate updatedUser) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setName(StringUtils.hasText(updatedUser.getName()) ? updatedUser.getName() : user.getName());
        user.setAge(updatedUser.getAge() > 0 ? updatedUser.getAge() : user.getAge());
        user.setLastName(StringUtils.hasText(updatedUser.getLastName()) ? updatedUser.getLastName() : user.getLastName());

        return userRepository.save(user);
    }
}
