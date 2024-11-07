package name.rhyn.sandpit.java.oop; // package name is optional. At most one package statement

// import statements are optional

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

interface SomeInterface {

}

/**
 * Only one class can be public. This is called top-level type. There can also be no public
 */
public class TopLevel {

  static final LocalDateTime localDateTime;
  static int AMOUNT; // Static field

  // Static Initializer
  static {
    AMOUNT = 3000;
    localDateTime = LocalDateTime.of(2024, Month.MAY, 10, 16, 32);
  }

  private int outerId; // Instance field
  private String sameName;
  private Nested nestedOne;
  private InnerClass innnerClass;

  // Instance Initializer
  {
    nestedOne = new Nested(3);
    innnerClass = new InnerClass();
  }

  public TopLevel(int outerId) {
    this.outerId = outerId;
  }

  public TopLevel(String outerId) {
    this.outerId = Integer.parseInt(outerId);
    return; // return can be used but it is unnecessary
  }

  // Note: Naming convention. A method must start with a letter, $ or _
  public void startWithALetter() {

  }

  public void $alsoValid() {

  }

  public void ___validToo() {

  }

  // Instance method
  public int getOuterValue() {
    return outerId;
  }

  // It's not possible to define the same method even when one is static
//    public static int getOuterValue() {
//        return 13;
//    }

  public void setOuterValue(int outerId) {
    this.outerId = outerId;
  }

  public Nested getNestedInstance() {
    return nestedOne;
  }

  public InnerClass createInnerInstance() {
    return this.new InnerClass(); // this isn't necessary. The constructor of InnerClass will receive an argument of the outer class
  }

  public String createMessageByLocalClass() {
    // public, protected and private modifier not allowed
    class Message {

      private static final LocalTime IN_THE_AFTERNOON = LocalTime.of(15, 48);

      String generate() {
        return "Hello it's " + IN_THE_AFTERNOON;
      }
    }

    return new Message().generate();
  }

  // Static class or nested class
  public static class Nested {

    private final int id;

    public Nested(int id) {
      this.id = id;
    }

    public int getId() {
      return id;
    }

  }

  /**
   * This is an inner class definition
   */
  public class InnerClass {

    private int savedOuterId;

    private String sameName;

    {
      savedOuterId = outerId;
      sameName = TopLevel.this.sameName; // To use the same name is bad design
    }

    public static void show() {

    }

    public int getOuterId() {
      return savedOuterId;
    }

  }

}

class OtherClass {

}

// Not allowed here
//static class Converter { }
