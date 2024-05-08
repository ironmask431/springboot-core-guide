package kevin.springboot.core.guide.feign.util;

public class BearerTokenFormatter {
    private BearerTokenFormatter() {
    }

    public static String format(String token) {
        return String.format("Bearer %s", token);
    }
}
