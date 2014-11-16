package io.nextweb.utils.data

import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success
import io.nextweb.Entity
import io.nextweb.Link
import io.nextweb.Node
import io.nextweb.utils.data.utils.Tree

import static extension de.mxro.async.Async.embed

class NextwebExt {

	/**
	 * <p>Removes the specified node from the defined <code>form</code> node and all its <b>direct</b> children.
	 * 
	 * <p>Callback is called when all operations are defined, NOT executed.
	 */
	def static removeRecursive(Entity from, Entity entity, ValueCallback<Success> cb) {
	}

	/**
	 * Determines all <b>direct</b> children of a node.
	 */
	def static collectDirectChildren(Entity of, ValueCallback<Tree<Link>> cb) {
		if (of instanceof Link) {
			val link = of
			collectDirectChildren(link, cb)
			return;
		}
		
		if (of instanceof Node) {
			val link = of.session().link(of as Node)
			
		}
	}

	/**
	 * Determines all <b>direct</b> children of a node.
	 */
	def static collectDirectChildren(Link root, ValueCallback<Tree<Link>> cb) {
		val session = root.session()
		val qry = root.selectAll

		qry.catchExceptions[er|cb.onFailure(er.exception)]

		qry.get [ children |

			Async.forEach(children.nodes(),
				[ e, ValueCallback<Tree<Link>> itmcb |
					collectDirectChildren(session.link(e), itmcb)
				],
				cb.embed [ res |
					val t = new Tree<Link>(session.link(root))
					for (Tree<Link> childTree : res) {

						if (childTree.root.uri().startsWith(root.uri())) {

							t.add(childTree)	
							
						}

					}
					
					cb.onSuccess(t)
				])
		]

	}

}