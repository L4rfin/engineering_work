package ekg.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ekg.AppConfig;
import ekg.entity.ResultsEntity;
import ekg.services.ResultRepositoryService;
import ekg.utility.DataFolderService;
import ekg.utility.DataReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RecentController {
    private final AppConfig appConfig;
    private final ResultRepositoryService repository;
    String fileName;
    private DataReader data;
    List<Double> time;
    List<Double> impact;

    public RecentController(AppConfig appConfig, ResultRepositoryService repository) {
        this.appConfig = appConfig;
        this.repository = repository;
    }


    @GetMapping("/recent_test")
    public String showRecent(Model model){
        DataFolderService dataFolderService = new DataFolderService();
        this.data = dataFolderService.readDataFileOf(appConfig.getUser().getId());
        impact = this.data.getY();
        this.time = new ArrayList<>();
        for (double i = 0; i < impact.size(); i++) {
            time.add(i);
        }

        model.addAttribute("user", appConfig.getUser());
        model.addAttribute("labels", time);
        model.addAttribute("dataY", impact);

        return "recent_test";
    }


    @GetMapping("/submitTest")
    public String submitTest(
            @RequestParam("note_input") String note,
            @RequestParam("method") int method,
            Model model) {
                DataFolderService dataFolderService = new DataFolderService();
        if (method == 1) {
            try {
                dataFolderService.eraseData(appConfig.getUser().getId());
                return "redirect:recent_test";
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

        }

        if (method == 2) {
            try {
                ResultsEntity results = new ResultsEntity();
                results.setUserId(appConfig.getUser().getId());
                results.setNote(note);
                String jsonObject = new ObjectMapper().writeValueAsString(data);
                results.setResult(jsonObject);
                ;


                if (repository.saveResult(results)) {
                    appConfig.setResults(results);
                    dataFolderService.eraseData(appConfig.getUser().getId());
                    return "redirect:tests_comparison";
                }
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                return "redirect:recent_test";
            }
        }
        return "redirect:recent_test";
    }

}
