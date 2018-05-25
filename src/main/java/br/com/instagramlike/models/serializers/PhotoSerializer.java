package br.com.instagramlike.models.serializers;

import br.com.instagramlike.models.domains.Photo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PhotoSerializer extends StdSerializer<Photo> {

    public PhotoSerializer(Class<Photo> t) {
        super(t);
    }

    @Override
    public void serialize(Photo photo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
