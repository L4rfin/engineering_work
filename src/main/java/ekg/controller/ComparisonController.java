package ekg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComparisonController {

    @GetMapping("/tests_comparison")
    public String showComparison(Model model) {
        return "tests_comparison";
    }
}
