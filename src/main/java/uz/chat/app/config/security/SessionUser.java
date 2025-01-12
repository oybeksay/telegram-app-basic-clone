package uz.chat.app.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionUser {

    public CustomUsersDetails user(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principals = authentication.getAuthorities();
        if (principals instanceof CustomUsersDetails ud) {
            return ud;
        } return null;
    }

    public Long id(){
        CustomUsersDetails user = user();
        if(user != null){
            return user.getId();
        } return -1L;
    }

}
