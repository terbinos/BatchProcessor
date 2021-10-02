package edu.miu.sa.batchadmin.service;

import edu.miu.sa.batchadmin.dto.LoginDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> authenticate(LoginDTO request);
}
