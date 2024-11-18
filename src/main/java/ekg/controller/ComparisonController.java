package ekg.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ekg.AppConfig;
import ekg.services.ResultRepositoryService;
import ekg.utility.DataReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ComparisonController {

    public final AppConfig appConfig;
    public final ResultRepositoryService repositoryService;
    List<Double> time1;
    List<Double> impulse1;
    List<Double> time2;
    List<Double> impulse2;

    public ComparisonController(AppConfig appConfig, ResultRepositoryService repositoryService) {
        this.appConfig = appConfig;
        this.repositoryService = repositoryService;
    }


    @GetMapping("/tests_comparison")
    public String showComparison(Model model) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        DataReader data;

        appConfig.setResultsHierarchy();

        //case of no test to show on pages
        if (appConfig.getResults_1() == null && appConfig.getResults_2() == null) {
            System.out.println("non test chosen");
            boolean message = true;
            model.addAttribute("value_chose", message);
            return "tests_comparison";
        }

        //case fo 1 test to show on pages
        if (appConfig.getResults_1() != null) {
            data = mapper.readValue(appConfig.getResults_1().getResult(), DataReader.class);
            impulse1 = data.getY();
            time1 = new ArrayList<>();
            for (double i = 0; i < impulse1.size(); i++) {
                time1.add(i);
            }
        }

        //case of 2 test to show on pages
        if (appConfig.getResults_2() != null) {

            //case of the same test chosen second time
            if (appConfig.getResults_1().getId() == appConfig.getResults_2().getId()) {
                appConfig.setResults_2(null);
                System.out.println("only the same test");
                model.addAttribute("labels1", time1);
                model.addAttribute("dataY1", impulse1);
                model.addAttribute("note1", appConfig.getResults_1().getNote());
                return "tests_comparison";
            }

            data = mapper.readValue(appConfig.getResults_2().getResult(), DataReader.class);
            impulse2 = data.getY();
            time2 = new ArrayList<>();
            for (double i = 0; i < impulse2.size(); i++) {
                time2.add(i);
            }

        }
        //showing the 1 test only
        else {
            System.out.println("only one chosen");
            model.addAttribute("labels1", time1);
            model.addAttribute("dataY1", impulse1);
            model.addAttribute("note1", appConfig.getResults_1().getNote());
            return "tests_comparison";
        }
        //showing the 1 and 2 tests
        System.out.println("chosen to test to compare");
        model.addAttribute("labels1", time1);
        model.addAttribute("dataY1", impulse1);
        model.addAttribute("note1", appConfig.getResults_1().getNote());
        model.addAttribute("labels2", time2);
        model.addAttribute("dataY2", impulse2);
        model.addAttribute("note2", appConfig.getResults_2().getNote());

        return "tests_comparison";
    }

    @PostMapping("/chart_edit")
    public String chartEdit(
            @RequestParam(value = "method", defaultValue = "null") String method,
            @RequestParam(value = "note", defaultValue = "") String note
    ) {
        System.out.println(method);
        return switch (method) {
            case "save1" -> {
                appConfig.getResults_1().setNote(note);
                if(repositoryService.updateResults(appConfig.getResults_1())){
                    System.out.println("result1 update correctly");
                }
                yield "redirect:tests_comparison";
            }
            case "save2" -> {
                appConfig.getResults_2().setNote(note);
                if(repositoryService.updateResults(appConfig.getResults_2())){
                    System.out.println("result2 update correctly");
                }
                yield "redirect:tests_comparison";
            }
            case "delete1" -> {
                if(repositoryService.deleteResult(appConfig.getResults_1())){
                    System.out.println("result1 remove correctly");
                }
                yield "redirect:tests_comparison";
            }
            case "delete2" -> {
                if(repositoryService.deleteResult(appConfig.getResults_2())){
                    System.out.println("result2 delete correctly");
                }
                yield "redirect:tests_comparison";
            }
            default -> throw new IllegalStateException("Unexpected value: " + method);
        };
    }

}
