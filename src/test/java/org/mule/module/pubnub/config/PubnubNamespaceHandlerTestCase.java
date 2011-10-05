/**
 * This file was automatically generated by the Mule Cloud Connector Development Kit
 */
package org.mule.module.pubnub.config;

import org.mule.module.pubnub.PubNubModule;
import org.mule.tck.FunctionalTestCase;

public class PubnubNamespaceHandlerTestCase extends FunctionalTestCase {
    @Override
    protected String getConfigResources() {
        return "pubnub-config.xml";
    }

    public void testPubNubConfig() throws Exception {

        Object o = muleContext.getRegistry().lookupObject("pubnub");
        assertNotNull(o);
        assertTrue(o instanceof PubNubModule);
        PubNubModule cc = (PubNubModule) o;
        assertEquals("demo", cc.getPublishKey());
        assertEquals("demo", cc.getSubscribeKey());
        assertEquals("secretKey", cc.getSecretKey());
        assertFalse(cc.isSsl());
    }

}