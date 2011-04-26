# PubNub connector Usage

Mule cloud connectors can be used from different frameworks or using plain old Java.

## Java

First create the PubNub cloud connector

    PubNubCloudConnector pubnub = new PubNubCloudConnector("publishKey", "subscribeKey", "secretKey");

To publish a message to a channel called 'test':

    ObjectNode message = pubnub.createMessage();
    message.put("hello", "world");
    pubnub.publish("test", message);

To request any messages from a channel called 'test' within a timelimit of 5000 milliseconds:

    JsonNode response = pubnub.request("test", 5000L);
    if(response!=null) {
        //At least one message was received before the timeout
        for(init i = 0; i < response.size(); i++) {
            System.out.println(response.get(i);
        }
    }

To subscribe any messages from a channel called 'test':

    MessageListener listener = new MessageListener() {
        public boolean onMessage(JsonNode message) {
             //do something with the message

             //return true to keep listening
             return true;
        }

    }
    //This is a blocking call
    pubnub.subscribe("test", listener);


## Mule

In Mule you can use Cloud connectors in Mule Xml configuration.  To do so, you need to add the schema for the cloud
connector you want to use:

    <mule xmlns="http://www.mulesoft.org/schema/mule/core"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:pubnub="http://www.mulesoft.org/schema/mule/pubnub"
          xsi:schemaLocation="http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/3.1/mule.xsd
                              http://www.mulesoft.org/schema/mule/pubnub http://www.mulesoft.org/schema/mule/pubnub/1.0/mule-pubnub.xsd">

        <!-- Add configuration and flows here -->

    </mule>

You'll need to configure the Cloud connector:

    <pubnub:config publishKey="demo" subscribeKey="demo" secretKey="" name="pubnub"/>

Then you can use the cloud connector to recieve and send messages.  When receiving you need to wrap the 'request' element in
the polling element:

    <flow name="theFlow">
        <poll frequency="5000">
            <pubnub:request channel="mule-test" timeout="5000"/>
        </poll>
        <logger level="INFO" message="EVENT RECEIVED: #[payload]"/>
        <echo-component/>
    </flow>

To publish messages, use the publish tag:

    <flow name="theFlow">
        <poll frequency="5000">
            <pubnub:request channel="mule-test" timeout="5000"/>
        </poll>
        <logger level="INFO" message="EVENT RECEIVED: #[payload]"/>
        <echo-component/>

        <pubnub:publish channel="mule-test-result"/>
    </flow>

## Spring

In Spring or Mule the PubNub cloud connector can be configured as a Spring bean and injected into your code:

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

        <bean name="pubnubConnector" class="org.mule.module.pubnub.PubNubCloudConnector">
            <property name="publishKey" value="demo"/>
            <property name="subscribeKey" value="demo"/>
            <property name="secretKey" value=""/>
        </bean>

    </beans>

Now you can get the PubNub connector from the Spring application context.

## Guice

To use PubNub from Guice you need to create a Provider that can be used to inject an instance of PubNub.  There are two ways
of doing this:

### Stand-alone provider

This is its own class that can be injected using the standard @Inject or binding annotations in Guice:

    public class org.mule.module.pubnub.guice.PubNubProvider implements com.google.inject.Provider<PubNubCloudConnector>
    {
        public PubNubCloudConnector get()
        {
            return new PubNubCloudConnector("demo", "demo", "");
        }
    }

### @Provides Annotation

Guice allows you to create provider methods inside a Module, the method needs the @Provides annotation:

    public class PubNubExampleModule extends AbstractModule
    {
        protected void configure()
        {
             //add configuration logic here
        }

        @Provides
        public PubNubCloudConnector createPubNub()
        {
            return new PubNubCloudConnector("demo", "demo", "");
        }
    }

## Groovy

Using the PubNub connector from Groovy is very similar to using Java:

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

Note that for Json manipulation cloud connectors use Jackson (the result of pubnub.createMessage() is
an ObjectNode). In Groovy 1.8 there will be a JSON builder, so message create can be done
using:

    void testPubSubGroovyJson()
    {
      def pubnub = new org.mule.module.pubnub.PubNubCloudConnector("demo", "demo", "");

      pubnub.publish("mule-test",
          new groovy.json.JsonBuilder([hello : "world"]).toString());
    }




