class PubNubTest extends GroovyTestCase
{
  void testPubSub()
  {
    def pubnub = new org.mule.module.pubnub.PubNubCloudConnector("demo", "demo", "");
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