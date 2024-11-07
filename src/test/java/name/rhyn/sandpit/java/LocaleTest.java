package name.rhyn.sandpit.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import org.junit.jupiter.api.Test;

class LocaleTest {

  @Test
  void locale() {
    Locale it = new Locale("it");
    Locale de = new Locale.Builder().setLanguage("de").build();
    Locale ch = new Locale.Builder().setLanguage("de").setRegion("CH").build();
    Locale germany = Locale.GERMANY;
    Locale french = new Locale("fr", "FR");

    assertEquals("it", it.getLanguage());
    assertEquals("de", de.getLanguage());
    assertEquals("de", ch.getLanguage());
    assertEquals("CH", ch.getCountry());
    assertEquals("de_DE", germany.toString());
    assertEquals("fr", french.getLanguage());
    assertEquals("FR", french.getCountry());
  }

}
