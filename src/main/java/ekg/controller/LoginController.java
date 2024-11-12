package ekg.controller;

import ekg.AppConfig;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    final
    AppConfig appConfig;

    final
    UserDetailsService userDetailsService;


    public LoginController(AppConfig appConfig, UserDetailsService userDetailsService) {
        this.appConfig = appConfig;
        this.userDetailsService = userDetailsService;
    }
    @GetMapping("/")
    public String redirectShowLogin() {
        return "/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

}
