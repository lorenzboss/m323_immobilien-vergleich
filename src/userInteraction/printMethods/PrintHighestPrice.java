package userInteraction.printMethods;

import static userInteraction.HelperMethods.askForInt;
import static userInteraction.HelperMethods.askYesNoQuestion;

import java.util.List;
import properties.Property;
import userInteraction.LogicOption;

/**
 * This class is responsible for printing the highest price of the properties.
 *
 * @author Lorenz Boss
 * @version 1.0
 */
public class PrintHighestPrice {

  /** Starts the process of printing the highest price of the properties.
   *
   * @param propertyList the list of properties
   * @param logicOption the logic option
   */
  public static void start(List<Property> propertyList, LogicOption logicOption) {
    boolean priceOnly =
        askYesNoQuestion("Do you only want to print out the price of the properties? (y/n): ");
    int limit = askForInt("How many properties do you want to print: ", propertyList.size(), 1);

    printHighestPrice(propertyList, logicOption, priceOnly, limit);
  }

  private static void printHighestPrice(
      List<Property> propertyList, LogicOption logicOption, boolean priceOnly, int limit) {
    if (priceOnly) {
      printHighestPricePriceOnly(propertyList, logicOption, limit);
    } else {
      printHighestPriceNormalForm(propertyList, logicOption, limit);
    }
  }

  private static void printHighestPricePriceOnly(
      List<Property> propertyList, LogicOption logicOption, int limit) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.HighestPrice.mostExpensivePropertiesPrice(propertyList, limit);
    } else {
      logic.imperative.HighestPrice.mostExpensivePropertiesPrice(propertyList, limit);
    }
  }

  private static void printHighestPriceNormalForm(
      List<Property> propertyList, LogicOption logicOption, int limit) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.HighestPrice.mostExpensiveProperties(propertyList, limit);
    } else {
      logic.imperative.HighestPrice.mostExpensiveProperties(propertyList, limit);
    }
  }
}
