package ekg.services;

import ekg.AppConfig;
import ekg.UsersRepository;
import ekg.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryService {
    private final UsersRepository repository;
    private  final AppConfig appConfig;
    UserRepositoryService(UsersRepository repository,AppConfig appConfig){
        this.repository = repository;
        this.appConfig = appConfig;
    }


    public boolean addUserToDatabase(UserEntity user){
        repository.save(user);
        appConfig.setUser(user);
        return repository.existsById(user.getId());
    }
    public boolean updateUser(UserEntity userToUpdate){
        UserEntity user = appConfig.getUser();
        if (user.equals(userToUpdate)) return false;
        else repository.save(user);
        return true;
    }
    public boolean deleteUser(UserEntity user){
        repository.delete(user);
        if (repository.existsById(user.getId())) return false;
        appConfig.setUser(null);
        appConfig.setResults(null);
        appConfig.setResults_2(null);
        return true;
    }
}
