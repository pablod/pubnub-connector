/**
 * Mule Pubnub Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
class PubNubTest extends GroovyTestCase {
    void testPubSub() {
        def pubnub = new org.mule.module.pubnub.PubNubModule("demo", "demo", "");
        def result = null;

        //We need to perform the publish and request in different threads; PubNub is not a queuing system

        Thread.start {
            result = pubnub.request("mule-test", 5000L);
        }

        //need to wait for the thread to start
        Thread.sleep(1000);
        def msg = pubnub.createMessage();
        msg.put("hello", "world");
        pubnub.publish("mule-test", msg);

        assert result != null
        assert result.get(0).equals(msg)
    }

//  void testPubSubGroovyJson()
    //  {
    //    def pubnub = new org.mule.module.pubnub.PubNubCloudConnector("demo", "demo", "");
    //
    //    pubnub.publish("mule-test",
    //            new groovy.json.JsonBuilder([hello: "world"]).toString());
    //  }
}

//Note that there is a Json builder as part of the forthcoming Groovy 1.8 and can be used to build more complex messages easily
//def json = new groovy.json.JsonBuilder().people {
//    person {
//        firstName 'Guillame'
//        lastName 'Laforge'
//        // Maps are valid values for objects too
//        address(
//            city: 'Paris',
//            country: 'France',
//            zip: 12345,
//        )
//        married true
//        conferences 'JavaOne', 'Gr8conf'
//    }
//}