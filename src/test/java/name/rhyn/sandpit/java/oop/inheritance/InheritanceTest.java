package name.rhyn.sandpit.java.oop.inheritance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class InheritanceTest {

  @Test
  void inheritanceMethodOverride() {
    ParentClass parentClass = new ParentClass();
    ParentClass parentReference = new ChildClass(1);

    try {
      ParentClass parentSelf = parentClass.getSelf();

      assertTrue(parentSelf instanceof ParentClass);

      ParentClass childSelf = parentReference.getSelf();

      assertTrue(childSelf instanceof ChildClass);

    } catch (IOException e) {
      fail(e);
    }
  }

  @Test
  void inheritance() {
    assertEquals("Interface static method", SomeInterface.staticMethod());
    assertEquals(10, SomeInterface.count);

    assertEquals("ChildClass static method", ChildClass.staticMethod());

    ChildClass childClass = new ChildClass();

    assertEquals("Interface default method", childClass.defaultMethod());
    assertEquals("ParentClass abstractMethod", childClass.abstractMethod());
    assertEquals(30, childClass.instanceCount);
    assertEquals(20, childClass.getParentCount());
    assertEquals(30, childClass.getCount());

    ParentClass parentClass = childClass;
    assertEquals(20, parentClass.instanceCount); // No override with variables
  }


}
