package io.nextweb.utils.data.tests

import com.appjangle.jre.AppjangleJre
import de.mxro.fn.Success
import de.oehme.xtend.junit.JUnit
import io.nextweb.utils.data.NextwebDataExtension


import static extension de.mxro.async.AsyncCommon.embed
import static de.mxro.async.jre.Async.*

@JUnit
class TestRemoveRecursiveSafe {
	
	def test() {

		val server = AppjangleJre.startServer
		val session = AppjangleJre.createSession(server)
		
		val root = session.seed(server)
		val node1 = root.append("node1", "./node1")
		
		node1.append("a child").append("and another")
		node1.append("a sibling").append("and something")
		
		val child3 = node1.append("child3")
		
		child3.append("a").append("b")
		child3.append("c")
		
		session.commit.get

		root.selectAll.get.values.contains("node1") => true


		waitFor [cb |
			root.removeSafeRecursive( node1, cb.embed [qries |
				(qries.size > 0) => true
				
				for (qry : qries) {
					qry.get []
				}
				
				cb.onSuccess(Success.INSTANCE)
				
			])

		]
		
		session.commit.get
		
		
		root.selectAll.get.values.contains("node1") => false
		
		session.close.get
		server.shutdown.get
		
	}
	
	extension NextwebDataExtension ext = new NextwebDataExtension
	// extension TreeExtension tree = new TreeExtension
	
}