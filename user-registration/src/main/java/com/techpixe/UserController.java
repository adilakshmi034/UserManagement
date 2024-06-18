package com.techpixe;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestParam(name="username",required=false) String username,
			@RequestParam(name="password",required=false) String password, @RequestParam(name="email",required=false) String email) {
		String message = userService.signup(username, password, email);
		return ResponseEntity.ok(message);
	}

	// Endpoint for user login
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		String message = userService.login(username, password);
		return ResponseEntity.ok(message);
	}
	 @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable Long id) {
	        Optional<User> user = userService.getUserById(id);
	        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
}
