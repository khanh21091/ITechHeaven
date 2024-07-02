package org.example.itech_heaven.Controller.Staff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/indexSA")
    public String indexSA() {
        return "indexSA";
    }


}
