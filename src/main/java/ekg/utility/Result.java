package ekg.utility;

import org.springframework.stereotype.Component;

@Component
public class Result {
    public final ekg.ResultRepository repository;
    Result(ekg.ResultRepository resultRepository){
       this.repository = resultRepository;
    }
}
