package com.floodguard.config;

import com.floodguard.entity.User;
import com.floodguard.enums.Role;
import com.floodguard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seed-admin.enabled:false}")
    private boolean seedAdminEnabled;

    @Value("${app.seed-admin.username:}")
    private String adminUsername;

    @Value("${app.seed-admin.email:}")
    private String adminEmail;

    @Value("${app.seed-admin.password:}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        if (!seedAdminEnabled) {
            return;
        }

        if (adminUsername == null || adminUsername.isBlank() ||
                adminEmail == null || adminEmail.isBlank() ||
                adminPassword == null || adminPassword.isBlank()) {
            log.warn("Admin seeding skipped: APP_SEED_ADMIN_* values are missing.");
            return;
        }

        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = User.builder()
                    .username(adminUsername)
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .role(Role.ADMIN)
                    .active(true)
                    .build();
            userRepository.save(admin);
            log.info("Default ADMIN account created for configured seed email.");
        }
    }
}
