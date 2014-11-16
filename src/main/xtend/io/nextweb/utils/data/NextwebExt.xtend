package io.nextweb.utils.data

import de.mxro.async.callbacks.ValueCallback
import de.mxro.fn.Success
import io.nextweb.Entity
import io.nextweb.Link
import java.util.List

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
	def static collectDirectChildren(Entity of, ValueCallback<List<Link>> cb) {
		
	}
	
}