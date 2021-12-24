package ru.geekbrains.config;

import org.junit.Assert;
import org.junit.Test;

public class ConfigFromCliTest {

    @Test
    public void testConfigFromCli() {
        Config config = new ConfigFromCli(new String[] {"first", "1234"});
        Assert.assertEquals("first", config.getWwwHome());
        Assert.assertEquals(1234, config.getPort());
    }
}
