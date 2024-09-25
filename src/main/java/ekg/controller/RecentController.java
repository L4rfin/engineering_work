package ekg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecentController {
    @GetMapping("/recent_test")
    public String showRecent(Model model){
        return "recent_test";
    }
}
