package name.rhyn.sandpit.java.datatypes.record;

/**
 * - A record can implement interfaces.
 * - A record can contain multiple regular constructors.
 * - A record can't have instance variables
 * - A record can't have instance initializers
 * - A record cannot include an abstract method
 * - A record can contain a compact constructor
 */
public record Passenger(String firstName, String lastName, String seatNumber) {

  public Passenger(String firstName, String lastName) {
    this(firstName, lastName, "No seat");
  }

  // Compact Constructor
  // It must have the same access modifier as the record -> public
  public Passenger {
    if (firstName == null || lastName == null || seatNumber == null) {
      throw new IllegalArgumentException("Fields cannot be null");
    }
    // this isn't allowed in a compact constructor
    // this.name = name.toLowerCase(); Can't reassign
  }

  @Override
  public String toString() {
    return "Passenger[firstName=" + firstName + ",lastName=" + lastName + ",seatNumber="
        + seatNumber + "]";
  }

  /**
   * To override don't use the get prefix
   */
  @Override
  public String firstName() {
    return "FirstName: " + firstName;
  }

}
