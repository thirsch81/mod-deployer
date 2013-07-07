import static org.vertx.testtools.VertxAssert.*

import org.vertx.groovy.testtools.VertxTests

import thhi.vertx.mods.DeployerVerticle
// The test methods must being with "test"

def testDeployDeployerVerticle() {
	container.deployWorkerVerticle("groovy:" + DeployerVerticle.class.name) { result ->
		assertTrue("${result.cause()}", result.succeeded)
		testComplete()
	}
}

VertxTests.initialize(this)
VertxTests.startTests(this)