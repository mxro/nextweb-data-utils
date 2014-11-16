package io.nextweb.utils.data.utils

import java.util.LinkedList
import java.util.List

class Tree<T> implements Iterable<Tree<T>> {

	T data;
	Tree<T> parent;
	List<Tree<T>> children;

	new(T data) {
		this.data = data;
		this.children = new LinkedList<Tree<T>>();
	}

	def Tree<T> add(T child) {
		val Tree<T> childNode = new Tree<T>(child)
		childNode.parent = this
		this.children.add(childNode)
		return childNode;
	}

	override iterator() {
		children.iterator
	}

}
