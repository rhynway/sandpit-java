package name.rhyn.sandpit.java.fp;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summingInt;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import name.rhyn.sandpit.java.fp.model.Player;
import name.rhyn.sandpit.java.fp.model.Result;
import name.rhyn.sandpit.java.fp.model.Scores;
import org.junit.jupiter.api.Test;

/**
 * A stream can consist of three parts. Source, intermediate operation, and terminal operation.
 * <p>
 * - A stream can only be processed once
 * - A source may return an infinite stream.
 * - An intermediate operation may return an infinite stream.
 */

class StreamTest {

  @Test
  void streamAndFilter() {
    List<Character> characters = List.of('a', 'b', 'c', 'd', 'e');

    List<Character> smallerThanC = characters
        .stream()
        .filter(e -> e < 'c')
        .toList();

    assertEquals(List.of('a', 'b'), smallerThanC);
  }

  @Test
  void parallelStream() {
    List<Character> characters = List.of('a', 'b', 'c', 'd', 'e');

    List<String> converted = characters
        .parallelStream()
        .filter((Character c) -> c <= 'c') // Brackets are required when using the type
        //.filter(c -> c <= 'c') or
        .peek(System.out::println) // prints a then b and then c
        .sequential()
        .map(c -> "" + c)
        .toList();

    assertEquals(List.of("a", "b", "c"), converted);
  }

  @Test
  void streamAndTakeWhile() {
    List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .takeWhile(n -> n <= 5)
        .toList();

    assertEquals(List.of(1, 2, 3, 4, 5), numbers);
  }

  @Test
  void streamAndDropWhile() {
    List<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .dropWhile(n -> n <= 5)
        .toList();

    assertEquals(List.of(6, 7, 8, 9, 10), numbers);
  }

  @Test
  void concatAndReduceAsInt() {
    Stream<Integer> oddNumbers = Stream.iterate(1, a -> a + 2);
    Stream<Integer> evenNumbers = Stream.iterate(2, a -> a + 2);

    Integer sumAsInteger = Stream.concat(oddNumbers, evenNumbers)
        .limit(3) // Only values from oddsTwo are used. 1, 3, 5
        .reduce(0, Integer::sum); // identity is int

    assertEquals(9, sumAsInteger);
  }

  @Test
  void concatAndReduceAsDouble() {
    Stream<Integer> oddsTwo = Stream.iterate(1, a -> a + 2);
    Stream<Integer> evensTwo = Stream.iterate(2, a -> a + 2);

    Double sumAsDouble = Stream.concat(oddsTwo, evensTwo)
        .limit(3)
        .reduce(0.0, Double::sum, Double::sum); // identity is type double

    assertEquals(9.0, sumAsDouble);
  }

  @Test
  void reduce() {
    OptionalInt sumOfOneToEleven = IntStream.range(1, 11)
        .reduce(Integer::sum);

    assertTrue(sumOfOneToEleven.isPresent());
    assertEquals(55, sumOfOneToEleven.getAsInt());

    int initialValueHigher = IntStream.range(1, 11)
        .reduce(100, Integer::sum);
    assertEquals(155, initialValueHigher);
  }

  @Test
  void reduceWithAccumulatorAndCombiner() {
    Player[] players = {new Player("a", 100), new Player("b", 299), new Player("c", 45)};

    Result allPlayers = Stream.of(players)
        .reduce(new Result(0, 0), (Result currentResult, Player player) -> new Result(
                currentResult.getValue() + player.getPoints(), currentResult.getCount() + 1),
            (r1, r2) -> new Result(r1.getValue() + r2.getValue(), r1.getCount() + r2.getCount()));

    assertEquals(3, allPlayers.getCount());
    assertEquals(444, allPlayers.getValue());

    Scores scoreOfAllPlayers = Stream.of(players)
        .reduce(new Scores(0, 0), (Scores currentResult, Player player) -> new Scores(
                currentResult.points() + player.getPoints(), currentResult.numberOfPlayers() + 1),
            (r1, r2) -> new Scores(r1.points() + r2.points(),
                r1.numberOfPlayers() + r2.numberOfPlayers()));

    assertEquals(3, scoreOfAllPlayers.numberOfPlayers());
    assertEquals(444, scoreOfAllPlayers.points());
  }


  @Test
  void mapToInt() {
    ToLongFunction<Integer> transformer = x -> x; // int applyAsInt(T value);

    var sum = Stream.of(3, 1, 4, 1, 5, 9)
        .limit(4)
        .peek(s -> System.out.println(s)) // Just to a Lambda expression
        .mapToLong(transformer)
        .peek(System.out::println)
        .sum();

    assertEquals(9, sum);
  }

  @Test
  void flatMapOnStreamOfStream() {
    Stream<Stream<Integer>> streamOfStream = Stream.of(Stream.of(8, 9), Stream.of(18, 19));

    var result = streamOfStream
        .flatMap(x -> x) // Creates a stream of four Integer. flatMap can return zero or more
        .toList();

    assertEquals(List.of(8, 9, 18, 19), result);
  }

  @Test
  void flatMapToIntOnStreamOfStream() {
    var smapperNumbers = List.of("4", "5");
    var higherNumbers = List.of("12", "13");

    var allNumbers = Stream.of(smapperNumbers, higherNumbers)
        .flatMapToInt(l -> l.stream().mapToInt(Integer::parseInt))
        .toArray();

    assertArrayEquals(new int[]{4, 5, 12, 13}, allNumbers);
  }

  @Test
  void average() {
    OptionalDouble average = DoubleStream.of(1, 2, 1, 3, 1, 4, 2)
        .average();

    assertTrue(average.isPresent());
    assertEquals(2.0, average.getAsDouble());

    var s1 = IntStream.empty();
    assertFalse(s1.average().isPresent());
  }

  @Test
  void count() {
    List<Double> processed = new ArrayList<>();
    DoubleStream doubles = DoubleStream.of(1, 2, 1, 3, 1, 4, 2);
    long countDoubles = doubles
        .peek(processed::add)
        .count(); // terminal operation. .count() Doesn't pull the data

    assertTrue(processed.isEmpty());
    assertEquals(7, countDoubles);
  }

  @Test
  void min() {
    OptionalInt minimum = IntStream.range(2, 5).min();

    assertTrue(minimum.isPresent());
    assertEquals(2, minimum.getAsInt());

    Optional<Integer> anotherMinimum = Stream.of(3, 4, 5).min(Integer::compareTo);
    assertTrue(anotherMinimum.isPresent());
    assertEquals(3, anotherMinimum.get());
  }

  @Test
  void max() {
    OptionalDouble maximum = DoubleStream.of(1, 2, 1, 3, 1, 4, 2)
        .max();

    assertEquals(4, maximum.getAsDouble());
  }

  @Test
  void sum() {
    double sum = DoubleStream.of(1, 2, 1, 3, 1, 4, 2)
        .sum();

    assertEquals(14.0, sum);
  }

  /**
   * findAny()
   * - may return any element on an ordered serial stream.
   * - may return any element on an unordered stream.
   * - may return any element on an ordered parallel stream.
   */
  @Test
  void findAny() {
    OptionalInt foundInSequential = IntStream.range(1, 10).findAny();

    assertTrue(foundInSequential.isPresent());
    assertEquals(1, foundInSequential.getAsInt()); // It's not guaranteed to be the first entry

    OptionalInt foundInParallel = IntStream.range(1, 10).parallel()
        .findAny(); // Can return any element

    assertTrue(foundInParallel.isPresent());
  }

  @Test
  void findFirst() {
    var list = List.of('c', 'b', 'a');
    Optional<Character> findFirst = list.stream().sorted().findFirst();

    assertEquals('a', findFirst.get());
  }

  @Test
  void groupingByStringLength() {
    List<String> names = List.of("Peter", "Franz", "Roger", "Hans", "Jan");
    Map<Boolean, List<String>> grouped = names.stream().collect(groupingBy(s -> s.length() > 4));

    assertEquals(List.of("Peter", "Franz", "Roger"), grouped.get(true));
    assertEquals(List.of("Hans", "Jan"), grouped.get(false));
  }

  @Test
  void groupingByBallotScore() {
    Stream<Ballot> ballots = Stream.of(
        new Ballot("Mario", 1, 10),
        new Ballot("Christina", 1, 8),
        new Ballot("Mario", 2, 9),
        new Ballot("Christina", 2, 8)
    );

    Map<String, Integer> scores = ballots.collect(
        groupingBy(Ballot::name, summingInt(Ballot::score)));

    assertEquals(19, scores.get("Mario"));
  }

  @Test
  void groupingByDigitOrUpperOrLowerCase() {
    List<String> strings = new ArrayList<>(List.of("Aa1", "Bb2", "Cc3"));

    Map<String, List<String>> groupedByString = strings.stream()
        .flatMap(code -> Stream.of(code.split("")))
        .collect(Collectors.groupingBy(character -> {
          if (Character.isDigit(character.charAt(0))) {
            return "Numbers";
          } else if (Character.isUpperCase(character.charAt(0))) {
            return "Uppercase";
          } else {
            return "Lowercase";
          }
        }));

    assertEquals(3, groupedByString.size());
    assertEquals(List.of("A", "B", "C"), groupedByString.get("Uppercase"));
    assertEquals(List.of("a", "b", "c"), groupedByString.get("Lowercase"));
    assertEquals(List.of("1", "2", "3"), groupedByString.get("Numbers"));
  }

  @Test
  void partitionedByStringLength() {
    List<String> names = List.of("Peter", "Franz", "Roger", "Hans", "Jan");
    Map<Boolean, List<String>> alsoGrouped = names.stream()
        .collect(partitioningBy(s -> s.length() > 4));

    assertEquals(List.of("Peter", "Franz", "Roger"), alsoGrouped.get(true));
  }

  @Test
  void partitionedByModuloTwo() {
    Map<Boolean, List<Integer>> evenAndOddNumbers = IntStream.range(1, 11)
        .boxed()
        .collect(Collectors.partitioningBy((Integer i) -> i % 2 == 0));

    assertEquals(List.of(2, 4, 6, 8, 10), evenAndOddNumbers.get(Boolean.TRUE));
    assertEquals(List.of(1, 3, 5, 7, 9), evenAndOddNumbers.get(Boolean.FALSE));
  }

  @Test
  void toMapMethod() {
    var words = Stream.of("speak", "bark", "meow", "growl");
    BinaryOperator<String> merge = (a, b) -> a.concat(",").concat(b);
    var map = words.collect(Collectors.toMap(
        String::length, k -> k, merge));

    assertEquals(2, map.size());
    assertEquals("bark,meow", map.get(4));
    assertEquals("speak,growl", map.get(5));
  }

  @Test
  void toList() {
    List<Integer> numbers = Stream.iterate(1, x -> x + 1)
        .limit(4)
        .collect(Collectors.toList());// Collectors.toList returns a modifiable list

    numbers.add(10);
    assertEquals(List.of(1, 2, 3, 4, 10), numbers);
  }

  @Test
  void summaryStatistics() {
    var numbers = LongStream.of(6, 10);
    var statistics = numbers.summaryStatistics();
    var average = statistics.getAverage();
    assertEquals(8.0, average);
  }

  @Test
  void anyMatch() {
    var numbers = Stream.iterate(1, i -> i + 1);
    var anyMatch = numbers.anyMatch(i -> i > 5);
    assertTrue(anyMatch);
  }

  @Test
  void allMatch() {
    boolean result = IntStream.iterate(1, i -> i + 2)
        .limit(4)
        .allMatch(x -> x % 2 == 0); // Pulls the data

    assertFalse(result);
  }

  @Test
  void forEachOrdered() {
    List<Integer> numbers = new ArrayList<>();
    IntStream.range(1, 5)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .forEachOrdered(numbers::add);

    assertEquals(List.of(4, 3, 2, 1), numbers);
  }

  @Test
  void boxed() {
    List<Integer> numbers = new ArrayList<>();
    IntStream.range(1, 5)// Contains primitive int
        .boxed() // Converted to a Stream<Integer>
        .forEachOrdered(numbers::add);

    assertEquals(List.of(1, 2, 3, 4), numbers);
  }

  @Test
  void generate() {
    int[] array = IntStream.iterate(1, v -> v < 10, v -> v + 2).toArray();
    int[] sameNumbers = IntStream.rangeClosed(1, 9).filter(v -> v % 2 != 0).toArray();
    int[] againNumbers = IntStream.range(0, 5).map(v -> v * 2 + 1).toArray();

    assertArrayEquals(new int[]{1, 3, 5, 7, 9}, array);
    assertArrayEquals(new int[]{1, 3, 5, 7, 9}, sameNumbers);
    assertArrayEquals(new int[]{1, 3, 5, 7, 9}, againNumbers);
  }

  @Test
  void flatMapToInt() {
    List<Character> characters = new ArrayList<>();
    Stream.of("Roger", "Helene", "Jane")
        .flatMapToInt(String::chars)
        .mapToObj(c -> (char) c)
        .forEach(characters::add);

    assertEquals(List.of('R', 'o', 'g', 'e', 'r', 'H', 'e', 'l', 'e', 'n', 'e', 'J', 'a', 'n', 'e'),
        characters);
  }

  @Test
  void streamSupport() {
    List<String> numbers = List.of("One", "Two", "Three", "Four");
    List<String> results = StreamSupport.stream(numbers.spliterator(), false)
        .map(entry -> "Value: " + entry).toList();

    assertEquals(List.of("Value: One", "Value: Two", "Value: Three", "Value: Four"), results);
  }

  @Test
  void random() {
    long count = new Random().ints(5).filter(i -> i % 2 == 0).count();

    assertTrue(count < 5);

    OptionalDouble average = new Random().doubles(-1.0, 1.0)
        .limit(1000) // When limit is removed then it runs forever
        .average();

    assertTrue(average.isPresent());
  }

  @Test
  void spliterator() {
    Spliterator.OfInt firstSpliterator = IntStream.range(0, 21).spliterator();
    Spliterator.OfInt secondSpliterator = firstSpliterator.trySplit();

    firstSpliterator.tryAdvance((int number) -> assertEquals(10, number));
    secondSpliterator.tryAdvance((int number) -> assertEquals(0, number));
  }

  @Test
  void distinct() {
    int[] numbers = {2, 3, 3, 4, 5, 5};
    int[] result = IntStream.of(numbers)
        .distinct().toArray();

    assertArrayEquals(new int[]{2, 3, 4, 5}, result);
  }

  @Test
  void collectWithSupplier() {
    Player[] players = {new Player("a", 100), new Player("b", 299), new Player("c", 45)};
    Result result = Stream.of(players)
        .collect(() -> new Result(0, 0),
            (Result currentResult, Player player) -> { // Return type of this BiConsumer is void
              // Here the values must be updated on the currentResult. Don't create a new object
              currentResult.setValue(currentResult.getValue() + player.getPoints());
              currentResult.setCount(currentResult.getCount() + 1);
            },
            (r1, r2) -> {
              // Here the values must be updated on first argument r1 not on the second one
              r1.setValue(r1.getValue() + r2.getValue());
              r1.setCount(r1.getCount() + r2.getCount());

            });

    assertEquals(3, result.getCount());
    assertEquals(444, result.getValue());
  }

  @Test
  void downstreamCollector() {
    Player[] players = {new Player("a", 100), new Player("b", 299), new Player("c", 45)};
    Map<String, String> result = Stream.of(players)
        .collect(Collectors.groupingBy(player -> {
              if (player.getPoints() < 100) {
                return "Low";
              }
              if (player.getPoints() < 200) {
                return "Middle";
              }
              return "High";
            },
            Collectors.mapping(Player::getName,
                Collectors.joining()))); // This is the downstream collector

    assertEquals("c", result.get("Low"));
    assertEquals("a", result.get("Middle"));
    assertEquals("b", result.get("High"));
  }

  @Test
  void parallelWithSynchronizedList() {
    var numbers = Collections.synchronizedList(new ArrayList<>());
    IntStream.range(1, 6)
        .parallel()
        .map(i -> {
          numbers.add(i);
          return i;
        })
        // They are printed in order, but it's not guaranteed to be the same in the numbers list
        .forEachOrdered(System.out::print);
    System.out.println("----");
    numbers.forEach(System.out::print);
  }

  record Ballot(String name, int judgeNumber, int score) {

  }

}
