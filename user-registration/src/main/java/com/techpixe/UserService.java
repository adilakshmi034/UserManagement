package com.techpixe;

import java.util.Optional;

public interface UserService {
    String signup(String username, String password, String email);
    String login(String username, String password);
	Optional<User> getUserById(Long id);
}

