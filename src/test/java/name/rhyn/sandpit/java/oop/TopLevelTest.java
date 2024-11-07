package name.rhyn.sandpit.java.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import name.rhyn.sandpit.java.oop.TopLevel.Nested;
import org.junit.jupiter.api.Test;

class TopLevelTest {

  @Test
  void topLevelInstance() {
    var classFileStructure = new TopLevel(13);
    classFileStructure.startWithALetter();
    classFileStructure.$alsoValid();
    classFileStructure.___validToo();

    assertEquals(13, classFileStructure.getOuterValue());
    classFileStructure.setOuterValue(58);
    assertEquals(58, classFileStructure.getOuterValue());

    String message = classFileStructure.createMessageByLocalClass();
    assertEquals("Hello it's 15:48", message);

    Nested nestedInstance = classFileStructure.getNestedInstance();
    assertEquals(3, nestedInstance.getId());

    var second = new TopLevel("56");
    assertEquals(56, second.getOuterValue());
  }

  @Test
  void nestedInstance() {
    TopLevel.Nested nested = new TopLevel.Nested(4);
    TopLevel.Nested nestedTwo = new TopLevel.Nested(29);

    assertEquals(4, nested.getId());
    assertEquals(29, nestedTwo.getId());
  }

  @Test
  void innerInstance() {
    var outer = new TopLevel(1);
    TopLevel.InnerClass innerInstance = outer.createInnerInstance();
    TopLevel.InnerClass.show();

    assertEquals(1, innerInstance.getOuterId());

    outer.setOuterValue(20);
    TopLevel.InnerClass innerInstance2 = outer.createInnerInstance();

    assertEquals(1, innerInstance.getOuterId());
    assertEquals(20, innerInstance2.getOuterId());

    TopLevel root = new TopLevel(2);
    TopLevel.InnerClass innerClass = root.new InnerClass();
    assertEquals(2, innerClass.getOuterId());
  }

  @Test
  void otherInstance() {
    OtherClass differentClassInSameFile = new OtherClass();
    assertNotNull(differentClassInSameFile);
  }

}