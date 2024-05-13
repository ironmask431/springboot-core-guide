package kevin.springboot.core.guide.feign.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityAuthTokenGetter {
    private SecurityAuthTokenGetter() {
    }

    public static String getAuthToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getCredentials();
    }
}
