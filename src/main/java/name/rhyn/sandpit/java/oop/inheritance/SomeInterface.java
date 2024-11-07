package name.rhyn.sandpit.java.oop.inheritance;

public interface SomeInterface {

  public static Integer count = 10;

  static String staticMethod() {
    return "Interface static method";
  }

  String abstractMethod();

  Integer getCount();

  default String defaultMethod() {
    return "Interface default method";
  }

}
