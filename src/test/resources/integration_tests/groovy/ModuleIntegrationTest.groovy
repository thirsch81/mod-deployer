import static org.vertx.testtools.VertxAssert.*

// And import static the VertxTests script
import org.vertx.groovy.testtools.VertxTests;

// The test methods must being with "test"
def testModuleGetDeploymentList() {
	vertx.eventBus.send("deployer.deploymentList", []) { reply ->
		assertNotNull(reply)
		container.logger.info(reply.body)
		reply.body.with {
			assertEquals("ok", it.status)
			assertNotNull(it."modules")
		}
		testComplete()
	}

}

// Make sure you initialize
VertxTests.initialize(this)

container.deployModule("thhi.vertx~deployer~0.5.0", { result ->
	// Deployment is asynchronous and this handler will be called when it's complete (or failed)
	assertTrue("${result.cause()}", result.succeeded)
	assertNotNull("deploymentID should not be null", result.result())
	// If deployed correctly then start the tests!
	VertxTests.startTests(this)
})