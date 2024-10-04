package ekg.controller;

import ekg.AppConfig;
import ekg.ResultRepository;
import ekg.entity.ResultsEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArchivesController {
    private final AppConfig appConfig;
    private final ResultRepository repository;

    public ArchivesController(AppConfig appConfig, ResultRepository repository) {
        this.appConfig = appConfig;
        this.repository = repository;
    }

    @GetMapping("/archives_tests")
    public String showArchives(Model model) {

        List<ResultsEntity> results = repository.findAll();
        results.forEach(resultsEntity -> System.out.println(resultsEntity.toString()));
        model.addAttribute("user", appConfig.getUser());
        model.addAttribute("results", repository.findAll());
        return "archives_tests";
    }

}
