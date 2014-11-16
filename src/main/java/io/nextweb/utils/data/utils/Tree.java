package io.nextweb.utils.data.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class Tree<T extends Object> implements Iterable<Tree<T>> {
  private T root;
  
  private List<Tree<T>> children;
  
  public Tree(final T data) {
    this.root = data;
    LinkedList<Tree<T>> _linkedList = new LinkedList<Tree<T>>();
    this.children = _linkedList;
  }
  
  public Tree<T> add(final Tree<T> childNode) {
    this.children.add(childNode);
    return childNode;
  }
  
  public T root() {
    return this.root;
  }
  
  public Iterator<Tree<T>> iterator() {
    return this.children.iterator();
  }
}
