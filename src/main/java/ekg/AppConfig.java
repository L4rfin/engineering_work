package ekg;

import ekg.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    private UserEntity user;
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

}
