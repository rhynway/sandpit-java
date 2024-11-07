package name.rhyn.sandpit.java.datatypes.record;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class RecordTest {

  @Test
  void records() {
    Passenger cityBusPassenger = new Passenger("Thomas", "Muster");
    Passenger longDistanceBusPassenger = new Passenger("Peter", "Muster", "3b");

    // Override
    // assertEquals("Peter", passenger.getFirstName()); No get prefix
    assertEquals("FirstName: Peter", longDistanceBusPassenger.firstName());
    assertEquals("Passenger[firstName=Peter,lastName=Muster,seatNumber=3b]",
        longDistanceBusPassenger.toString());

    var allPassenger = List.of(cityBusPassenger, longDistanceBusPassenger);
    List<Passenger> passengersWithSeat = allPassenger.stream()
        .filter(p -> !p.seatNumber().equals("No seat"))
        .toList();

    assertEquals(1, passengersWithSeat.size());
  }

}
