package name.rhyn.sandpit.java.flow;


import static name.rhyn.sandpit.java.datatypes.ClothingSize.EXTRA_LARGE;
import static name.rhyn.sandpit.java.datatypes.ClothingSize.LARGE;
import static name.rhyn.sandpit.java.datatypes.ClothingSize.MEDIUM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import name.rhyn.sandpit.java.datatypes.ClothingSize;
import org.junit.jupiter.api.Test;

class SwitchStatementTest {

  public static final String ONE = "One";
  public static final String TWO = "Two";
  public static final String THREE = "Three";
  private static final String ERROR_MESSAGE = "Argument not recognized";

  @Test
  void enhancedSwitchWithByteResultTwo() {
    byte two = 2;
    String result = enhancedSwitchByte(two);

    assertEquals(TWO, result);
  }

  @Test
  void enhancedSwitchWithByteException() {
    byte four = 4;

    assertThrows(IllegalArgumentException.class, () -> enhancedSwitchByte(four));
  }

  private String enhancedSwitchByte(byte b) {
    // This is an enhanced switch statement. It's case end is defined by the semicolon of the function.
    return switch (b) { // byte, short, char or int
      default -> throw new IllegalArgumentException(ERROR_MESSAGE); // optional default case
      case 1 -> ONE;
      case 3 -> {
        System.out.println("3");
        yield THREE; // yield is used in the block to return a value.
      }
      case 2 -> TWO;
    };
  }

  @Test
  void enhancedSwitchWithChars() {
    assertEquals(10, enhancedSwitchChar('r'));
    assertEquals(10, enhancedSwitchChar('s'));
    assertEquals(29, enhancedSwitchChar('t'));
    assertEquals(20, enhancedSwitchChar('o'));
  }

  @Test
  void enhancedSwitchWithCharException() {
    assertThrows(IllegalArgumentException.class, () -> enhancedSwitchChar('x'));
  }

  private int enhancedSwitchChar(char c) {
    return switch (c) {
      case 'r', 's' -> 10; // Separate chases by ,
      case 't' -> {
        System.out.println("Case t");
        yield Character.getNumericValue(c);
      }
      case 'o' -> 20;
      default -> throw new IllegalArgumentException(ERROR_MESSAGE);
    };
  }

  @Test
  void switchCharWithC() {
    switchChar('c');
  }

  private void switchChar(char c) {
    switch (c) {
      case 'a', 'b', 'c':
        System.out.println("A B C");
        break;
      case 'j', 'k':
        System.out.println("J or K");
        break;
      // default: Not required
    }
  }

  @Test
  void enhancedSwitchEnumWithMedium() {
    assertEquals(450, switchEnum(MEDIUM));
  }

  @Test
  void enhancedSwitchEnumWithValue() {
    // special case
    switch (MEDIUM) {
      case EXTRA_SMALL -> System.out.println("Size XS");
      case SMALL -> System.out.println("Size S");
      case MEDIUM -> System.out.println("Size M");
    }
  }

  private int switchEnum(ClothingSize clothingSize) {
    int result = -1;
    switch (clothingSize) {
      case EXTRA_LARGE, SMALL:
        result = EXTRA_LARGE.getInternalCode();
        break;
      case MEDIUM:
        result = MEDIUM.getInternalCode();
        break;
      case LARGE:
        result = LARGE.getInternalCode();
        break;
      // case ClothingSize.EXTRA_LARGE: Not allowed
//            default: not required because all cases of the enum TrafficLight are covered.
    }
    return result;
  }

  @Test
  void enhancedSwitchStringWithValues() {
    assertEquals(1, enhancedSwitchString(ONE));
    assertEquals(2, enhancedSwitchString(TWO));
  }

  @Test
  void enhancedSwitchStringWithWrongArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> enhancedSwitchString("Wrong"));
  }

  private int enhancedSwitchString(String value) {
    String caseThree = THREE;
    return switch (value) {
      case ONE -> 1;
      case TWO -> 2;
      // case caseThree: Compile error. Constant expression required
      default -> throw new IllegalArgumentException(ERROR_MESSAGE);
    }; // IMPORTANT: As soon as result is defined the semicolon is required at the end.
  }

  @Test
  void switchWithNull() {
    String a = null;
    assertThrows(NullPointerException.class, () -> {
      var result = switch (a) {
        case "Test" -> "One";
        case "More" -> null;
        case "Demo" -> "Three";
        default -> "Unknown";
      };
    });
  }

  @Test
  void enhancedSwitchWithInt() {
    switchWithBreak(4);
    switchWithBreak(3);
  }

  private void switchWithBreak(int x) {
    switch (x) { // Nothing is returned from the switch statement
      case 3, 4 -> {
        System.out.println("Value is " + x);
        if (x == 3) {
          break;
        }
        System.out.println("After break");
      }
      case 5 -> System.out.println("Case Five");
      case 6 -> {
      } // Empty block is valid
    }
  }

}
