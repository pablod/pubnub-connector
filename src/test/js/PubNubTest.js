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
