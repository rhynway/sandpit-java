package name.rhyn.sandpit.java.oop.inheritance.sealed;

public sealed class Bicycle {

  private final float wheelSize;
  private final float tireDiameter;

  public Bicycle(float wheelSize, float tireDiameter) {
    this.wheelSize = wheelSize;
    this.tireDiameter = tireDiameter;
  }

  float getWheelSize() {
    return wheelSize;
  }

  float getTireDiameter() {
    return tireDiameter;
  }

  static non-sealed class RoadBike extends Bicycle {

    public RoadBike(float wheelSize, float diameter) {
      super(wheelSize, diameter);
    }
  }

  final class CityBike extends Bicycle {

    public CityBike(float wheelSize, float diameter) {
      super(wheelSize, diameter);
    }
  }

  sealed class MountainBike extends Bicycle {

    public MountainBike(float wheelSize, float diameter) {
      super(wheelSize, diameter);
    }
  }

  final class EnduroBike extends MountainBike {

    public EnduroBike(float wheelSize, float diameter) {
      super(wheelSize, diameter);
    }
  }

}
