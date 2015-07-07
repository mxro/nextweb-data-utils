package io.nextweb.utils.data;

import de.mxro.tree.Tree;
import de.mxro.tree.TreeExtension;
import delight.async.AsyncCommon;
import delight.async.callbacks.ValueCallback;
import delight.functional.Closure;
import delight.functional.Closure2;
import delight.functional.Success;
import io.nextweb.Entity;
import io.nextweb.Link;
import io.nextweb.ListQuery;
import io.nextweb.Node;
import io.nextweb.NodeList;
import io.nextweb.Query;
import io.nextweb.Client;
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
      @Override
      public void apply(final Tree<Link> tree) {
        List<Tree<Link>> _list = NextwebDataExtension.this.tree.<Link>toList(tree);
        for (final Tree<Link> childNode : _list) {
          {
            final Tree<Link> it = childNode;
            boolean _hasParent = it.hasParent();
            if (_hasParent) {
              Tree<Link> _parent = it.parent();
              Link _value = _parent.value();
              Link _value_1 = it.value();
              _value.remove(_value_1);
            }
          }
        }
        cb.onSuccess(Success.INSTANCE);
      }
    };
    ValueCallback<Tree<Link>> _embed = AsyncCommon.<Tree<Link>>embed(cb, _function);
    this.collectDirectChildren(entity, _embed);
  }
  
  public void removeSafeRecursive(final Entity from, final Entity entity, final ValueCallback<List<NextwebPromise<Success>>> cb) {
    final Closure<Tree<Link>> _function = new Closure<Tree<Link>>() {
      @Override
      public void apply(final Tree<Link> tree) {
        final ArrayList<NextwebPromise<Success>> res = CollectionLiterals.<NextwebPromise<Success>>newArrayList();
        List<Tree<Link>> _list = NextwebDataExtension.this.tree.<Link>toList(tree);
        for (final Tree<Link> treeNode : _list) {
          {
            final Tree<Link> it = treeNode;
            boolean _hasParent = it.hasParent();
            if (_hasParent) {
              Tree<Link> _parent = it.parent();
              Link _value = _parent.value();
              Link _value_1 = it.value();
              NextwebPromise<Success> _removeSafe = _value.removeSafe(_value_1);
              res.add(_removeSafe);
            } else {
              NextwebPromise<Success> _removeSafe_1 = from.removeSafe(entity);
              res.add(_removeSafe_1);
            }
          }
        }
        cb.onSuccess(res);
      }
    };
    ValueCallback<Tree<Link>> _embed = AsyncCommon.<Tree<Link>>embed(cb, _function);
    this.collectDirectChildren(entity, _embed);
  }
  
  /**
   * Determines all <b>direct</b> children of a node.
   */
  public void collectDirectChildren(final Entity of, final ValueCallback<Tree<Link>> cb) {
    if ((of instanceof Link)) {
      final Link link = ((Link)of);
      this.collectDirectChildren(link, cb);
      return;
    }
    if ((of instanceof Node)) {
      Client _session = ((Node)of).session();
      final Link link_1 = _session.link(((Node) of));
      this.collectDirectChildren(link_1, cb);
      return;
    }
    if ((of instanceof Query)) {
      final Query query = ((Query)of);
      final ExceptionListener _function = new ExceptionListener() {
        @Override
        public void onFailure(final ExceptionResult er) {
          Throwable _exception = er.exception();
          cb.onFailure(_exception);
        }
      };
      query.catchExceptions(_function);
      final Closure<Node> _function_1 = new Closure<Node>() {
        @Override
        public void apply(final Node node) {
          Client _session = node.session();
          Link _link = _session.link(node);
          NextwebDataExtension.this.collectDirectChildren(_link, cb);
        }
      };
      query.get(_function_1);
    }
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
    final Client session = node.session();
    Link _link = session.link(node);
    final Tree<Link> t = new Tree<Link>(_link);
    String _uri = node.uri();
    String _uri_1 = root.uri();
    boolean _startsWith = _uri.startsWith(_uri_1);
    boolean _not = (!_startsWith);
    if (_not) {
      cb.onSuccess(t);
      return;
    }
    final ListQuery qry = node.selectAll();
    final ExceptionListener _function = new ExceptionListener() {
      @Override
      public void onFailure(final ExceptionResult er) {
        Throwable _exception = er.exception();
        cb.onFailure(_exception);
      }
    };
    qry.catchExceptions(_function);
    final Closure<NodeList> _function_1 = new Closure<NodeList>() {
      @Override
      public void apply(final NodeList children) {
        List<Node> _nodes = children.nodes();
        final Closure2<Node, ValueCallback<Tree<Link>>> _function = new Closure2<Node, ValueCallback<Tree<Link>>>() {
          @Override
          public void apply(final Node e, final ValueCallback<Tree<Link>> itmcb) {
            Link _link = session.link(e);
            NextwebDataExtension.this.collectDirectChildrenInt(root, _link, itmcb);
          }
        };
        final Closure<List<Tree<Link>>> _function_1 = new Closure<List<Tree<Link>>>() {
          @Override
          public void apply(final List<Tree<Link>> res) {
            for (final Tree<Link> childTree : res) {
              t.add(childTree);
            }
            cb.onSuccess(t);
          }
        };
        ValueCallback<List<Tree<Link>>> _embed = AsyncCommon.<List<Tree<Link>>>embed(cb, _function_1);
        AsyncCommon.<Node, Tree<Link>>forEach(_nodes, _function, _embed);
      }
    };
    qry.get(_function_1);
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
