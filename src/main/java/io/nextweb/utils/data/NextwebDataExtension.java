package io.nextweb.utils.data;

import de.mxro.async.Async;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.fn.Closure;
import de.mxro.fn.Closure2;
import de.mxro.fn.Success;
import de.mxro.tree.Tree;
import de.mxro.tree.TreeExtension;
import io.nextweb.Entity;
import io.nextweb.Link;
import io.nextweb.ListQuery;
import io.nextweb.Node;
import io.nextweb.NodeList;
import io.nextweb.Query;
import io.nextweb.Session;
import io.nextweb.promise.NextwebPromise;
import io.nextweb.promise.exceptions.ExceptionListener;
import io.nextweb.promise.exceptions.ExceptionResult;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class NextwebDataExtension {
  /**
   * <p>Removes the specified node from the defined <code>form</code> node and all its <b>direct</b> children.
   * 
   * <p>Callback is called when all operations are defined, NOT executed.
   */
  public void removeRecursive(final Entity from, final Entity entity, final ValueCallback<Success> cb) {
    final Closure<Tree<Link>> _function = new Closure<Tree<Link>>() {
      public void apply(final Tree<Link> tree) {
        final Closure<Tree<Link>> _function = new Closure<Tree<Link>>() {
          public void apply(final Tree<Link> it) {
            boolean _hasParent = it.hasParent();
            if (_hasParent) {
              Tree<Link> _parent = it.parent();
              Link _value = _parent.value();
              Link _value_1 = it.value();
              _value.remove(_value_1);
            }
          }
        };
        NextwebDataExtension.this.tree.<Link, Object>forEachNode(tree, _function);
        cb.onSuccess(Success.INSTANCE);
      }
    };
    ValueCallback<Tree<Link>> _embed = Async.<Tree<Link>>embed(cb, _function);
    NextwebDataExtension.collectDirectChildren(entity, _embed);
  }
  
  public void removeSaveRecursive(final Entity from, final Entity entity, final ValueCallback<List<NextwebPromise<Success>>> cb) {
    final Closure<Tree<Link>> _function = new Closure<Tree<Link>>() {
      public void apply(final Tree<Link> tree) {
        final ArrayList<NextwebPromise<Success>> res = CollectionLiterals.<NextwebPromise<Success>>newArrayList();
        List<Tree<Link>> _list = NextwebDataExtension.this.tree.<Link>toList(tree);
        for (final Tree<Link> treeNode : _list) {
          Tree<Link> _parent = treeNode.parent();
          Link _value = _parent.value();
          Link _value_1 = treeNode.value();
          NextwebPromise<Success> _removeSafe = _value.removeSafe(_value_1);
          res.add(_removeSafe);
        }
        cb.onSuccess(res);
      }
    };
    ValueCallback<Tree<Link>> _embed = Async.<Tree<Link>>embed(cb, _function);
    NextwebDataExtension.collectDirectChildren(entity, _embed);
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public static void collectDirectChildren(final Entity of, final ValueCallback<Tree<Link>> cb) {
    if ((of instanceof Link)) {
      final Link link = ((Link)of);
      NextwebDataExtension.collectDirectChildren(link, cb);
      return;
    }
    if ((of instanceof Node)) {
      Session _session = ((Node)of).session();
      final Link link_1 = _session.link(((Node) of));
      NextwebDataExtension.collectDirectChildren(link_1, cb);
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
          NextwebDataExtension.collectDirectChildren(_link, cb);
        }
      };
      query.get(_function_1);
    }
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public static void collectDirectChildren(final Link root, final ValueCallback<Tree<Link>> cb) {
    final Session session = root.session();
    final ListQuery qry = root.selectAll();
    final ExceptionListener _function = new ExceptionListener() {
      public void onFailure(final ExceptionResult er) {
        Throwable _exception = er.exception();
        cb.onFailure(_exception);
      }
    };
    qry.catchExceptions(_function);
    final Closure<NodeList> _function_1 = new Closure<NodeList>() {
      public void apply(final NodeList children) {
        List<Node> _nodes = children.nodes();
        final Closure2<Node, ValueCallback<Tree<Link>>> _function = new Closure2<Node, ValueCallback<Tree<Link>>>() {
          public void apply(final Node e, final ValueCallback<Tree<Link>> itmcb) {
            Link _link = session.link(e);
            NextwebDataExtension.collectDirectChildren(_link, itmcb);
          }
        };
        final Closure<List<Tree<Link>>> _function_1 = new Closure<List<Tree<Link>>>() {
          public void apply(final List<Tree<Link>> res) {
            Link _link = session.link(root);
            final Tree<Link> t = new Tree<Link>(_link);
            for (final Tree<Link> childTree : res) {
              Link _value = childTree.value();
              String _uri = _value.uri();
              String _uri_1 = root.uri();
              boolean _startsWith = _uri.startsWith(_uri_1);
              if (_startsWith) {
                t.add(childTree);
              }
            }
            cb.onSuccess(t);
          }
        };
        ValueCallback<List<Tree<Link>>> _embed = Async.<List<Tree<Link>>>embed(cb, _function_1);
        Async.<Node, Tree<Link>>forEach(_nodes, _function, _embed);
      }
    };
    qry.get(_function_1);
  }
  
  @Extension
  private TreeExtension tree = new TreeExtension();
}
