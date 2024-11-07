package name.rhyn.sandpit.java.oop.interfaces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InterfacesTest {

  @Test
  void variables() {
    assertEquals(100, InterfaceVariables.maximum);
    assertEquals(100, InterfaceVariables.partlyMaximum);
    assertEquals(100, InterfaceVariables.completeMaximum);
  }

  @Test
  void methods() {
    InterfaceMethods interfaceMethods = new InterfaceMethods() {
      @Override
      public String respond() {
        return "Yo";
      }

      @Override
      public String respond2() {
        return "Hello";
      }
    };

    assertEquals("Hi", interfaceMethods.respondDefault());
    assertEquals("Yo", interfaceMethods.respond());
    assertEquals("Hello", interfaceMethods.respond2());
  }

  @Test
  void staticMethods() {
    assertEquals("This static method is public", StaticMethods.fullyAccessible());

    assertEquals("This static method has a default access and uses a private static method",
        StaticMethods.withinPackageAccessible());
  }

}
