/**
 * Mule Pubnub Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
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
