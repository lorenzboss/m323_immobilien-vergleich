package properties;

import com.google.gson.annotations.SerializedName;
import properties.enums.District;
import properties.enums.Rooms;

public record Property(
    @SerializedName("jahr") int year,
    @SerializedName("bezirk") District district,
    @SerializedName("zimmerzahl") Rooms rooms,
    @SerializedName("verkaufspreis_chf") Integer price) {}
