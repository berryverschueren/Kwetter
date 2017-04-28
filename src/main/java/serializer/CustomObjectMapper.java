package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by Berry-PC on 28/04/2017.
 */
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        registerModule(new LinksModule());
        enable(SerializationFeature.INDENT_OUTPUT);
    }
}
