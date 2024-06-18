package com.techpixe;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public String signup(String username, String password, String email) {
        // Check if username is already taken
        if (userRepository.findByUsername(username) != null) {
            return "Username already exists";
        }
        
        // Create new user
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // Note: In real-world scenarios, use password hashing (e.g., bcrypt)
        newUser.setEmail(email);
        
        userRepository.save(newUser);
        
        return "User registered successfully";
    }
    
    @Override
    public String login(String username, String password) {
        // Simulate login process (validate credentials)
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return "Invalid username or password";
        }
        
        return "Login successful";
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
