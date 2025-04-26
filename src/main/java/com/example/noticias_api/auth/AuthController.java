package com.example.noticias_api.auth;

import com.example.noticias_api.model.User;
import com.example.noticias_api.repository.UserRepository;
import com.example.noticias_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl uds;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthController(AuthenticationManager am, JwtUtil ju, UserDetailsServiceImpl uds,
                          UserRepository ur, PasswordEncoder pe, UserService us) {
        this.authManager = am;
        this.jwtUtil = ju;
        this.uds = uds;
        this.userRepo = ur;
        this.passwordEncoder = pe;
        this.userService = us;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest loginData) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginData.getUsername(), loginData.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }

        UserDetails userDetails = uds.loadUserByUsername(loginData.getUsername());
        Optional<User> userOptional = userRepo.findByUsername(loginData.getUsername());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        String role = userOptional.get().getRole();
        String token = jwtUtil.generateToken(userDetails.getUsername(), role);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", role);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest user) {
        try {
            userService.registerUser(user.getUsername(), user.getPassword(), user.getRole());
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao registrar usuário: " + e.getMessage());
        }
    }
}
