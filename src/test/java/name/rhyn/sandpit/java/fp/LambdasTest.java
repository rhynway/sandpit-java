package name.rhyn.sandpit.java.fp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import name.rhyn.sandpit.java.datatypes.record.Passenger;
import name.rhyn.sandpit.java.fp.model.Point;
import org.junit.jupiter.api.Test;

/**
 * - Lambdas con only implement interfaces. Anonymous class can be subclasses of concrete or
 * abstract classes and can pass arguments to a parent constructor
 * - Lambdas can only implement interfaces with one method.
 * - Lambda expression are object expressions that implement an interface perhaps annotated with
 * '@FunctionalInterface'
 */
class LambdasTest {

  @Test
  void functionalInterface() {
    MyLambda<String> myLambda = (text) -> "MyLambda: " + text;

    assertEquals("MyLambda: Hello", myLambda.process("Hello"));
  }

  @Test
  void function() {
    // Has an argument and a returned value
    Function<LocalDate, Integer> first = (localDate) -> localDate.getDayOfMonth() + 50;

    assertEquals(51, first.apply(LocalDate.of(2024, 8, 1)));
  }

  @Test
  void toLongFunction() {
    ToLongFunction<String> convert = (s) -> Long.parseLong(s) + 11;
    // ToIntFunction
    // ToDoubleFunction

    assertEquals(20, convert.applyAsLong("9"));
  }

  @Test
  void toIntBiFunction() {
    ToIntBiFunction<LocalDate, Integer> convert = (date, days) -> date.getDayOfMonth() + days;
    // ToLongBiFunction
    // ToDoubleBiFunction

    assertEquals(18, convert.applyAsInt(LocalDate.of(2024, 6, 15), 3));
  }

  @Test
  void doubeToIntFunction() {
    DoubleToIntFunction function = d -> ((Double) d).intValue() + 100;

    assertEquals(139, function.applyAsInt(39.987));
  }

  @Test
  void biFunction() {
    BiFunction<LocalDate, Integer, String> convert = (d, n) -> "Date " + n + " is " + d.toString();

    assertEquals("Date 4 is 2024-07-24", convert.apply(LocalDate.of(2024, 7, 24), 4));
  }

  @Test
  void predicate() {
    Predicate<String> notLongerThanThree = (String s) -> {
      return s.length() <= 3;
    };

    assertTrue(notLongerThanThree.test("Hi"));
    assertFalse(notLongerThanThree.test("Hello"));

    Predicate<String> simpler = (String s) -> s.length() <= 3;
    Predicate<String> evenSimpler = (var s) -> s.length() <= 3;
    Predicate<String> evenMoreSimple = (s) -> s.length() <= 3;
    Predicate<String> simplest = s -> s.length() <= 3;
  }

  @Test
  void biPredicate() {
    BiPredicate<LocalDate, Integer> isSpecificDay = (LocalDate ld, Integer days) ->
        ld.getDayOfMonth() == days;

    assertTrue(isSpecificDay.test(LocalDate.of(2024, 4, 2), 2));
  }

  @Test
  void biConsumer() {
    // To mix parameter style like var and Passenger is not allowed
    BiConsumer<Passenger, Integer> movePassenger = (var passenger, var delta) -> System.out.println(
        passenger.seatNumber() + " moved by " + delta);

    var passenger = new Passenger("Peter", "Muster", "2a");
    movePassenger.accept(passenger, 2);
  }


  @Test
  void consumer() {
    Consumer<Long> second = (var l) -> System.out.println("Amount is " + l); // Returns void

    second.accept(300L);
  }

  @Test
  void longConsumer() {
    // Has an accept method
    LongConsumer longConsumer = (long l) -> System.out.println("long is " + l);

    longConsumer.accept(13);
  }

  @Test
  void supplier() {
    Supplier<String> supplier = () -> "Provides a value";

    assertEquals("Provides a value", supplier.get());
  }

  /**
   * UnaryOperator extends the Function interface
   */
  @Test
  void unaryOperator() {
    // Has an argument and returns a value
    UnaryOperator<Integer> multiplyByThree = (value) -> value * 3;

    assertEquals(9, multiplyByThree.apply(3));
  }

  @Test
  void binaryOperator() {
    BinaryOperator<Integer> binaryOperator = (a, b) -> 2 + a + b;

    assertEquals(9, binaryOperator.apply(3, 4));
  }


  @Test
  void javaFunctionalInterfaces() {
    Runnable runnable = () -> {
      System.out.println("Runnable runs");
    };

    runnable.run();

    Callable<String> callable = () -> "Some value";
    try {
      assertEquals("Some value", callable.call());
    } catch (Exception e) {
      fail(e);
    }

    // Could be simplified to Comparator.comparing(Passenger::firstName)
    Comparator<Passenger> passengerComparator = (p1, p2) -> p1.firstName()
        .compareTo(p2.firstName());

    Passenger passengerOne = new Passenger("Rolf", "Golf");
    Passenger passengerTwo = new Passenger("Peter", "Meter");
    assertEquals(2, passengerComparator.compare(passengerOne, passengerTwo));
  }

  @Test
  void methodReference() {
    // Method reference must match the arguments
    Function<Passenger, String> firstNameFunction = Passenger::firstName;

    Passenger firstPassenger = new Passenger("Sandra", "Miller");
    assertEquals("FirstName: Sandra", firstNameFunction.apply(firstPassenger));
  }

  @Test
  void lambdaUseInMapping() {
    List<String> words = List.of("Abc", "Abcd", "Abcde", "abcdef");

    Function<String, String> shorten = (text) -> text.substring(0, 2);
    List<String> customersUpperCase = words.stream().map(shorten).toList();

    assertEquals(List.of("Ab", "Ab", "Ab", "ab"), customersUpperCase);
  }

  @Test
  void callDifferentMethods() {
    Point point = new Point();

    BiConsumer<Point, Integer> setOne = Point::setOne;
    setOne.accept(point, 13);

    BiConsumer<Point, Integer> setTwo = Point::setTwo;
    setTwo.accept(point, 22);

    assertEquals(13, point.getOne());
    assertEquals(22, point.getTwo());

    Function<Point, Integer> getOne = Point::getOne;
    assertEquals(13, getOne.apply(point));
  }

  @Test
  void complexFunctionalInterface() {
    Play p = () -> "A functional interface with many methods";

    assertEquals("A functional interface with many methods", p.fun());
    p.play();
    Play.baseball();
  }

}
