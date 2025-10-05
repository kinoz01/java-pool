import java.util.*;
import java.net.URI;

public class BreakdownURL {

    public Map<String, String> parseURL(String url) {
        Map<String, String> res = new HashMap<>();
        try {
            URI u = URI.create(url);
            res.put("protocol", u.getScheme());
            res.put("domain", u.getHost());
            if (u.getPort() != -1)
                res.put("port", u.getPort() + "");
            res.put("path", (u.getPath() == null || u.getPath().isEmpty()) ? "/" : u.getPath());
            if (u.getQuery() != null)
                res.put("query", u.getQuery());
        } catch (Exception e) {
        }
        return res;
    }
}