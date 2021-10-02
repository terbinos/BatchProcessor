package edu.miu.sa.batchadmin.controller;

import edu.miu.sa.batchadmin.constants.RestEndpoints;
import edu.miu.sa.batchadmin.dto.LoginDTO;
import edu.miu.sa.batchadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(RestEndpoints.USER_PREFIX)
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(RestEndpoints.LAUNCH_POSTFIX)
    public String launchJob() {
        return new RestTemplate().getForObject(RestEndpoints.URL, String.class);
    }

    @RequestMapping(RestEndpoints.LOGIN)
    public ResponseEntity<Object> authenticate(@RequestBody LoginDTO loginBody) {
        return userService.authenticate(loginBody);
    }
}
