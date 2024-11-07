package name.rhyn.sandpit.java.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import name.rhyn.sandpit.java.datatypes.record.Passenger;
import org.junit.jupiter.api.Test;

class ArraysTest {

  @Test
  void declareAndThenInitialize() {
    // Declare an array of integers
    int[] numbers;

    // Allocate memory for 5 integers
    numbers = new int[5];

    // Initialize the array elements
    numbers[0] = 10;
    numbers[1] = 20;
    numbers[2] = 30;
    numbers[3] = 40;
    numbers[4] = 50;

    assertEquals(5, numbers.length);
    assertEquals(30, numbers[2]);
  }

  @Test
  void declareAndInitialize() {
    // Declare and initialize an array of Passengers
    Passenger[] passengers = {new Passenger("a", "A"), new Passenger("b", "B"),
        new Passenger("c", "C")};

    // Access and verify elements of the array
    assertEquals(3, passengers.length);
    assertEquals("Passenger[firstName=a,lastName=A,seatNumber=No seat]", passengers[0].toString());
  }

  @Test
  void multiDimension() {
    boolean[][][] threeDimension = new boolean[3][2][4];

    threeDimension[0] = new boolean[][]{{true, false}};

    assertEquals(3, threeDimension.length);
    assertTrue(threeDimension[0][0][0]);
    assertFalse(threeDimension[0][0][1]);
  }

}
