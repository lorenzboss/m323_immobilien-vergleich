package properties.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import properties.enums.District;

/**
 * Deserializer for the district, which is represented as a string in the json file.
 *
 * @author Lorenz Boss
 * @version 1.0
 */
public class DistrictDeserializer implements JsonDeserializer<District> {

  /**
   * Deserializes the district.
   *
   * @param json the json element
   * @param typeOfT the type of the object
   * @param context the context
   * @return the district
   * @throws JsonParseException if the district is unknown
   */
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
