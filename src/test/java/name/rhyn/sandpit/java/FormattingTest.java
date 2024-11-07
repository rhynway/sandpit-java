package name.rhyn.sandpit.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Date;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class FormattingTest {

  @Test
  void simpleDateFormat() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a",
        Locale.GERMAN);
    Date date = new Date(2024 - 1900, 2, 4, 9, 34); // March 4, 2024, 9:34 AM

    assertEquals("Montag, März 4, 2024 at 9:34 AM", simpleDateFormat.format(date));
  }

  @Test
  void dateTimeFormatterWithLocalDateTime() {
    LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 2, 3, 59);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
        "'At' hh:mm 'of day' d 'in' MMMM 'of' yyyy", Locale.GERMAN);

    assertEquals("At 03:59 of day 2 in Januar of 2024", formatter.format(localDateTime));
  }

  @Test
  void dateTimeFormatterWithLocalDate() {
    LocalDate firstEver = LocalDate.of(0, 1, 1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yy G", Locale.GERMAN);

    assertEquals("01 01 01 v. Chr.", formatter.format(firstEver));
  }

  @Test
  void dateTimeFormatterWithLocalTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm ss", Locale.GERMAN);
    LocalTime time = LocalTime.of(13, 14, 15);

    assertEquals("01:14 15", formatter.format(time));
    assertEquals("01:14 15", time.format(formatter));
  }

  @Test
  void dateTimeFormatterOfLocalizedDate() {
    LocalDate localDate = LocalDate.of(2022, Month.JANUARY, 23);
    LocalTime midnight = LocalTime.of(0, 0);
    LocalDateTime localDateTime = LocalDateTime.of(localDate, midnight);

    DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(
        Locale.GERMAN);

    assertEquals("Sonntag, 23. Januar 2022", f.format(localDate));
    assertEquals("Sonntag, 23. Januar 2022", f.format(localDateTime));
    assertThrows(UnsupportedTemporalTypeException.class, () -> f.format(midnight));
  }

  @Test
  void numberFormatter() {
    NumberFormat numberFormat = NumberFormat.getCompactNumberInstance(Locale.GERMAN,
        NumberFormat.Style.LONG);

    double allmostTwoMillion = 1_900_000.0;
    assertEquals("2 Million", numberFormat.format(allmostTwoMillion));

    assertEquals("£1.10", NumberFormat.getCurrencyInstance(Locale.UK).format(1.1));

    assertEquals("6,95 €", NumberFormat.getCurrencyInstance(Locale.GERMANY).format(6.95));
  }

  @Test
  void decimalFormat() {
    Locale switzerland = new Locale("de", "CH");
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(switzerland);
    DecimalFormat decimalFormat = new DecimalFormat("##,#00.#", symbols);

    assertEquals("06.9", decimalFormat.format(6.923));
    assertEquals("20’100", decimalFormat.format(20_100.05));
  }

  @Test
  void messageFormat() {
    assertEquals("At 2024-04-17T18:16 was it sunny",
        MessageFormat.format("At {0} was it {1}", LocalDateTime.of(2024, 4, 17, 18, 16), "sunny"));
  }

}
