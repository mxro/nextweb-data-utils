package io.nextweb.utils.data;

import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Success;
import io.nextweb.Entity;
import io.nextweb.Link;
import java.util.List;

@SuppressWarnings("all")
public class NextwebExt {
  /**
   * <p>Removes the specified node from the defined <code>form</code> node and all its <b>direct</b> children.
   * 
   * <p>Callback is called when all operations are defined, NOT executed.
   */
  public static Object removeRecursive(final Entity from, final Entity entity, final ValueCallback<Success> cb) {
    return null;
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public static Object collectDirectChildren(final Entity of, final ValueCallback<List<Link>> cb) {
    return null;
  }
}
