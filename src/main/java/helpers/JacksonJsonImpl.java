package helpers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JacksonJsonImpl {
    private static final ThreadLocal<ObjectMapper> tlObjectMapper = new ThreadLocal();
    private static final ThreadLocal<ObjectMapper> mtlObjectMapper = new ThreadLocal();
    private static volatile JacksonJsonImpl _instance;

    public JacksonJsonImpl() {
    }

    public static JacksonJsonImpl getInstance() {
        if (_instance == null) {
            Class var0 = JacksonJsonImpl.class;
            synchronized(JacksonJsonImpl.class) {
                if (_instance == null) {
                    _instance = new JacksonJsonImpl();
                }
            }
        }

        return _instance;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = (ObjectMapper)tlObjectMapper.get();
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setVisibilityChecker(objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            tlObjectMapper.set(objectMapper);
        }

        return objectMapper;
    }

    public <T> String toJSon(T t) throws IOException {
        try {
            String json = this.getObjectMapper().writeValueAsString(t);
            return json;
        } catch (JsonGenerationException var3) {
            throw var3;
        } catch (JsonMappingException var4) {
            throw var4;
        } catch (IOException var5) {
            throw var5;
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) throws IOException {
        try {
            T t = this.getObjectMapper().readValue(json, clazz);
            return t;
        } catch (JsonParseException var4) {
            throw var4;
        } catch (JsonMappingException var5) {
            throw var5;
        } catch (IOException var6) {
            throw var6;
        }
    }

    public <T> T fromJson(File file, Class<T> clazz) throws IOException {
        try {
            T t = this.getObjectMapper().readValue(file, clazz);
            return t;
        } catch (JsonParseException var4) {
            throw var4;
        } catch (JsonMappingException var5) {
            throw var5;
        } catch (IOException var6) {
            throw var6;
        }
    }

    public <T> T fromJson(String json, TypeReference<T> typeRef) throws IOException {
        try {
            T t = this.getObjectMapper().readValue(json, typeRef);
            return t;
        } catch (JsonParseException var4) {
            throw var4;
        } catch (JsonMappingException var5) {
            throw var5;
        } catch (IOException var6) {
            throw var6;
        }
    }

    public <T> List<HashMap> toListHashMap(T t) throws IOException {
        List<HashMap> jsonMap = (List)this.getObjectMapper().convertValue(t, List.class);
        return jsonMap;
    }

    public <T> HashMap toHashMap(T t) throws IOException {
        HashMap jsonMap = (HashMap)this.getObjectMapper().convertValue(t, HashMap.class);
        return jsonMap;
    }

    public <T> byte[] toJSonByte(T t) throws IOException {
        String json = (new ObjectMapper()).writeValueAsString(t);
        return this.toJSonByte(json);
    }

    public <T> byte[] toJSonByte(String json) throws IOException {
        try {
            byte[] b = this.getObjectMapper().writeValueAsBytes(this.getObjectMapper().readValue(json, Object.class));
            return b;
        } catch (JsonGenerationException var3) {
            throw var3;
        }
    }

    public <T> byte[] toMsgPackByte(T t) throws IOException {
        try {
            String json = (new ObjectMapper()).writeValueAsString(t);
            return this.toMsgPackByte(json);
        } catch (JsonGenerationException var3) {
            throw var3;
        } catch (JsonMappingException var4) {
            throw var4;
        } catch (IOException var5) {
            throw var5;
        }
    }

    public <T> JsonNode toJsonNode(String json) throws IOException {
        try {
            JsonNode jsonNode = this.getObjectMapper().readTree(json);
            return jsonNode;
        } catch (JsonGenerationException var3) {
            throw var3;
        } catch (JsonMappingException var4) {
            throw var4;
        } catch (IOException var5) {
            throw var5;
        }
    }
}