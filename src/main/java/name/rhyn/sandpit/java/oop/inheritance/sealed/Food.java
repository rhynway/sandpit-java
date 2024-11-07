package name.rhyn.sandpit.java.oop.inheritance.sealed;

// Which modifiers can be assigned to a sealed subclass?
// final, sealed, or non-sealed
public abstract sealed class Food permits Fruits, Vegetables, Meet {

}

sealed class Fruits extends Food permits Strawberry, Blueberry {

}

final class Strawberry extends Fruits {

}

non-sealed class Blueberry extends Fruits {

}

final class Vegetables extends Food {

}

abstract non-sealed class Meet extends Food {

}




