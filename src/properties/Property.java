package properties;

import com.google.gson.annotations.SerializedName;
import properties.enums.District;
import properties.enums.Rooms;

/**
 * This class represents a property.
 *
 * @author Lorenz Boss
 * @version 1.0
 */
public record Property(
    @SerializedName("jahr") int year,
    @SerializedName("bezirk") District district,
    @SerializedName("zimmerzahl") Rooms rooms,
    @SerializedName("verkaufspreis_chf") Integer price) {}
