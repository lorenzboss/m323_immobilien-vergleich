package logic.imperative;

import properties.Property;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class HighestPrice {
    public static void mostExpensivePropertiesPrice(List<Property> propertyList, Integer limit, boolean isProperties) {
        System.out.println("\n\n");
        System.out.println("The price of the most expensive properties");
        propertyList.sort((p1, p2) -> {
            if (p1.price() == null && p2.price() == null) {
                return 0;
            }
            if (p1.price() == null) {
                return 1;
            }
            if (p2.price() == null) {
                return -1;
            }
            return p2.price() - p1.price();
        });
        System.out.printf("%3s | %10s | %15s | %9s | %12s%n", "No", "Year", "District", "Rooms", "Price");
        System.out.println("------------------------------------------------------------");


        if (limit == null) {
            limit = propertyList.size();
        }
        for (int i = 0; i < limit && i < propertyList.size(); i++) {
            Property property = propertyList.get(i);
            if (property.price() != null) {
                System.out.printf(
                        "%3d | %10d | %15s | %10s | %12d%n",
                        i + 1,
                        property.year(),
                        property.district(),
                        property.rooms(),
                        property.price()
                );
            }
        }
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
