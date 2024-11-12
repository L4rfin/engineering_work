package ekg.controller;

import ekg.AppConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final AppConfig appConfig;

    public HomeController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("user", appConfig.getUser());
        return "/home";
    }

    @GetMapping("/sign_out")
    public String signOut(@RequestParam("method") int method) {
        appConfig.setUser(null);
        appConfig.setResults_1(null);
        appConfig.setResults_2(null);
        if (method == 1) {
            return "redirect:/user_option";
        } else return "redirect:/user_selection";
    }

}
