package io.nextweb.utils.data.tests

import com.appjangle.jre.AppjangleJre
import de.mxro.fn.Success
import de.oehme.xtend.junit.JUnit
import io.nextweb.utils.data.NextwebDataExtension

import static de.mxro.async.jre.AsyncJre.*

import static extension de.mxro.async.Async.embed

@JUnit
class TestRemoveRecursive {
	
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
		

		waitFor [cb |
			root.removeSafeRecursive( node1, cb.embed [qries |
				(qries.size > 0) => true
				
				session.commit.get
				
				cb.onSuccess(Success.INSTANCE)
				
			])
			
			
			
		]
		
		
		node1.selectAll().get().size() => 0
		
		session.close.get
		server.shutdown.get
		
	}
	
	extension NextwebDataExtension ext = new NextwebDataExtension
	// extension TreeExtension tree = new TreeExtension
	
}