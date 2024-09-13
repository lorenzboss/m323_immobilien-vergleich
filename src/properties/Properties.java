package properties;

import properties.enums.District;
import properties.enums.Rooms;

public class Properties {
  private final int year;
  private final int districtNumber;
  private final District district;
  private final Rooms rooms;
  private final Integer price;

  public Properties(
          int year, int districtNumber, District district, Rooms rooms, Integer price) {
    this.year = year;
    this.districtNumber = districtNumber;
    this.district = district;
    this.rooms = rooms;
    this.price = price;
  }

  public int getYear() {
    return year;
  }

  public int getDistrictNumber() {
    return districtNumber;
  }

  public District getDistrict() {
    return district;
  }

  public Rooms getRooms() {
    return rooms;
  }

  public Integer getPrice() {
    return price;
  }
}
