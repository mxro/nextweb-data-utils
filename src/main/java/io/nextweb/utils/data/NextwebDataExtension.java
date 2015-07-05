package io.nextweb.utils.data;

import de.mxro.tree.Tree;
import de.mxro.tree.TreeExtension;
import delight.async.callbacks.ValueCallback;
import io.nextweb.Entity;
import io.nextweb.Link;
import io.nextweb.promise.NextwebPromise;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class NextwebDataExtension {
  /**
   * <p>Removes the specified node from the defined <code>form</code> node and all its <b>direct</b> children.
   * 
   * <p>Callback is called when all operations are defined, NOT executed.
   */
  public void removeRecursive(final Entity from, final Entity entity, final /* ValueCallback<Success> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method remove is undefined for the type NextwebDataExtension"
      + "\nThe method or field Success is undefined for the type NextwebDataExtension"
      + "\nINSTANCE cannot be resolved");
  }
  
  public void removeSafeRecursive(final Entity from, final Entity entity, final /* ValueCallback<List<NextwebPromise<Success>>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method removeSafe is undefined for the type NextwebDataExtension"
      + "\nThe method removeSafe is undefined for the type NextwebDataExtension"
      + "\nType mismatch: cannot convert from ArrayList<Object> to List<NextwebPromise<Success>>");
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public void collectDirectChildren(final Entity of, final ValueCallback<Tree<Link>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method session is undefined for the type NextwebDataExtension"
      + "\nInvalid number of arguments. The method get() is not applicable for the arguments ((Object)=>void)"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nlink cannot be resolved");
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public void collectDirectChildren(final Link node, final ValueCallback<Tree<Link>> cb) {
    this.collectDirectChildrenInt(node, node, cb);
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public void collectDirectChildrenInt(final Link root, final Link node, final ValueCallback<Tree<Link>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method nodes is undefined for the type NextwebDataExtension"
      + "\nInvalid number of arguments. The method get() is not applicable for the arguments ((Object)=>void)"
      + "\nAmbiguous feature call.\nThe methods\n\tlink(Reference) in SessionOperations,\n\tlink(Link) in SessionOperations,\n\tlink(Node) in SessionOperations and\n\tlink(String) in SessionOperations\nall match."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  public String getParentUri(final Link node) {
    String _xblockexpression = null;
    {
      final String it = node.uri();
      int _lastIndexOf = it.lastIndexOf("/");
      _xblockexpression = it.substring(0, _lastIndexOf);
    }
    return _xblockexpression;
  }
  
  public boolean hasDirectChild(final Link parent, final Link node) {
    String _parentUri = this.getParentUri(node);
    String _uri = parent.uri();
    return _parentUri.equals(_uri);
  }
  
  @Extension
  private TreeExtension tree = new TreeExtension();
}
