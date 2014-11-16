package io.nextweb.utils.data;

import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Closure;
import de.mxro.fn.Closure2;
import de.mxro.fn.Success;
import io.nextweb.Entity;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.NextwebPromise;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.ArrayList;
import java.util.List;
import nx.core.domain.Tree;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class NextwebExt {
  /**
   * <p>Removes the specified node from the defined <code>form</code> node and all its <b>direct</b> children.
   * 
   * <p>Callback is called when all operations are defined, NOT executed.
   */
  public static void removeRecursive(final Entity from, final Entity entity, final ValueCallback<Success> cb) {
    final Closure<Tree<Link>> _function = new Closure<Tree<Link>>() {
      public void apply(final Tree<Link> tree) {
        final Closure2<Link, Link> _function = new Closure2<Link, Link>() {
          public void apply(final Link parent, final Link node) {
            parent.remove(node);
          }
        };
        NextwebExt.<Link>traverse(tree, _function);
        cb.onSuccess(Success.INSTANCE);
      }
    };
    ValueCallback<Tree<Link>> _embed = Async.<Tree<Link>>embed(cb, _function);
    NextwebExt.collectDirectChildren(entity, _embed);
  }
  
  public static void removeSaveRecursive(final Entity from, final Entity entity, final ValueCallback<List<NextwebPromise<Success>>> cb) {
    final Closure<Tree<Link>> _function = new Closure<Tree<Link>>() {
      public void apply(final Tree<Link> tree) {
        final ArrayList<NextwebPromise<Success>> res = CollectionLiterals.<NextwebPromise<Success>>newArrayList();
        List<Tree<Link>> _list = NextwebExt.<Link>toList(tree);
        for (final Tree<Link> link : _list) {
        }
        cb.onSuccess(res);
      }
    };
    ValueCallback<Tree<Link>> _embed = Async.<Tree<Link>>embed(cb, _function);
    NextwebExt.collectDirectChildren(entity, _embed);
  }
  
  private static <T extends Object> void traverse(final Tree<T> tree, final Closure2<T, T> operation) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method root is undefined for the type NextwebExt"
      + "\nThe method root is undefined for the type NextwebExt"
      + "\nType mismatch: cannot convert from Tree<T> to Iterable<?>"
      + "\nType mismatch: cannot convert from Object to Tree<T>");
  }
  
  private static <T extends Object> List<Tree<T>> toList(final Tree<T> t) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method root is undefined for the type NextwebExt"
      + "\nType mismatch: cannot convert from Tree<T> to Iterable<?>"
      + "\nType mismatch: cannot convert from Object to Tree<T>"
      + "\nType mismatch: cannot convert from ArrayList<Object> to List<Tree<T>>");
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public static void collectDirectChildren(final Entity of, final ValueCallback<Tree<Link>> cb) {
    if ((of instanceof Link)) {
      final Link link = ((Link)of);
      NextwebExt.collectDirectChildren(link, cb);
      return;
    }
    if ((of instanceof Node)) {
      Session _session = ((Node)of).session();
      final Link link_1 = _session.link(((Node) of));
      NextwebExt.collectDirectChildren(link_1, cb);
      return;
    }
    if ((of instanceof Query)) {
      final Query query = ((Query)of);
      final ExceptionListener _function = new ExceptionListener() {
        public void onFailure(final ExceptionResult er) {
          Throwable _exception = er.exception();
          cb.onFailure(_exception);
        }
      };
      query.catchExceptions(_function);
      final Closure<Node> _function_1 = new Closure<Node>() {
        public void apply(final Node node) {
          Session _session = node.session();
          Link _link = _session.link(node);
          NextwebExt.collectDirectChildren(_link, cb);
        }
      };
      query.get(_function_1);
    }
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public static void collectDirectChildren(final Link root, final ValueCallback<Tree<Link>> cb) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method root is undefined for the type NextwebExt"
      + "\nThe method add is undefined for the type NextwebExt"
      + "\nInvalid number of arguments. The constructor Tree(Network, GRootNodeType) is not applicable for the arguments (Link)"
      + "\nType mismatch: cannot convert from Link to Network"
      + "\nuri cannot be resolved"
      + "\nstartsWith cannot be resolved");
  }
}
