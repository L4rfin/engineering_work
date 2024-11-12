package ekg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserOptionController {

    @GetMapping("/user_selection_option")
    public String user_selection(
            @RequestParam(value = "method", defaultValue = "0") int method
    ) {
        {
            if (method == 1) return "redirect:/user_selection";
            if (method == 2) return "redirect:/create_user";
            return "redirect:/user_option";
        }
    }

    @GetMapping("/user_option")
    public String showUserOption() {
        return "user_option";
    }

}
