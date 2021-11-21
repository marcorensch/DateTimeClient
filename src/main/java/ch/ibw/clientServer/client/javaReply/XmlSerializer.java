package ch.ibw.clientServer.client.javaReply;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class XmlSerializer {

    public String serialize(Object obj) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.writeValueAsString(obj);
    }

    public <T> T deserialize(String payload, TypeReference<T> ref) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(payload, ref);
    }
}
