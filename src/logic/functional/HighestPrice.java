package logic.functional;

import properties.Property;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class HighestPrice {
    public static void mostExpensivePropertiesPrice(List<Property> propertyList, int limit) {
        AtomicInteger index = new AtomicInteger(1);
        System.out.println("\n\n");
        System.out.println("The price of the most expensive properties");
        propertyList.stream()
                .map(Property::price)
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .limit(limit)
                .toList()
                .forEach(
                        price ->
                                System.out.printf("%3d: selling price CHF: %d%n", index.getAndIncrement(), price));
    }

    public static void mostExpensiveProperties(List<Property> propertyList, int limit) {
        AtomicInteger index = new AtomicInteger(1);
        System.out.println("\n\n");
        System.out.println("The most expensive properties");
        propertyList.stream()
                .filter(property -> property.price() != null)
                .sorted(Comparator.comparing(Property::price).reversed())
                .limit(limit)
                .toList()
                .forEach(
                        property ->
                                System.out.printf(
                                        "%3d: year: %d, district: %10s, rooms: %9s, price CHF: %d%n",
                                        index.getAndIncrement(),
                                        property.year(),
                                        property.district(),
                                        property.rooms(),
                                        property.price()));
    }


}
