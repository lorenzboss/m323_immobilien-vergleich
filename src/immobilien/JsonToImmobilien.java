package immobilien;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import deserializers.BezirkDeserializer;
import deserializers.VerkaufspreisDeserializer;
import deserializers.ZimmerzahlDeserializer;
import immobilien.enums.Bezirk;
import immobilien.enums.Zimmerzahl;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonToImmobilien {
    public static List<Immobilien> convertJsonToImmobilien(String filePath) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Bezirk.class, new BezirkDeserializer());
        gsonBuilder.registerTypeAdapter(Zimmerzahl.class, new ZimmerzahlDeserializer());
        gsonBuilder.registerTypeAdapter(Integer.class, new VerkaufspreisDeserializer());
        Gson gson = gsonBuilder.create();

        Type listType = new TypeToken<List<Immobilien>>() {
        }.getType();

        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, listType);
        }
    }
}