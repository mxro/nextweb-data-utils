package io.nextweb.utils.data;

import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Success;
import io.nextweb.Entity;
import io.nextweb.Link;
import io.nextweb.utils.data.utils.Tree;

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
  public static Object collectDirectChildren(final Entity of, final ValueCallback<Tree<Link>> cb) {
    return null;
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public static void collectDirectChildren(final Tree<Link> root, final ValueCallback<Tree<Link>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nmissing \')\' at \'for\'"
      + "\nThe method or field childDree is undefined for the type NextwebExt"
      + "\nInvalid number of arguments. The method collectDirectChildren(Entity, ValueCallback<Tree<Link>>) is not applicable for the arguments (Object)");
  }
}
