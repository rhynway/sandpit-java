package name.rhyn.sandpit.java.referencetypes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * The String class is immutable. String literals are stored in a special area of the Java heap
 * known as the String Constant Pool. This helps in saving memory as multiple references can point
 * to the same String object in the pool.
 */
class StringTest {

  private final String learning = "Learning";

  @Test
  void constantPool() {
    String learning_1 = "Learning";
    String learning_2 = "Learning";
    String learn = "Learn";
    learn = learn + "ing";
    // new is required to get a new object saved
    String learning_3 = new String((learning_2));

    // Both references are pointing to the same string in the constant pool
    assertTrue(learning_1 == learning_2);
    assertFalse(learning_2 == learn); // It's not the same reference
    assertEquals(learning_2, learn); // but the same content
    assertFalse(learning_2 == learning_3);

    final String first = "Learn";
    String dynamicLearning = first + "ing"; // Optimized into the same constant as s1
    assertTrue(learning_2 == dynamicLearning);

    String s6 = learn.intern(); // Searching for such a constant and references it or creates it.
    assertTrue(learning_1 == s6);
  }

  @Test
  void methodCharAt() {
    char n = learning.charAt(4); // From CharSequence interface

    assertEquals('n', n);
  }

  @Test
  void methodLength() {
    int length = learning.length();

    assertEquals(8, length);
  }

  @Test
  void methodSubstring() {
    String substring = learning.substring(5, 8);

    assertEquals("ing", substring);
  }

  @Test
  void methodContains() {
    boolean contains = learning.contains("e");

    assertTrue(contains);
  }

  @Test
  void methodIndexOf() {
    int indexOf = learning.indexOf('i');

    assertEquals(5, indexOf);
  }

  @Test
  void methodStartsWith() {
    boolean startsWithLe = learning.startsWith("Le");

    assertTrue(startsWithLe);
  }

  @Test
  void methodToUpperCase() {
    String upperCase = learning.toUpperCase();

    assertEquals("LEARNING", upperCase);
  }

  @Test
  void methodIsEmptyAndisBlank() {
    String empty = "";
    assertTrue(empty.isEmpty());
    String blank = " \n \t";
    assertFalse(blank.isEmpty());
    assertTrue(blank.isBlank()); // Check the list of whitespaces in the Java documentation
  }

  @Test
  void methodConcatAndConcatination() {
    String concated = learning.concat(" Java");

    assertEquals("Learning Java", concated);

    String calculated = 3 + 6 + " result";

    assertEquals("9 result", calculated);

    String notCalculated = "result " + 3 + 6;

    assertEquals("result 36", notCalculated);
  }

  @Test
  void methodEqualsIgnoreCase() {
    boolean equalsIgnoreCase = learning.equalsIgnoreCase("LeArNiNg");

    assertTrue(equalsIgnoreCase);
  }

  @Test
  void methodReplace() {
    String replaced = learning.replace('n', '*');

    assertEquals("Lear*i*g", replaced);
  }

  @Test
  void methodCompareTo() {
    int compared = learning.compareTo("Learning"); // From Comparable interface

    assertEquals(0, compared);
  }

  @Test
  void methodChars() {
    IntStream chars = learning.chars();

    assertArrayEquals(new int[]{76, 101, 97, 114, 110, 105, 110, 103}, chars.toArray());
  }

  @Test
  void methodIndent() {
    String indented = learning.indent(4);

    assertEquals("    Learning\n", indented);
  }

  @Test
  void methodTrimAndStrip() {
    char whitespace = '\u2000'; // EN QUAD
    assertTrue(Character.isWhitespace(whitespace));

    String text = whitespace + "text" + whitespace;

    assertEquals(" text ", text);
    assertEquals(" text ", text.trim());
    assertEquals("text", text.strip());
  }

  @Test
  void methodRepeat() {
    String repeated = learning.repeat(3);

    assertEquals("LearningLearningLearning", repeated);
  }

  @Test
  void methodSplit() {
    String s = "This is the sandpit project";

    String[] strings = s.split("\\s");

    assertEquals(5, strings.length);
  }

  @Test
  void whitespaces() {
    char space = ' ';
    assertTrue(Character.isWhitespace(space));

    char horizontalTab = '\t';
    assertTrue(Character.isWhitespace(horizontalTab));

    char unixNewline = '\n';
    assertTrue(Character.isWhitespace(unixNewline));

    char verticalTabulation = '\u000B';
    assertTrue(Character.isWhitespace(verticalTabulation));

    char formFeed = '\f';
    assertTrue(Character.isWhitespace(formFeed));

    char carriageReturn = '\r';
    assertTrue(Character.isWhitespace(carriageReturn));

    char fileSeparator = '\u001C';
    assertTrue(Character.isWhitespace(fileSeparator));

    char groupSeparator = '\u001D';
    assertTrue(Character.isWhitespace(groupSeparator));

    char recordSeparator = '\u001E';
    assertTrue(Character.isWhitespace(recordSeparator));

    char unitSeparator = '\u001F';
    assertTrue(Character.isWhitespace(unitSeparator));

    // There are even more. Just see the javadoc of the isWhitespace method
  }

  @Test
  void staticJoin() {
    String joined = String.join(" - ", new CharSequence[]{"Java", "Certificate"});

    assertEquals("Java - Certificate", joined);
  }

  @Test
  void testUnicodeAndUtf16() {
    // Unicode uses one or two char values
    char f = 'F';   // Uses one byte
    String fuji = "🗻"; // Uses two byte
    char[] fChars = {f};
    assertEquals(1, fChars.length);
    char[] fujiChars = fuji.toCharArray();
    assertEquals(2, fujiChars.length);
  }

  @Test
  void textBlock() {
    // On the opening tripple quote only whitespaces are allowed
    var expected = """
        cheetah
        cub""";// The ending tripple quotes can be on the same line

    // A newline is always represented by a \n in a text block
    assertEquals(expected, "cheetah\ncub");
    assertEquals(expected, "cheetah\ncub".translateEscapes());

    // translateEscapes transforms \\n into a newline
    assertEquals(expected, "cheetah\\ncub".translateEscapes());

    var text = """
        "ape"
        "baboon"
        "gorilla"
        """;
    // There are four lines printed when printed out
    assertEquals(4, text.split("\n", -1).length);
    List<String> lines = text.lines().toList();
    assertEquals(3, lines.size());

    var quotes = """
            \"The Quotes that Could\"
        \"\"\"
        """;
    var sameQuotes = """
            "The Quotes that Could"
        \"""
        """;
    assertEquals(quotes, sameQuotes);// Two escaping backslashes can be removed
  }

  @Test
  void textBlockWithEssentialAndIncidentalWhitespaces() {
    // @Formatter:off
    // Contains only essential whitespace
    var onlyEssentialWhitespaces = """

        green
          yellow
    """; // The closing delimiter must be on the same column as var.
    // @Formatter:on

    var blockWithIncidentalWhitespaces = """
        
            green
              yellow
        """;
    assertEquals(onlyEssentialWhitespaces, blockWithIncidentalWhitespaces);
  }

  @Test
  void textBlockMethodStrip() {
    String differentStrip = """
        This is a text  \s
        \f\f on three lines
        """;
    String differentStripFirst = differentStrip.stripIndent();
    assertEquals("This is a text\n\f\f on three lines\n", differentStripFirst);

    String differentStripResult = differentStripFirst.stripTrailing();
    assertEquals("This is a text\n\f\f on three lines", differentStripResult);
  }

  @Test
  void textBlockWithNewlines() {
    String replacesNewlines = """
        n-\n, r-\r, rn-\r\n, xx
        xx\
        xx""";

    String indented = replacesNewlines.indent(0); // Negative numbers a valid too

    // The indent method call replaced some of the escaped characters with \n and added one at the last line
    String expected = """
        n-
        , r-
        , rn-
        , xx
        xxxx
        """;

    assertEquals(expected, indented);
  }

  @Test
  void textBlockWithLineConcatination() {
    // The \ concatinates the text
    String oneLine = """
        This is text \
        that will appear \
        on one line""";

    assertEquals("This is text that will appear on one line", oneLine);
  }

}
