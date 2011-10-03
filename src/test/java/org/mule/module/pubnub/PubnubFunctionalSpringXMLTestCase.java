/**
 * This file was automatically generated by the Mule Cloud Connector Development Kit
 */
package org.mule.module.pubnub;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// specifies the Spring configuration to load for this test fixture
@ContextConfiguration(locations = {"/pubnub-spring-config.xml"})
public class PubnubFunctionalSpringXMLTestCase {


    // this instance will be dependency injected by type
    @Autowired
    private PubNubModule pubnub;

    /**
     * We just test the config because we already test the PubNub connector functionality in another test
     *
     * @throws Exception
     */
    @Test
    public void testConnectorConfig() throws Exception {
        Assert.assertEquals("demo", pubnub.getPublishKey());
        Assert.assertEquals("demo", pubnub.getSubscribeKey());
        Assert.assertEquals("", pubnub.getSecretKey());
        Assert.assertFalse(pubnub.isSsl());
    }
}

