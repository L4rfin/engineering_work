package ekg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesController {

    @GetMapping("/legacy_tests")
    public String showArchives(Model model){
        return "legacy_tests";
    }

}
