package io.nextweb.utils.data

import de.mxro.async.Async
import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Closure2
import de.mxro.fn.Success
import de.mxro.tree.Tree
import io.nextweb.Entity
import io.nextweb.Link
import io.nextweb.Node
import io.nextweb.Query
import io.nextweb.promise.NextwebPromise
import java.util.List

import static extension de.mxro.async.Async.embed
import de.mxro.tree.TreeExtension

class NextwebExt {

	/**
	 * <p>Removes the specified node from the defined <code>form</code> node and all its <b>direct</b> children.
	 * 
	 * <p>Callback is called when all operations are defined, NOT executed.
	 */
	def static void removeRecursive(Entity from, Entity entity, ValueCallback<Success> cb) {
		
		entity.collectDirectChildren(cb.embed [tree |
			
			tree.traverse( [parent, node |
				parent.remove(node)
			])
			
			cb.onSuccess(Success.INSTANCE)
			
		])		
	}


	def static void removeSaveRecursive(Entity from, Entity entity, ValueCallback<List<NextwebPromise<Success>>> cb) {
		
		entity.collectDirectChildren(cb.embed [tree|
			
			val res = newArrayList
			
			for (link: toList(tree)) {
				
				
				
			}
			
			cb.onSuccess(res)
			
		])
		
	}

	

	/**
	 * Determines all <b>direct</b> children of a node.
	 */
	def static void collectDirectChildren(Entity of, ValueCallback<Tree<Link>> cb) {
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
			query.catchExceptions [er | cb.onFailure(er.exception)]
			query.get [node|
				collectDirectChildren(node.session().link(node), cb)
			]
		}
	}

	/**
	 * Determines all <b>direct</b> children of a node.
	 */
	def static void collectDirectChildren(Link root, ValueCallback<Tree<Link>> cb) {
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
	
	extension TreeExtension tree = new TreeExtension

}
