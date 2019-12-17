package ch.ibw.clientServer.client.javaReply;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class XmlSerializer {

    public <T> T deserialize(String data, TypeReference<T> ref) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(data, ref);
    }
}
