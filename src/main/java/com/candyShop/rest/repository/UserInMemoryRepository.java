package com.candyShop.rest.repository;

import com.candyShop.rest.controller.dto.CurrentUser;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.util.HashMap;
@Component
public class UserInMemoryRepository {
    private static final HashMap<String, CurrentUser> REGISTERED_USERS = new HashMap<>(2);

    public CurrentUser findUserByUsername(final String username) {
        return REGISTERED_USERS.get(username);
    }
    @PostConstruct
    public void setupUsers() {
        REGISTERED_USERS.put("user1", buildCurrentUser(
                "user1",
                "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG"
        ));
        REGISTERED_USERS.put("user2", buildCurrentUser(
                "user2",
                "$2a$10$hvOa9FAisXftunkgb/QmkO5FLTQCI123rKTY.yuWAv9DjOW43/cSi"
        ));
        REGISTERED_USERS.put("user", buildCurrentUser(
                "user",
                "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG"
        ));
    }
   private static CurrentUser buildCurrentUser(final String username, final String password) {
        final CurrentUser currentUser = new CurrentUser();
        currentUser.setUsername(username);
        currentUser.setPassword(password);
        return null;
   }

}
