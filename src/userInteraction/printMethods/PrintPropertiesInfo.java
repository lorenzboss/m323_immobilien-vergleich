package userInteraction.printMethods;

import static userInteraction.HelperMethods.askForInt;
import static userInteraction.HelperMethods.askYesNoQuestion;

import java.util.List;
import properties.Property;
import userInteraction.LogicOption;

/**
 * This class is responsible for printing the properties info in a compact or normal form.
 *
 * @author Lorenz Boss
 * @version 1.0
 */
public class PrintPropertiesInfo {

  /**
   * Starts the process of printing the properties info in a compact or normal form.
   *
   * @param propertyList the list of properties
   * @param logicOption the logic option
   */
  public static void start(List<Property> propertyList, LogicOption logicOption) {
    boolean compactForm =
        askYesNoQuestion("Do you want to print the properties in a compact form? (y/n): ");
    boolean setLimit = askYesNoQuestion("Do you want to set a limit? (y/n): ");
    Integer limit = setLimit ? askForInt("Please enter the limit: ", propertyList.size(), 1) : null;

    printPropertiesInfo(propertyList, logicOption, compactForm, limit);
  }

  private static void printPropertiesInfo(
      List<Property> propertyList, LogicOption logicOption, boolean compactForm, Integer limit) {
    if (compactForm) {
      printPropertiesInfoCompactForm(propertyList, logicOption, limit);
    } else {
      printPropertiesInfoForm(propertyList, logicOption, limit);
    }
  }

  private static void printPropertiesInfoCompactForm(
      List<Property> propertyList, LogicOption logicOption, Integer limit) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PropertiesInfo.propertiesInfoCompact(propertyList, limit);
    } else {
      logic.imperative.PropertiesInfo.propertiesInfoCompact(propertyList, limit);
    }
  }

  private static void printPropertiesInfoForm(
      List<Property> propertyList, LogicOption logicOption, Integer limit) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PropertiesInfo.propertiesInfo(propertyList, limit);
    } else {
      logic.imperative.PropertiesInfo.propertiesInfo(propertyList, limit);
    }
  }
}
