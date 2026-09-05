package korrawit.cms.config;

import java.lang.reflect.Type;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.format.FormatMapper;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

public final class Jackson3JsonFormatMapper implements FormatMapper {

    private final ObjectMapper objectMapper = JsonMapper.builder().build();

    @Override
    public <T> T fromString(CharSequence charSequence, JavaType<T> javaType, WrapperOptions wrapperOptions) {
        Type type = javaType.getJavaType();
        return objectMapper.readValue(charSequence.toString(), objectMapper.constructType(type));
    }

    @Override
    public <T> String toString(T value, JavaType<T> javaType, WrapperOptions wrapperOptions) {
        return objectMapper.writeValueAsString(value);
    }
}
