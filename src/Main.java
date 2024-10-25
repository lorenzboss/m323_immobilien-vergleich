import java.io.IOException;
import java.util.List;
import logic.TestFunctions;
import properties.JsonToProperties;
import properties.Property;
import userInteraction.ConsoleApplication;

/**
 * Main class to start the application.
 * @author Lorenz Boss
 * @version 1.0
 */
public class Main {

    /**
     * Reads the json file and starts the application.
     * @param args command line arguments
     * @throws IOException if the file is not found
     */
  public static void main(String[] args) throws IOException {
    final List<Property> propertyList = JsonToProperties.convertJsonToProperties("dataset.json");

    //TestFunctions.testFunction(propertyList);
    ConsoleApplication.start(propertyList);
  }
}
