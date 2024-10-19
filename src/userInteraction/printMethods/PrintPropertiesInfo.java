package userInteraction.printMethods;

import static userInteraction.HelperMethods.askForInt;
import static userInteraction.HelperMethods.askYesNoQuestion;

import java.util.List;
import properties.Property;
import userInteraction.LogicOption;

public class PrintProperties {

  public static void start(List<Property> propertyList, LogicOption logicOption) {
    boolean compactForm =
        askYesNoQuestion("Do you want to print the properties in a compact form? (y/n): ");
    boolean setLimit = askYesNoQuestion("Do you want to set a limit? (y/n): ");
    Integer limit = setLimit ? askForInt("Please enter the limit: ", propertyList.size(), 1) : null;

    printProperties(propertyList, logicOption, compactForm, limit);
  }

  private static void printProperties(
      List<Property> propertyList, LogicOption logicOption, boolean compactForm, Integer limit) {
    if (compactForm) {
      printPropertiesCompactForm(propertyList, logicOption, compactForm, limit);
    } else {
      printPropertiesNormalForm(propertyList, logicOption, compactForm, limit);
    }
  }

  private static void printPropertiesCompactForm(
      List<Property> propertyList, LogicOption logicOption, boolean compactForm, Integer limit) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PrintProperties.printPropertiesCompact(propertyList, limit);
    } else {
      logic.imperative.PrintProperties.printPropertiesCompact(propertyList, limit);
    }
  }

  private static void printPropertiesNormalForm(
      List<Property> propertyList, LogicOption logicOption, boolean compactForm, Integer limit) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PrintProperties.printProperties(propertyList, limit);
    } else {
      logic.imperative.PrintProperties.printProperties(propertyList, limit);
    }
  }
}
