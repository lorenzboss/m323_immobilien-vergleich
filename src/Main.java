import java.io.IOException;
import java.util.List;
import logic.TestFunctions;
import properties.JsonToProperties;
import properties.Property;

public class Main {

  public static void main(String[] args) throws IOException {
    final List<Property> propertyList = JsonToProperties.convertJsonToProperties("dataset.json");

    TestFunctions.testFunction(propertyList);
    // ConsoleApplication.start(propertyList);
  }
}
