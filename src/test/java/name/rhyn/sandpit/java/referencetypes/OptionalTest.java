package name.rhyn.sandpit.java.referencetypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class OptionalTest {

  @Test
  void optionalWithValue() {
    var text = "Some text";
    Optional<String> someText = Optional.of(text);

    assertTrue(someText.isPresent());
    someText.ifPresent(v -> assertEquals(text, v));
    assertEquals(text, someText.get());
    assertEquals(9, someText.map(String::length).get());
  }

  @Test
  void optionalWhichIsEmpty() {
    Optional<LocalDate> empty = Optional.empty();

    assertTrue(empty.isEmpty());
    LocalDate aDay = LocalDate.of(2024, 11, 4);
    assertEquals(aDay, empty.orElse(aDay));
    assertEquals(aDay, empty.orElseGet(() -> aDay));
    assertThrows(NoSuchElementException.class, () -> empty.orElseThrow());
  }

  @Test
  void optionalOfNull() {
    Optional<Double> number = Optional.<Double>ofNullable(null);

    assertTrue(number.isEmpty());
  }

}
