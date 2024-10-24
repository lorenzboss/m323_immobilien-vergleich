package properties.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class PriceDeserializer implements JsonDeserializer<Integer> {
  @Override
  public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String jsonString = json.getAsString().trim();
    if (jsonString.equals("( )")) {
      return null;
    }
    try {
      return Integer.parseInt(jsonString);
    } catch (NumberFormatException e) {
      throw new JsonParseException("Invalid integer format: " + jsonString, e);
    }
  }
}
