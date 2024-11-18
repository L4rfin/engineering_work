package ekg.services;


import ekg.AppConfig;
import ekg.ResultRepository;
import ekg.entity.ResultsEntity;
import org.springframework.stereotype.Service;

@Service
public class ResultRepositoryService {
    private final AppConfig appConfig;
    private final ResultRepository repository;

    ResultRepositoryService(AppConfig appConfig, ResultRepository repository) {
        this.appConfig = appConfig;
        this.repository = repository;
    }

    public boolean saveResult(ResultsEntity results) {
        repository.save(results);
        if (repository.existsById(results.getId())) {
            appConfig.setResults(results);
            return true;
        }

        return false;
    }
    public boolean updateResults(ResultsEntity results){
        repository.save(results);
        return repository.findById(results.getId()).hashCode()==results.hashCode();
    }
    public boolean deleteResult(ResultsEntity results){
        repository.delete(results);
        if (repository.findById(results.getId()).isEmpty()){
            ResultsEntity results1 = appConfig.getResults_1();
            ResultsEntity results2 = appConfig.getResults_2();
            if (results.equals(results1)) {
                appConfig.setResults(null);
            } else if (results.equals(results2)) {
                appConfig.setResults_2(null);
            }
            return true;
        }
        return false;
    }
}
