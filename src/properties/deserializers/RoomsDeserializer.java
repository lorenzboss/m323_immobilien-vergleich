package properties.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import properties.enums.Rooms;

public class RoomsDeserializer implements JsonDeserializer<Rooms> {
    @Override
    public Rooms deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String value = json.getAsString();
        return switch (value) {
            case "1" -> Rooms.ONE;
            case "2" -> Rooms.TWO;
            case "3" -> Rooms.THREE;
            case "4" -> Rooms.FOUR;
            case "5+" -> Rooms.FIVE_PLUS;
            case "Total" -> Rooms.TOTAL;
            default -> throw new JsonParseException("Unknown number of rooms: " + value);
        };
    }
}
