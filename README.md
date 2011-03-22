Mule pubnub Cloud Connector
===========================

PubNub is an Internet-Wide Messaging Service for Real-time Web and Mobile apps and games. PubNub is a massively
scalable Real-time Bidirectional Messaging Service in the Cloud.

This connector supports version 3 of the PubNub REST API. PubNub is a freemium service where users get 5,000
messages per day for free.  For more information go to PubNub: http://pubnub.com

Usage
-----

Mule cloud connectors can be used from different frameworks or using plain old Java.

Java
----
    PubnubCloudConnector pubnub = new PubnubCloudConnector("publishKey", "subscribeKey", "secretKey");

    //Publish a message to a channel called 'test'
    ObjectNode message = pubnub.createMessage();
    message.put("hello", "world");
    pubnub.publish("test", message);

    //Request any messages from a channel called 'test' with a time out of 5000 milliseconds
    JsonNode response = pubnub.request("test", 5000L);
    if(response!=null) {
        //At least one message was received before the timeout
        for(init i = 0; i < response.size(); i++) {
            System.out.println(response.get(i);
        }
    }

    //Subscribe any messages from a channel called 'test' with a time out of 5000 milliseconds
    MessageListener listener = new MessageListener() {
        public boolean onMessage(JsonNode message) {
             //do something with the message

             //return true to keep listening
             return true;
        }

    }
    //This is a blocking call
    pubnub.subscribe("test", listener);


Mule
----
IN Mule you can use Cloud connectors in Mule Xml configuration.  To do so, you need to add the schema for the cloud
connector you want to use:

    <mule xmlns="http://www.mulesoft.org/schema/mule/core"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:pubnub="http://www.mulesoft.org/schema/mule/pubnub"
          xsi:schemaLocation="http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/3.1/mule.xsd
                              http://www.mulesoft.org/schema/mule/pubnub http://www.mulesoft.org/schema/mule/pubnub/1.0-SNAPSHOT/mule-pubnub.xsd">

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

Spring
------

In Spring or Mule the PubNub cloud connector can be configured as a Spring bean and injected into your code:

    <bean name="pubnub" class="org.mule.modules.pubnub.PubnubCloudConnector">
        <property name="publishKey" value="demo"/>
        <property name="subscribeKey" value="demo"/>
        <property name="secretKey" value=""/>
    </bean>

Installation
------------

The connector can either be installed for all applications running within the Mule instance or can be setup to be used
for a single application.

*All Applications*

Download the connector from the link above and place the resulting jar file in
/lib/user directory of the Mule installation folder.

*Single Application*

To make the connector available only to single application then place it in the
lib directory of the application otherwise if using Maven to compile and deploy
your application the following can be done:

Add the connector's maven repo to your pom.xml:

    <repositories>
        <repository>
            <id>muleforge-releases</id>
            <name>MuleForge Repository</name>
            <url>https://repository.muleforge.org/release/</url>
            <layout>default</layout>
        </repsitory>
    </repositories>

Add the connector as a dependency to your project. This can be done by adding
the following under the <dependencies> element in the pom.xml file of the
application:

    <dependency>
        <groupId>org.mule.modules</groupId>
        <artifactId>mule-module-pubnub</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

Configuration
-------------

You can configure the connector as follows:

    <pubnub:config/>

Here is detailed list of all the configuration attributes:

| attribute | description | optional | default value |
|:-----------|:-----------|:---------|:--------------|
|name|Give a name to this configuration so it can be later referenced by config-ref.|yes||





Config
------

Prepare PubNub connector state. The keys required to create the connector are available from
your account on the PubNub website: http://www.pubnub.com/account.
SSL can be used by setting the SSL flag.

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|publishKey| Your publish key that allows you to send data to the PubNub cloud|no||
|subscribeKey| Your subscribe key that allows you to send data to the PubNub cloud|no||
|secretKey| The secret key given to you when you created the account|no||
|ssl| Whether to use SSL or not when communicating with the PubNub cloud|no||


Publish
-------

Send a json message to a channel.

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|channel| name to publish the message to|no||
|jsonMessage| the message to publish|no||


History
-------

Load history from a channel.

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|channel| name.|no||
|limit|   history count response.|no||

Time
----

Get the Timestamp from PubNub Cloud.

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||













