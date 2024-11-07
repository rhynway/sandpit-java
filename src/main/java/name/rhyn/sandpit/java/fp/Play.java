package name.rhyn.sandpit.java.fp;

/**
 * The annotation @FunctionalInterface is optional
 */
@FunctionalInterface
public interface Play {

  public static void baseball() { // A functional interface can contain any number of static methods
  }

  private static void soccer() {
  }

  default void play() {
    hidden();
  }

  /**
   * A functional interface has exactly one abstract method
   */
  String fun();

  private void hidden() { // A functional interface can contain any number of private methods
    System.out.println("I have private access");
  }
}
