package name.rhyn.sandpit.java.datatypes;

public enum ClothingSize {

  EXTRA_SMALL("XS") {
    @Override
    public int getInternalCode() {
      return 100;
    }
  },
  SMALL("S") {
    @Override
    public int getInternalCode() {
      return 300;
    }
  },
  MEDIUM("M") {
    @Override
    public int getInternalCode() {
      return 450;
    }
  },
  LARGE("L") {
    @Override
    public int getInternalCode() {
      return 500;
    }
  },
  EXTRA_LARGE("XL") {
    @Override
    public int getInternalCode() {
      return 600;
    }
  };

  // Fields can have a public, protected, no and private access modifier
  private String abbreviation;

  // Not allowed:  public, protected, final
  // Allowed : default, private
  ClothingSize(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public abstract int getInternalCode();

}
