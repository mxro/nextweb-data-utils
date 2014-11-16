package io.nextweb.utils.data;

import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Closure;
import de.mxro.fn.Closure2;
import de.mxro.fn.Success;
import io.nextweb.Entity;
import io.nextweb.promise.NextwebPromise;
import io.nextweb.utils.data.utils.Tree;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class NextwebExt {
  /**
   * <p>Removes the specified node from the defined <code>form</code> node and all its <b>direct</b> children.
   * 
   * <p>Callback is called when all operations are defined, NOT executed.
   */
  public static void removeRecursive(final Entity from, final Entity entity, final ValueCallback<Success> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nremove cannot be resolved");
  }
  
  public static void removeSaveRecursive(final Entity from, final Entity entity, final ValueCallback<NextwebPromise<Success>> cb) {
    final Closure<Tree<Link>> _function = new Closure<Tree<Link>>() {
      public void apply(final Tree<Link> tree) {
        List<Link> _list = NextwebExt.<Link>toList(tree);
        for (final Link link : _list) {
        }
      }
    };
    ValueCallback<Tree<Link>> _embed = Async.<Tree<Link>>embed(cb, _function);
    NextwebExt.collectDirectChildren(entity, _embed);
  }
  
  private static <T extends Object> void traverse(final Tree<T> tree, final Closure2<T, T> operation) {
    for (final Tree<T> node : tree) {
      {
        NextwebExt.<T>traverse(node, operation);
        T _root = tree.root();
        T _root_1 = node.root();
        operation.apply(_root, _root_1);
      }
    }
  }
  
  private static <T extends Object> List<T> toList(final Tree<T> t) {
    ArrayList<T> _xblockexpression = null;
    {
      final ArrayList<T> l = CollectionLiterals.<T>newArrayList();
      for (final Tree<T> node : t) {
        {
          final List<T> cl = NextwebExt.<T>toList(node);
          l.addAll(cl);
        }
      }
      T _root = t.root();
      l.add(_root);
      _xblockexpression = l;
    }
    return _xblockexpression;
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public static void collectDirectChildren(final Entity of, final /* ValueCallback<Tree<Link>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nLink cannot be resolved to a type."
      + "\nNode cannot be resolved to a type."
      + "\nNode cannot be resolved to a type."
      + "\nQuery cannot be resolved to a type."
      + "\nThe method catchExceptions is undefined for the type NextwebExt"
      + "\nThe method exception is undefined for the type NextwebExt"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context.");
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public static void collectDirectChildren(final /* Link */Object root, final /* ValueCallback<Tree<Link>> */Object cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nLink cannot be resolved to a type."
      + "\nLink cannot be resolved to a type."
      + "\nLink cannot be resolved to a type."
      + "\nThe method exception is undefined for the type NextwebExt"
      + "\nThe method or field Async is undefined for the type NextwebExt"
      + "\nThe method nodes is undefined for the type NextwebExt"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nsession cannot be resolved"
      + "\nselectAll cannot be resolved"
      + "\ncatchExceptions cannot be resolved"
      + "\nget cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\nlink cannot be resolved"
      + "\nlink cannot be resolved"
      + "\nuri cannot be resolved"
      + "\nstartsWith cannot be resolved"
      + "\nuri cannot be resolved");
  }
}
