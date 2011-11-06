/**
 * Mule Pubnub Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.pubnub;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Assert;
import org.mule.tck.FunctionalTestCase;
import org.mule.util.concurrent.Latch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class PubnubFunctionalMuleXMLTestCase extends FunctionalTestCase {
    @Override
    protected String getConfigResources() {
        return "pubnub-config.xml";
    }

    public void testPubNubConfig() throws Exception {

        final PubNubModule cc = new PubNubModule("demo", "demo", "");
        cc.init();

        final AtomicReference<JsonNode> result = new AtomicReference<JsonNode>();
        final Latch latch = new Latch();
        // Callback Interface when a Message is Received
        final MessageListener callback = new MessageListener() {
            @Override
            public boolean onMessage(JsonNode message) {
                // Print Received Message
                System.out.println("Subscribed Message received: " + message);
                result.set(message);
                latch.release();
                //Stop listening
                return false;
            }
        };

        //We need to to subscribe and publish in different threads since PubNub is not a queuing
        //system, so messages are only received to subscribers who are actively listening
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // Listen for Messages (Subscribe)
                cc.subscribe("mule-test-result", callback);
            }
        });

        t.start();

        ObjectNode msg = new ObjectMapper().createObjectNode();
        msg.put("hello", "cloud");

        Thread.sleep(5000);
        cc.publish("mule-test", msg);
        Assert.assertTrue("Message was not received on channel: mule-test-result", latch.await(15, TimeUnit.SECONDS));


    }

}
