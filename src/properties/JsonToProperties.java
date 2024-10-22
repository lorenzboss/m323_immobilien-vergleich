package properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import properties.deserializers.DistrictDeserializer;
import properties.deserializers.PriceDeserializer;
import properties.deserializers.RoomsDeserializer;
import properties.enums.District;
import properties.enums.Rooms;

public class JsonToProperties {
    public static List<Property> convertJsonToProperties(String filePath) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(District.class, new DistrictDeserializer());
        gsonBuilder.registerTypeAdapter(Rooms.class, new RoomsDeserializer());
        gsonBuilder.registerTypeAdapter(Integer.class, new PriceDeserializer());
        Gson gson = gsonBuilder.create();

        Type listType = new TypeToken<List<Property>>() {
        }.getType();

        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, listType);
        }
    }
}