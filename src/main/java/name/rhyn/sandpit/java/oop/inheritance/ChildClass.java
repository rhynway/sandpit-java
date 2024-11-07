package name.rhyn.sandpit.java.oop.inheritance;

import java.io.FileNotFoundException;

public class ChildClass extends ParentClass {

  public Integer instanceCount = 30;

  public ChildClass() {
    super();
  }

  public ChildClass(Integer instanceCount) {
    super();
    this.instanceCount = instanceCount;
  }

  public static String staticMethod() {
    return "ChildClass static method";
  }

  /**
   * Overriding a method showcasing the return type and exception rules.
   * <p>
   * - The access modifier of the method in the child class must be the same as or broader than the
   * method in the superclass. (In the parent class it's protected)
   *
   * @return a covariant object when overriding the parent method. In the parent it returns
   * ParentClass.
   * @throws FileNotFoundException which is the same or narrower exception than in the parent.
   *                               Throws Exception wouldn't compile
   */
  @Override
  public ChildClass getSelf() throws FileNotFoundException {
    return this;
  }

  @Override
  public Integer getCount() {
    return instanceCount;
  }

  public Integer getParentCount() {
    return super.getCount();
  }

  @Override
  public String defaultMethod() {
    return super.defaultMethod();
  }
}
