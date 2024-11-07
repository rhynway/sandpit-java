package name.rhyn.sandpit.java.oop.interfaces;

/**
 * A static method
 * - A static method can be declared public
 * - A static method can be declared without an access modifier (default).
 * - A static method can be declared private. Reason for making it private: It hides the secret
 * implementation details from another developer using the interface.
 * <p>
 * Why public static methods in interfaces?
 * - Allow an interface to define a method at the class level.
 * - Improve code reuse within the interface.
 */
public interface StaticMethods {

  public static String fullyAccessible() {
    return "This static method is public";
  }

  // protected static void notAllowed() { }

  static String withinPackageAccessible() {
    var privateResponse = internallyAccessible();
    return "This static method has a default access" + privateResponse;
  }

  private static String internallyAccessible() {
    return " and uses a private static method";
  }
}
