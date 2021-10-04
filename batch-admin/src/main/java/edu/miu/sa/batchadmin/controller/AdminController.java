package edu.miu.sa.batchadmin.controller;

import edu.miu.sa.batchadmin.constants.RestEndpoints;
import edu.miu.sa.batchadmin.dto.LoginDTO;
import edu.miu.sa.batchadmin.messaging.Publisher;
import edu.miu.sa.batchadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(RestEndpoints.USER_PREFIX)
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    Publisher publisher;

    @GetMapping(RestEndpoints.LAUNCH_POSTFIX)
    public String launchJob() {
        System.out.println("hey there");
        publisher.publish("START");
        return "Job start message sent!";
    }

    @PostMapping(RestEndpoints.LOGIN)
    public ResponseEntity<Object> authenticate(@RequestBody LoginDTO loginBody) {
        return userService.authenticate(loginBody);
    }
}
