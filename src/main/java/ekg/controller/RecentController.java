package ekg.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ekg.AppConfig;
import ekg.ResultRepository;
import ekg.entity.ResultsEntity;
import ekg.utility.DataReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RecentController {
    private final AppConfig appConfig;
    private final ResultRepository repository;
    String fileName;
    private DataReader data;
    List<Double> time;
    List<Double> impact;
    public RecentController(AppConfig appConfig, ResultRepository repository) {
        this.appConfig = appConfig;
        this.repository = repository;
    }


    @GetMapping("/recent_test")
    public String showRecent(Model model) throws IOException {
        this.fileName = appConfig.getUser().getId()+".json";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(new File("src/main/java/ekg/data/"+this.fileName), DataReader.class);
        impact = data.getY();
        this.time = new ArrayList<>();
        for (double i=0;i<impact.size();i++){time.add(i);}

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
        if (method == 1) {
            try{
                FileWriter fileWriter = new FileWriter("src/main/java/ekg/data/" + fileName);
                fileWriter.append("{\"x\":[],"+"\"y\":[]}");
                fileWriter.close();
                model.addAttribute("user", appConfig.getUser());
                model.addAttribute("labels", "");
                model.addAttribute("dataY", "");
                return "redirect:/recent_test";
            }catch (Exception e){
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
                repository.save(results);


                if (repository.findById(results.getId()).isPresent()) {
                    appConfig.setResults_1(results);
                    return "redirect:/tests_comparison";
                }
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                return "redirect:/recent_test";
            }
        }
        return "redirect:/recent_test";
    }

}
