package kevin.springboot.core.guide.feign.util;

import java.net.URI;

public class BaseUriCreator {
    private BaseUriCreator() {
    }

    public static URI create(String prefixUrl) {
        return URI.create("http://" + prefixUrl);
    }
}
