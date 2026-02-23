import java.util.*;

/**
 * SRP: Single responsibility - parse "key=value;key=value" strings into a field map.
 * Reason to change: only if the input string format changes (delimiter, encoding, etc.)
 */
public class RawInputParser {

    /**
     * Parses a raw semicolon-delimited key=value string.
     * Example: "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE"
     */
    public Map<String, String> parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        if (raw == null || raw.isBlank()) return kv;
        for (String part : raw.split(";")) {
            String[] tokens = part.split("=", 2);
            if (tokens.length == 2) {
                kv.put(tokens[0].trim(), tokens[1].trim());
            }
        }
        return kv;
    }
}
