package io.nextweb.utils.data

import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success
import de.mxro.tree.Tree
import de.mxro.tree.TreeExtension
import io.nextweb.Entity
import io.nextweb.Link
import io.nextweb.Node
import io.nextweb.Query
import io.nextweb.promise.NextwebPromise
import java.util.List

import static extension de.mxro.async.Async.embed

class NextwebDataExtension {

	/**
	 * <p>Removes the specified node from the defined <code>form</code> node and all its <b>direct</b> children.
	 * 
	 * <p>Callback is called when all operations are defined, NOT executed.
	 */
	def void removeRecursive(Entity from, Entity entity, ValueCallback<Success> cb) {

		entity.collectDirectChildren(
			cb.embed [ tree |
				for (childNode : tree.toList) {
					val it = childNode
					if (hasParent) {
						parent.value.remove(value)
					}
				}
				cb.onSuccess(Success.INSTANCE)
			])
	}

	def void removeSafeRecursive(Entity from, Entity entity, ValueCallback<List<NextwebPromise<Success>>> cb) {

		entity.collectDirectChildren(
			cb.embed [ tree |
				val res = newArrayList
				for (treeNode : tree.toList) {
					val it = treeNode
					if (hasParent) {
						res.add(parent.value.removeSafe(value))

					} else {
						res.add(from.removeSafe(entity))
					}
				}
				cb.onSuccess(res)
			])

	}

	/**
	 * Determines all <b>direct</b> children of a node.
	 */
	def void collectDirectChildren(Entity of, ValueCallback<Tree<Link>> cb) {

		if (of instanceof Link) {
			val link = of
			collectDirectChildren(link, cb)
			return
		}

		if (of instanceof Node) {
			val link = of.session().link(of as Node)
			collectDirectChildren(link, cb)
			return
		}

		if (of instanceof Query) {
			val query = of
			query.catchExceptions[er|cb.onFailure(er.exception)]
			query.get [ node |
				collectDirectChildren(node.session().link(node), cb)
			]
		}
	}

	/**
	 * Determines all <b>direct</b> children of a node.
	 */
	def void collectDirectChildren(Link node, ValueCallback<Tree<Link>> cb) {
		collectDirectChildrenInt(node, node, cb)
	}

	/**
	 * Determines all <b>direct</b> children of a node.
	 */
	def void collectDirectChildrenInt(Link root, Link node, ValueCallback<Tree<Link>> cb) {
		val session = node.session()
		val t = new Tree<Link>(session.link(node))

		if (!node.uri().startsWith(root.uri())) {
			cb.onSuccess(t)
			return;
		}

		val qry = node.selectAll

		qry.catchExceptions[er|cb.onFailure(er.exception)]

		qry.get [ children |
			Async.forEach(children.nodes(),
				[ e, ValueCallback<Tree<Link>> itmcb |
					collectDirectChildrenInt(root, session.link(e), itmcb)
				],
				cb.embed [ res |
					for (Tree<Link> childTree : res) {

						t.add(childTree)

					}
					cb.onSuccess(t)
				])
		]

	}

	def void isDirectParent( Link node, Link parent) {
		
	}

	extension TreeExtension tree = new TreeExtension

}
