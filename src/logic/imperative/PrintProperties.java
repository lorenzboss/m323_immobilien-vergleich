package logic.imperative;

import properties.Property;

import java.util.List;

public class PrintProperties {
    public static void printProperties(List<Property> propertyList, Integer limit) {
        System.out.println("\n\n");

        if (limit == null) {
            System.out.println("All properties:");
            limit = propertyList.size();
        } else if (limit > propertyList.size() || limit < 1) {
            System.out.println("Please enter a valid number!");
            return;
        } else {
            System.out.printf("The first %d properties%n", limit);
        }

        for (int i = 0; i < limit; i++) {
            System.out.println("Year:     " + propertyList.get(i).year());
            System.out.println("District: " + propertyList.get(i).district());
            System.out.println("Rooms:    " + propertyList.get(i).rooms());
            System.out.println("Price:    " + propertyList.get(i).price());
            System.out.println();
        }
    }

    public static void printPropertiesCompact(List<Property> propertyList, Integer limit) {
        int name = 5;
        System.out.println("\n\n");

        if (limit == null) {
            System.out.println("All properties:");
            limit = propertyList.size();
        } else if (limit > propertyList.size() || limit < 1) {
            System.out.println("Please enter a valid number!");
            return;
        } else {
            System.out.printf("The first %d properties%n", limit);
        }
        System.out.printf(
                "%s %-10s %-9s %7s%n", "Year", "Distract", "Rooms", "Price");
        for (int i = 0; i < limit; i++) {
            System.out.printf(
                    "%d %-10s %-9s %7d%n",
                    propertyList.get(i).year(), propertyList.get(i).district(), propertyList.get(i).rooms(), propertyList.get(i).price())
            ;
        }
    }
}
