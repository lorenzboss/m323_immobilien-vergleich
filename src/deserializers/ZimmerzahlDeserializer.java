package deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import immobilien.enums.Zimmerzahl;

import java.lang.reflect.Type;

public class ZimmerzahlDeserializer implements JsonDeserializer<Zimmerzahl> {
    @Override
    public Zimmerzahl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String value = json.getAsString();
        return switch (value) {
            case "1" -> Zimmerzahl.EINS;
            case "2" -> Zimmerzahl.ZWEI;
            case "3" -> Zimmerzahl.DREI;
            case "4" -> Zimmerzahl.VIER;
            case "5+" -> Zimmerzahl.FÜNF_PLUS;
            case "Total" -> Zimmerzahl.TOTAL;
            default -> throw new JsonParseException("Unbekannte Zimmerzahl: " + value);
        };
    }
}
