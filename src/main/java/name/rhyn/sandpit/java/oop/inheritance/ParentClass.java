package name.rhyn.sandpit.java.oop.inheritance;

import java.io.IOException;

public class ParentClass implements SomeInterface {

  public Integer instanceCount;

  public ParentClass() {
    instanceCount = 20;
  }

  public ParentClass(Integer instanceCount) {
    this.instanceCount = instanceCount;
  }

  protected ParentClass getSelf() throws IOException {
    return this;
  }

  @Override
  public String abstractMethod() {
    return "ParentClass abstractMethod";
  }

  @Override
  public Integer getCount() {
    return instanceCount;
  }

}
