package io.nextweb.utils.data.tests

import com.appjangle.jre.AppjangleJre
import de.oehme.xtend.junit.JUnit
import io.nextweb.utils.data.NextwebExt

@JUnit
class TestRemoveRecursive {
	
	def tes() {
		
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
		
		
		
		
		session.close.get
		server.shutdown.get
		
	}
	
	extension NextwebExt ext = new NextwebExt
	
}