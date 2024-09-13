package properties;

import com.google.gson.annotations.SerializedName;
import properties.enums.District;
import properties.enums.Rooms;

public class Property {
  @SerializedName("jahr")
  private final int year;

  @SerializedName("bezirk_nummer")
  private final int districtNumber;

  @SerializedName("bezirk")
  private final District district;

  @SerializedName("zimmerzahl")
  private final Rooms rooms;

  @SerializedName("verkaufspreis_chf")
  private final Integer price;

  public Property(int year, int districtNumber, District district, Rooms rooms, Integer price) {
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
