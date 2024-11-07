package name.rhyn.sandpit.java.fp.model;

public class Player {

  private final String name;
  private final int points;

  public Player(String name, int points) {
    this.name = name;
    this.points = points;
  }

  public int getPoints() {
    return points;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name + " : " + points;
  }
}
