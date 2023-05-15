package mk.ukim.finki.tutormind.tutormind.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_TEACHER, ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
