package name.rhyn.sandpit.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Search sequence
 * 1. Exact match with language or language and country
 * 2. When no match then only the language is searched
 * 3. When no match then the default language is searched
 * 4. When no match then the file witout language is used.
 */
class ResourcesTest {

  private static final String BUNDLE_NAME = "resources";

  private static final String KEY_ONE = "key.one";
  private static final String KEY_TWO = "key.two";

  @BeforeAll
  public static void setup() {
    Locale.setDefault(Locale.ENGLISH);
  }

  @Test
  void resourcesDefault() {
    ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

    assertEquals("Learning Java", bundle.getString(KEY_ONE));
    assertEquals("default", bundle.getString(KEY_TWO));
  }

  @Test
  void resources_de() {
    Locale de = new Locale("de");
    ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, de);

    assertEquals("Java lernen", bundle.getString(KEY_ONE));
    assertEquals("de", bundle.getString(KEY_TWO));
  }

  @Test
  void resources_deCH() {
    Locale deCH = new Locale("de", "CH");
    var bundle = ResourceBundle.getBundle(BUNDLE_NAME, deCH);

    assertEquals("Java lehrä", bundle.getString(KEY_ONE));
    assertEquals("de_CH", bundle.getString(KEY_TWO));
  }

  @Test
  void resources_fr() {
    var bundle = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("fr"));

    assertEquals("Learning Java", bundle.getString(KEY_ONE));
    assertEquals("en", bundle.getString(KEY_TWO));
  }

  @Test
  void resourcesNotExisting() {
    assertThrows(MissingResourceException.class, () -> {
      ResourceBundle.getBundle("Not existing");
    });
  }

}
