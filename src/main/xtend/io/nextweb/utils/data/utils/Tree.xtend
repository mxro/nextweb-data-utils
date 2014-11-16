package io.nextweb.utils.data.utils

import java.util.LinkedList
import java.util.List

class Tree<T> implements Iterable<Tree<T>> {

	T root;
	List<Tree<T>> children;

	new(T data) {
		this.root = data;
		this.children = new LinkedList<Tree<T>>();
	}

	def Tree<T> add(Tree<T> childNode) {
		
		this.children.add(childNode)
		return childNode;
	}

	def root() {
		root
	}

	override iterator() {
		children.iterator
	}

}
