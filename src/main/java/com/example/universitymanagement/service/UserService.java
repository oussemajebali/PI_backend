package com.example.universitymanagement.service;

import java.util.List;

import com.example.universitymanagement.entity.UserUpdate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.universitymanagement.entity.User;
import com.example.universitymanagement.repository.UserRepository;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int Id) {
        return userRepository.findById(Id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
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
