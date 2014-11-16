package io.nextweb.utils.data.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class Tree<T extends Object> implements Iterable<Tree<T>> {
  private T data;
  
  private Tree<T> parent;
  
  private List<Tree<T>> children;
  
  public Tree(final T data) {
    this.data = data;
    LinkedList<Tree<T>> _linkedList = new LinkedList<Tree<T>>();
    this.children = _linkedList;
  }
  
  public Tree<T> add(final T child) {
    final Tree<T> childNode = new Tree<T>(child);
    childNode.parent = this;
    this.children.add(childNode);
    return childNode;
  }
  
  public Iterator<Tree<T>> iterator() {
    return this.children.iterator();
  }
}
