import java.io.IOException;
import java.util.List;
import properties.JsonToProperties;
import properties.Property;
import userInteraction.ConsoleApplication;

public class Main {

  public static void main(String[] args) throws IOException {
    final List<Property> propertyList = JsonToProperties.convertJsonToProperties("dataset.json");

    ConsoleApplication.start(propertyList);
  }
}
