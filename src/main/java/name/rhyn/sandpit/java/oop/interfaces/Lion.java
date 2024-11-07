package name.rhyn.sandpit.java.oop.interfaces;

public interface Lion {

  String roar();

  default void drink() {
  }

  String toString(); // Not considered abstract because it matches the one from Object

  boolean equals(Object o); // Not considered abstract because it matches the one from Object

  int hashCode(); // Not considered abstract because it matches the one from Object

}
