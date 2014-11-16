package io.nextweb.utils.data.tests

import com.appjangle.jre.AppjangleJre
import de.oehme.xtend.junit.JUnit

@JUnit
class TestRemoveRecursive {
	
	def tes() {
		
		val server = AppjangleJre.startServer
		val session = AppjangleJre.createSession(server)
		
		
		
		
		session.close.get
		server.shutdown.get
		
	}
	
	
}