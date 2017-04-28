package serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.ws.rs.core.Link;
import java.io.IOException;

/**
 * Created by Berry-PC on 28/04/2017.
 */
public class LinkSerializer extends JsonSerializer<Link> {
    @Override
    public void serialize(Link link, JsonGenerator jg, SerializerProvider sp)
            throws IOException {
        jg.writeStartObject();
        jg.writeStringField("rel", link.getRel());
        jg.writeStringField("href", link.getUri().toString());
        jg.writeStringField("type", link.getType());
        jg.writeEndObject();
    }
}
