package edu.miu.sa.batchadmin.config;

import edu.miu.sa.batchadmin.domain.User;
import edu.miu.sa.batchadmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class UserInit {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserInit(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createTestAdmin() {
        createAdmin();
    }

    @Transactional
    public void createAdmin() {
        if (!userRepository.getUserByUsername("Terbinos").isPresent()) {
            User admin = new User();
            admin.setUsername("Terbinos");
            admin.setPassword(bCryptPasswordEncoder.encode("admin123"));
            userRepository.save(admin);
        }
    }
}
