package deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import immobilien.enums.Bezirk;

import java.lang.reflect.Type;

public class BezirkDeserializer implements JsonDeserializer<Bezirk> {
    @Override
    public Bezirk deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String value = json.getAsString();
        return switch (value) {
            case "Bezirk Laufen" -> Bezirk.LAUFEN;
            case "Bezirk Liestal" -> Bezirk.LIESTAL;
            case "Bezirk Sissach" -> Bezirk.SISSACH;
            case "Bezirk Waldenburg" -> Bezirk.WALDENBURG;
            case "Bezirk Arlesheim" -> Bezirk.ARLESHEIM;
            default -> throw new JsonParseException("Unbekannter Bezirk: " + value);
        };
    }
}