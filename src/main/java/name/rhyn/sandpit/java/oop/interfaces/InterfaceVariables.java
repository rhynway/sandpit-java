package name.rhyn.sandpit.java.oop.interfaces;

/**
 * A variable defined in an interface is implicitly public static and final.
 * - Valid access modifiers are: public and nothing (implicitly public not default)
 */
interface InterfaceVariables {

  Integer maximum = 100;

  public Integer partlyMaximum = 100;

  public static final Integer completeMaximum = 100;

}
