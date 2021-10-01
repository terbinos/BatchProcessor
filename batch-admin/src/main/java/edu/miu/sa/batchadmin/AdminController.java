package edu.miu.sa.batchadmin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class AdminController {

    @RequestMapping("/admin/launch")
    public String launchJob() {
        String url = "http://localhost:8081/launch";
        return new RestTemplate().getForObject(url, String.class);
    }
}
