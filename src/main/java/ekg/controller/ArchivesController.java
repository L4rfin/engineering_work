package ekg.controller;

import ekg.AppConfig;
import ekg.ResultRepository;
import ekg.entity.ResultsEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<ResultsEntity> results = repository.findAllByUserId(appConfig.getUser().getId());
        results.forEach(resultsEntity -> System.out.println(resultsEntity.toString()));
        model.addAttribute("user", appConfig.getUser());
        model.addAttribute("results", results);
        return "archives_tests";
    }
    @GetMapping("/archives_chosen_test")
    public String resultsOfTest(
                                @RequestParam("id_test") long idTest) {
        appConfig.setResults_1(repository.getById(idTest));
        System.out.println(repository.getById(idTest));
        return "redirect:/tests_comparison";
    }

}
