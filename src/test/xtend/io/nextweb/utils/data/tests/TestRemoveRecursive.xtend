package io.nextweb.utils.data.tests

import com.appjangle.jre.AppjangleJre
import de.oehme.xtend.junit.JUnit

@JUnit
class TestRemoveRecursive {
	
	def tes() {
		
		val server = AppjangleJre.startServer
		val session = AppjangleJre.createSession(server)
		
		
		val root = session.seed(server)
		val node1 = root.append("node1", "./node1")
		
		
		session.close.get
		server.shutdown.get
		
	}
	
	
}