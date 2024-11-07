package name.rhyn.sandpit.java.datatypes;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * - enum types are implicitly final classes
 * - all constructors are implicitly private
 * - The type is protected against reflective access
 * - The type behaves properly when deserialized
 */
class EnumsTest {

  // In a enum the toString is already implemented
  // Enumerations are classes

  @Test
  void clothingSize() {
    ClothingSize size = ClothingSize.MEDIUM;

    assertEquals(2, size.ordinal());
    assertEquals("MEDIUM", size.toString());
    assertEquals("MEDIUM", size.name());
    assertEquals(450, size.getInternalCode());
  }

  @Test
  void constructors() {
    enum A {
      X1,
      X2(20),
      X3(30);

      private int key;

      // public, protected are not allowed
      // No modifier means private
      A() {
        key = -1;
      }

      private A(int key) {
        this.key = key;
      }

      @Override
      public String toString() {
        return super.toString() + " " + this.key;
      }

    }

    assertEquals(-1, A.X1.key);
    assertEquals(20, A.X2.key);
    assertEquals("X3 30", A.X3.toString());
  }

  @Test
  void staticValues() {
    ClothingSize[] sizes = ClothingSize.values();
    List<Integer> internalCodes = Arrays.stream(sizes)
        .map(ClothingSize::getInternalCode)
        .toList();

    assertEquals(List.of(100, 300, 450, 500, 600), internalCodes);
    assertEquals(5, sizes.length);
  }

  @Test
  void methodValueOf() {
    ClothingSize green = Enum.valueOf(ClothingSize.class, "MEDIUM");

    assertEquals(ClothingSize.MEDIUM, green);

    Enum<ClothingSize> asTypedEnum = ClothingSize.EXTRA_LARGE;
    assertEquals(4, asTypedEnum.ordinal());
  }

}
