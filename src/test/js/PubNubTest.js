/**
 * Mule Pubnub Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
//Not tested, I would like to figure out a way to
function testPublish()
{
    var pubnub = new org.mule.module.pubnub.PubNubCloudConnector("demo", "demo", "");
    pubnub.publish("mule-test", {"hello" : "world"});
}

//This doesn't work, but is what I'd lke to enable with cloud connect
function testSubscribe()
{
    var pubnub = new org.mule.module.pubnub.PubNubCloudConnector("demo", "demo", "");
    pubnub.subscribe("mule-test", new function(message) {
       //TODO there is no way this would work right now, but would be cool if we could do it
    });
}
