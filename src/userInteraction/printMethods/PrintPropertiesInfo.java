package userInteraction.printMethods;

import static userInteraction.HelperMethods.askForInt;
import static userInteraction.HelperMethods.askYesNoQuestion;

import java.util.List;
import properties.Property;
import userInteraction.LogicOption;

public class PrintPropertiesInfo {

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
      printPropertiesInfoCompactForm(propertyList, logicOption, compactForm, limit);
    } else {
      printPropertiesInfoForm(propertyList, logicOption, compactForm, limit);
    }
  }

  private static void printPropertiesInfoCompactForm(
      List<Property> propertyList, LogicOption logicOption, boolean compactForm, Integer limit) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PropertiesInfo.propertiesInfoCompact(propertyList, limit);
    } else {
      logic.imperative.PropertiesInfo.propertiesInfoCompact(propertyList, limit);
    }
  }

  private static void printPropertiesInfoForm(
      List<Property> propertyList, LogicOption logicOption, boolean compactForm, Integer limit) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PropertiesInfo.propertiesInfo(propertyList, limit);
    } else {
      logic.imperative.PropertiesInfo.propertiesInfo(propertyList, limit);
    }
  }
}
