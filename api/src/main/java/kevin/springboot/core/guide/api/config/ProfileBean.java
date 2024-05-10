package kevin.springboot.core.guide.api.config;

import lombok.Getter;

@Getter
public class ProfileBean {
    private String activeProfile;
    public ProfileBean(String activeProfile) {
        this.activeProfile = activeProfile;
    }
}
