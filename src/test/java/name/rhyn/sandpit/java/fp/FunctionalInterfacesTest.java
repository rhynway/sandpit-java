package name.rhyn.sandpit.java.fp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import name.rhyn.sandpit.java.oop.interfaces.Lion;
import org.junit.jupiter.api.Test;

class FunctionalInterfacesTest {

  @Test
  void simpleFunctionalInterface() {
    Play play = () -> "Hello";

    assertEquals("Hello", play.fun()); // fun is the only abstract method
  }

  @Test
  void complexFunctionalInterface() {
    Calculator calculator = new Calculator();

    assertEquals(8, calculator.calculate((k, p) -> p + k + 1, 2, 5));

    AddNumbers multiply = new AddNumbers() {
      @Override
      public int add(int x, int y) {
        return multiply(x, y);
      }
    };

    assertEquals(12, calculator.calculate(multiply, 3, 4));

    assertEquals(17, AddNumbers.subtract(20, 3));
  }

  @Test
  void functionalInterfaceBeAware() {
    Lion lion = () -> "grrrr";

    assertEquals("grrrr", lion.roar());
    assertTrue(lion.toString()
        .startsWith("name.rhyn.sandpit.java.fp.FunctionalInterfacesTest"));
  }

}
