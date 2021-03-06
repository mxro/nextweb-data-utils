package io.nextweb.utils.data.tests

import de.oehme.xtend.junit.JUnit
import io.nextweb.utils.data.NextwebDataExtension
import com.appjangle.api.jre.AppjangleApi

@JUnit
class TestThatDirectParentsCanBeDetermined {

	def test() {

		val session = AppjangleApi.createClient()

		val directChild = session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2014/3/12/n4/Unnamed2")

		val indirectChild = session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2014/3/12/n4/Unnamed15/Unnamed2")
		
		val parent = session.link("http://slicnet.com/mxrogm/mxrogm/data/stream/2014/3/12/n4")
		
		parent.hasDirectChild(directChild) => true
		
		directChild.hasDirectChild(parent) => false
		
        parent.hasDirectChild(indirectChild) => false
		
	}

	extension NextwebDataExtension dx = new NextwebDataExtension

}
