package name.rhyn.sandpit.java.referencetypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.zone.ZoneOffsetTransition;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * All classes in java.time are immutable and thread-safe. Exceptions are mutable There is no
 * constructor just factories Classes in the java.time package are based on the ISO 8601 calendar
 * system.
 */
class DateAndTimeTest {

  /**
   * Duration = Zeitdauer
   */
  @Test
  void duration_no_timeZone() {
    Duration days = Duration.ofDays(1);
    Duration hours = Duration.ofHours(2);
    Duration minutes = Duration.ofMinutes(3);
    Duration seconds = Duration.ofSeconds(4);
    Duration someSeconds = Duration.of(123, ChronoUnit.SECONDS);
    Duration secondsWithNegativeNanoseconds = Duration.ofSeconds(4, -567);
    Duration milliseconds = Duration.ofMillis(5);
    Duration nanoSeconds = Duration.ofNanos(6);

    assertEquals(123, someSeconds.getSeconds());
    assertEquals(24, days.toHours());
    assertEquals(120, hours.toMinutes());
    assertEquals(180, minutes.toSeconds());
    assertEquals(4000, seconds.toMillis());
    assertEquals(3999, secondsWithNegativeNanoseconds.toMillis());
    assertEquals(5, milliseconds.toMillis());
    assertEquals(6, nanoSeconds.toNanos());

    Duration duration = Duration.ofDays(
            1) // See Period for the date-based equivalent to this class.
        .plusHours(2)
        .plusMinutes(3)
        .plusSeconds(4)
        .plusMillis(500)
        .plusNanos(60000);

    assertEquals("PT24H", days.toString());
    assertEquals("PT26H3M4.50006S", duration.toString());
    assertEquals(93784, duration.getSeconds());

    //                          hours are ignored
    Duration thirtyFourMinutes = Duration.ofDays(1).ofHours(2).ofMinutes(3).ofMinutes(34);
    assertEquals(Duration.ofMinutes(34), thirtyFourMinutes);
  }

  @Test
  void period_no_timeZone() {
    Period oneYear = Period.ofYears(1);
    Period threeMonth = Period.ofMonths(3); // years, months, and days
    Period twoWeeks = Period.ofWeeks(2);
    Period fourDays = Period.ofDays(4);

    assertEquals("P1Y", oneYear.toString());
    assertEquals("P3M", threeMonth.toString());
    assertEquals("P14D", twoWeeks.toString());
    assertEquals("P4D", fourDays.toString());

    Period sixteenMonthFiveDays = Period.of(0, 16, 5);
    assertEquals("P16M5D", sixteenMonthFiveDays.toString());
    // Converts but it's not always possible because of leap year and different months
    Period normalized = sixteenMonthFiveDays.normalized();
    assertEquals("P1Y4M5D", normalized.toString());

    var from = LocalDate.of(2020, 1, 5);
    var till = LocalDate.of(2022, 1, 12);
    Period between = Period.between(from, till);

    assertEquals("P2Y7D", between.toString());

    Period oneYearNineMonthFiveDays = Period.parse("P1Y9M5D");
    assertEquals(1, oneYearNineMonthFiveDays.getYears());
    assertEquals(9, oneYearNineMonthFiveDays.getMonths());
    assertEquals(5, oneYearNineMonthFiveDays.getDays());

    var period = Period.ofYears(1).ofMonths(6).ofDays(3); // Note: This is not a builder
    assertEquals("P3D", period.toString());
  }

  @Test
  void localDate_no_timeZone() {
    LocalDate firstFebruary = LocalDate.of(2024, 1, 2);
    assertEquals("2024-01-02", firstFebruary.toString());

    int year = 2024;
    // int month = Month.MARCH;
    int day = 24;       //  month as int doesn't compile
    //LocalDate date = LocalDate.of(year, month, day);
    LocalDate firstMarch = LocalDate.of(year, Month.MARCH, day);

    assertEquals(2024, firstMarch.getYear());
    assertEquals("2024-03-22", firstMarch.minus(2, ChronoUnit.DAYS).toString());
    assertThrows(UnsupportedTemporalTypeException.class,
        () -> firstMarch.minus(Duration.ofDays(1)));

    LocalDate aDay = LocalDate.of(2020, 8, 2);
    LocalDate aDayBefore = aDay.minus(Period.ofDays(1));

    UnsupportedTemporalTypeException unsupportedTemporalTypeException = assertThrows(
        UnsupportedTemporalTypeException.class, () -> aDay.minus(Duration.ofDays(2)));
    assertEquals("Unsupported unit: Seconds", unsupportedTemporalTypeException.getMessage());
  }

  @Test
  void localTime_no_timeZone() {
    LocalTime inTheAfternoon = LocalTime.of(14, 38);
    LocalTime secondsLater = LocalTime.of(14, 38, 55, 400);

    assertEquals("14:38:55.000000400", secondsLater.toString());
    assertEquals("15:38", inTheAfternoon.plusHours(1).toString());
    assertEquals("14:40", inTheAfternoon.plusMinutes(2).toString());
    assertEquals("14:38:03", inTheAfternoon.plusSeconds(3).toString());
    assertEquals("14:38:00.000000004", inTheAfternoon.plusNanos(4).toString());

    LocalDate date = LocalDate.of(2020, 6, 5);
    var exception = assertThrows(DateTimeException.class, () -> LocalTime.from(date));
    assertEquals(
        "Unable to obtain LocalTime from TemporalAccessor: 2020-06-05 of type java.time.LocalDate",
        exception.getMessage());
  }

  @Test
  void localDateTime_no_timeZone() {
    LocalDateTime firstMarchMorning = LocalDateTime.of(2024, 1, 3, 9, 35);
    assertEquals("2024-01-03T09:35", firstMarchMorning.toString());

    assertThrows(DateTimeException.class, () -> LocalDate.of(2022, Month.FEBRUARY, 31));

    LocalDateTime aMoment = LocalDateTime.of(2020, 8, 2, 14, 55);
    // Method minus with a Duration argument.
    assertEquals("2020-08-02T14:54:58", aMoment.minus(Duration.ofSeconds(2)).toString());
  }

  @Test
  void chronoUnit_no_timeZone() {
    LocalDateTime twentySixtMarch = LocalDateTime.of(2018, 3, 26, 16, 35);
    LocalDateTime minutesLater = LocalDateTime.of(2018, 3, 26, 16, 55);
    long betweenInMinutes = ChronoUnit.MINUTES.between(twentySixtMarch, minutesLater);
    long betweenInHours = ChronoUnit.HOURS.between(twentySixtMarch, minutesLater);

    assertEquals(20, betweenInMinutes);
    assertEquals(0, betweenInHours);
  }

  @Test
  void zonedDateTime_with_timeZone() {
    // The ZoneId has an effect on the string representation.
    ZoneId europeZurich = ZoneId.of("Europe/Zurich");

    ZonedDateTime winterTime = ZonedDateTime.of(2021, 3, 28, 1, 57, 0, 0, europeZurich);
    Instant instant = winterTime.toInstant();
    ZoneOffset offset = winterTime.getOffset();

    assertEquals("2021-03-28T00:57:00Z", instant.toString());
    assertEquals("+01:00", offset.toString());
    assertEquals("2021-03-28T01:57+01:00[Europe/Zurich]", winterTime.toString());

    ZonedDateTime summerTime = winterTime.plusMinutes(5);

    assertEquals("+02:00", summerTime.getOffset().toString());
    assertEquals("2021-03-28T03:02+02:00[Europe/Zurich]", summerTime.toString());

    // Travelling
    LocalDateTime takeoff = LocalDateTime.of(2019, 7, 1, 14, 30);
    ZonedDateTime zurich = ZonedDateTime.of(takeoff, ZoneId.of("Europe/Zurich"));
    ZonedDateTime landing = zurich.plusHours(8).plusMinutes(37);

    assertEquals("2019-07-01T23:07+02:00[Europe/Zurich]", landing.toString()); // Zurich
    assertEquals("2019-07-01T14:07-07:00[America/Vancouver]",
        landing.withZoneSameInstant(ZoneId.of("America/Vancouver")).toString()); // Vancouver
  }

  @Test
  void format() {
    var date = LocalDateTime.of(2022, 1, 28, 17, 35);
    DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
    String formatted = date.format(isoDateTime);

    assertEquals("2022-01-28T17:35:00", formatted);

    LocalDate newYearsEve = LocalDate.of(2018, 12, 31);

    assertEquals("31.12.2018", newYearsEve.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    assertEquals("12/31/2018", newYearsEve.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
  }

  @Test
  void zoneIds() {
    Set<String> zoneIds = ZoneId.getAvailableZoneIds();

    assertEquals(603, zoneIds.size());

    ZoneId cet = ZoneId.of("CET");
    ZoneId europeZurich = ZoneId.of("Europe/Zurich");

    assertNotNull(cet);
    assertNotNull(europeZurich);

    // Contains overlaps and gaps.
    assertEquals(40, europeZurich.getRules().getTransitions().size());
    Instant aDayInJanuary = Instant.from(
        ZonedDateTime.of(2024, 1, 29, 17, 23, 30, 100, europeZurich));
    ZoneOffsetTransition previousTransition = europeZurich.getRules()
        .previousTransition(aDayInJanuary);
    ZoneOffsetTransition nextTransition = europeZurich.getRules().nextTransition(aDayInJanuary);

    // Overlap
    assertEquals("Transition[Overlap at 2023-10-29T03:00+02:00 to +01:00]",
        previousTransition.toString());
    // Gap
    assertEquals("Transition[Gap at 2024-03-31T02:00+01:00 to +02:00]", nextTransition.toString());
  }

  @Test
  void zonedDateTime() {
    LocalDateTime secondAugustLunch = LocalDateTime.of(2019, 8, 2, 12, 5);
    ZonedDateTime inTokyo = ZonedDateTime.of(secondAugustLunch, ZoneId.of("Asia/Tokyo"));
    Instant instant = inTokyo.toInstant();
    ZonedDateTime zdt2 = inTokyo.withZoneSameInstant(ZoneId.of("Australia/Sydney"));

    assertEquals("2019-08-02T12:05+09:00[Asia/Tokyo]", inTokyo.toString());
    assertEquals("2019-08-02T03:05:00Z", instant.toString()); // Time is in UTC
    assertEquals("2019-08-02T13:05+10:00[Australia/Sydney]", zdt2.toString());
  }

  @Test
  void chaining() {
    var thirteensJanuary = LocalDate.of(2024, 1, 13);
    var sameDate = thirteensJanuary.plusDays(2).minusDays(1).minusDays(1);

    assertEquals(thirteensJanuary, sameDate);
  }

}
