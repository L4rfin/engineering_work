package ekg.controller;

import ekg.AppConfig;
import ekg.UsersRepository;
import ekg.WebSecurityConfig;
import ekg.entity.UserEntity;
import ekg.services.UserRepositoryService;
import ekg.utility.DataFolderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserEditionController {

    final
    AppConfig appConfig;
    private final UserRepositoryService repositoryService;
    public UserEditionController(UserRepositoryService repositoryService, AppConfig appConfig) {
        this.appConfig = appConfig;
        this.repositoryService =repositoryService;
    }

    @GetMapping("/user_edition")
    public String showUserEdition(Model model) {
        model.addAttribute("user", appConfig.getUser());
        return "user_edition";
    }

    @PostMapping("/send_edit")
    public String saveEdit(
            @RequestParam(value = "username", defaultValue = "null") String name,
            @RequestParam(value = "age", defaultValue = "0") int age,
            @RequestParam(value = "note", defaultValue = "null") String note
    ) {
        UserEntity user = appConfig.getUser();
        user.setNote(note);
        user.setAge(age);
        user.setName(name);
        if (repositoryService.updateUser(user)) System.out.println("user updated correctly");;
        return "redirect:home";
    }
    @PostMapping("/delete_user")
    public String userDelete(){
        long temId = appConfig.getUser().getId();
        if (repositoryService.deleteUser(appConfig.getUser())) System.out.println("user deleted correctly");
        DataFolderService folderService = new DataFolderService();
        folderService.RemoveUserDataFile(temId);
        return "redirect:login";
    }
}
