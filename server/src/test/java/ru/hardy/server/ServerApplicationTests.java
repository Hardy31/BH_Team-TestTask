package ru.hardy.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void whenSetUpSuccessful_thenHeadlessIsTrue() {
		assert(GraphicsEnvironment.isHeadless());
	}

}
