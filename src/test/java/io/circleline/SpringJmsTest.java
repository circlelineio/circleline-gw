package io.circleline;

import org.apache.camel.main.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 1001923 on 16. 1. 18..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring/jms-context.xml"})
public class SpringJmsTest {
    @Test
    public void run() throws Exception{
        Main main = new Main();
        main.run();
    }
}
