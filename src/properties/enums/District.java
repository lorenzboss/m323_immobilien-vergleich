package properties.enums;

public enum District {
  LAUFEN,
  LIESTAL,
  SISSACH,
  WALDENBURG,
  ARLESHEIM;

  public int getSortOrder() {
    return switch (this) {
      case ARLESHEIM -> 1;
      case LAUFEN -> 2;
      case LIESTAL -> 3;
      case SISSACH -> 4;
      case WALDENBURG -> 5;
      default -> Integer.MAX_VALUE;
    };
  }
}
