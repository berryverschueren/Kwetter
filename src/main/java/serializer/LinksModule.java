package serializer;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.ws.rs.core.Link;

/**
 * Created by Berry-PC on 28/04/2017.
 */
public class LinksModule extends SimpleModule {

    private static final String NAME = "LinksModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil(){};

    public LinksModule() {
        super(NAME, VERSION_UTIL.version());
        addSerializer(Link.class, new LinkSerializer());
    }
}
