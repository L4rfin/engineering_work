package ekg;

import ekg.entity.ResultsEntity;
import ekg.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;

@Component
public class AppConfig {

    AppConfig() {
        String password = "";
        try {
            Scanner scanner = new Scanner("config.txt");

            password = scanner.next();
            scanner.close();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        setPass(password);
        System.out.println(password);
    }

    private String pass;
    private String role;
    private UserEntity user;
    private ResultsEntity results_1;
    private ResultsEntity results_2;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ResultsEntity getResults_1() {
        return results_1;
    }

    public boolean setResults(ResultsEntity results) {
        setResultsHierarchy();
        if (results==null){
            results_1 = null;
            return true;
        }
        if (results_1 == null) {
            results_1 = results;
            return true;
        }else {
            results_2 = results;
        }
        return false;
    }

    public void setResultsHierarchy() {
        if (results_1 == null && results_2 != null) {
            results_1 = results_2;
            results_2 = null;
        }
    }

    public ResultsEntity getResults_2() {
        return results_2;
    }

    public void setResults_2(ResultsEntity results_2) {
        this.results_2 = results_2;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
