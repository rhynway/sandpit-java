package name.rhyn.sandpit.java.oop.inheritance.sealed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import name.rhyn.sandpit.java.oop.inheritance.sealed.Bicycle.CityBike;
import name.rhyn.sandpit.java.oop.inheritance.sealed.Bicycle.EnduroBike;
import name.rhyn.sandpit.java.oop.inheritance.sealed.Bicycle.MountainBike;
import name.rhyn.sandpit.java.oop.inheritance.sealed.Bicycle.RoadBike;
import org.junit.jupiter.api.Test;

/**
 * - A sealed subclass must have a final, sealed, or non-sealed modifier.
 * - A sealed class may be extended by another sealed class.
 * - A sealed class can be declared abstract.
 * - In an unnamed module, a sealed class must include all its subclasses within the same package.
 */
class SealedTest {

  @Test
  void sealedPlanet() {

    Food strawberry = new Strawberry();
    Blueberry blueberry = new Blueberry();

    Vegetables vegetables = new Vegetables();

    Meet meet = new Meet() {

    };

    assertNotNull(strawberry);
    assertNotNull(blueberry);
    assertNotNull(vegetables);
    assertNotNull(meet);

  }

  @Test
  void sealedBicycle() {
    Bicycle bicycle = new Bicycle(28, 2.0f);
    CityBike cityBike = bicycle.new CityBike(28, 2.1f);
    MountainBike mountainBike = bicycle.new MountainBike(29, 2.6f);
    EnduroBike enduroBike = bicycle.new EnduroBike(27.5f, 2.4f);
    RoadBike roadBike = new RoadBike(28, 1.6f);

    assertEquals(28, bicycle.getWheelSize());
    assertEquals(2.0f, bicycle.getTireDiameter());
    assertEquals(28, cityBike.getWheelSize());
    assertEquals(29, mountainBike.getWheelSize());
    assertEquals(27.5f, enduroBike.getWheelSize());
    assertEquals(28, roadBike.getWheelSize());
  }

}
