package properties.enums;

public enum Rooms {
  ONE,
  TWO,
  THREE,
  FOUR,
  TOTAL,
  FIVE_PLUS;

  public int getSortOrder() {
    return switch (this) {
      case ONE -> 1;
      case TWO -> 2;
      case THREE -> 3;
      case FOUR -> 4;
      case TOTAL -> 5;
      case FIVE_PLUS -> 6;
      default -> Integer.MAX_VALUE;
    };
  }
}
