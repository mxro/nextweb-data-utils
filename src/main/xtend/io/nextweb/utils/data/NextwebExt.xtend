package io.nextweb.utils.data

import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success
import io.nextweb.Entity
import io.nextweb.Link
import io.nextweb.utils.data.utils.Tree

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
		
	}
	
	/**
	 * Determines all <b>direct</b> children of a node.
	 */
	def static collectDirectChildren(Tree<Link> root, ValueCallback<Tree<Link>> cb) {
		val session = root..session()
		val qry = of.selectAll
		
		qry.catchExceptions [er|cb.onFailure(er.exception)]
		
		qry.get [children|
			val t = new Tree<Link>(session.link(of.get))
			for (child: children) {
				val childTree = t.add(session.link(child))
				
				
				
			}
			
			
		]
		
	}
	
}