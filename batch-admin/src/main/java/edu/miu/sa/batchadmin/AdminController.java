package edu.miu.sa.batchadmin;

import edu.miu.sa.batchadmin.messaging.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {
    @Autowired
    Publisher publisher;

    @RequestMapping("/admin/launch")
    public String launchJob() throws Exception {
        publisher.publish("START");
        return "Job start message sent!";
    }
}
