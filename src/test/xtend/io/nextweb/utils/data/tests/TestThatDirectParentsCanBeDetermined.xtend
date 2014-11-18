package io.nextweb.utils.data.tests

import de.oehme.xtend.junit.JUnit
import io.nextweb.jre.Nextweb
import io.nextweb.utils.data.NextwebDataExtension

@JUnit
class TestThatDirectParentsCanBeDetermined {

	def test() {

		val session = Nextweb.createSession()

		val node = session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2014/3/12/n4/Unnamed2")

		val parent = session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2014/3/12/n4")
		
		
		node.isDirectParent(parent) => true
	}

	extension NextwebDataExtension dx = new NextwebDataExtension

}
