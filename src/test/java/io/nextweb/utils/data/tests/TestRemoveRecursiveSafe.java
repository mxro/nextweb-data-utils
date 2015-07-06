package io.nextweb.utils.data.tests;

import de.oehme.xtend.junit.JUnit;
import delight.functional.Success;
import io.nextweb.utils.data.NextwebDataExtension;
import org.eclipse.xtext.xbase.lib.Extension;

/* @JUnit */@SuppressWarnings("all")
public class TestRemoveRecursiveSafe {
  public Success test() {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>"
      + "\nType mismatch: cannot convert from boolean to Procedure1<? super Boolean>");
  }
  
  @Extension
  private NextwebDataExtension ext = new NextwebDataExtension();
}
