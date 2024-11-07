package name.rhyn.sandpit.java.oop.interfaces;

public interface InterfaceMethods {

  private String respondHidden() { // Only when there is a body
    return "hello";
  }

  /**
   * An abstract method
   * - has no body
   * - It is implicitly public abstract
   * - protected and private access modifier are not allowed
   */
  String respond();

  public abstract String respond2();

  /**
   * A default method
   * - has a always a body
   * - Valid access modifiers are: public and nothing (implicitly public not default)
   * - Nothing can prevent override of a default method in an interface
   */
  default String respondDefault() {
    return "Hi";
  }

  // Not allowed for a method -> final, protected, volatile, sealed

}
