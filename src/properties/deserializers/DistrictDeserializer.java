package properties.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import properties.enums.District;

public class DistrictDeserializer implements JsonDeserializer<District> {
  @Override
  public District deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String value = json.getAsString();
    return switch (value) {
      case "Bezirk Laufen" -> District.LAUFEN;
      case "Bezirk Liestal" -> District.LIESTAL;
      case "Bezirk Sissach" -> District.SISSACH;
      case "Bezirk Waldenburg" -> District.WALDENBURG;
      case "Bezirk Arlesheim" -> District.ARLESHEIM;
      default -> throw new JsonParseException("Unknown district: " + value);
    };
  }
}
