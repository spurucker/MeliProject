package org.meli.exercise.Meli;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meli.exercise.Meli.mutant.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeliApplicationTests {

	@Autowired
	private MutantService service;

	@Test
	public void contextLoads() {
		assertThat(service, is(notNullValue()));
	}
}
