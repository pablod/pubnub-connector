Mule PubNub Cloud Connector
===========================

PubNub is an Internet-Wide Messaging Service for Real-time Web and Mobile apps and games. PubNub is a massively scalable Real-time Bidirectional Messaging Service in the Cloud.

This connector supports version 3 of the PubNub REST API. PubNub is a freemium service where users get 5,000 messages per day for free.  For more information go to PubNub: http://pubnub.com

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
            <url>https://repository.mulesoft.org/releases/</url>
            <layout>default</layout>
        </repsitory>
    </repositories>

Add the connector as a dependency to your project. This can be done by adding
the following under the dependencies element in the pom.xml file of the
application:

    <dependency>
        <groupId>org.mule.modules</groupId>
        <artifactId>mule-module-pubnub</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

Configuration
-------------

You can configure the connector as follows:

    <pubnub:config publishKey="value" subscribeKey="value" secretKey="value" ssl="value"/>

Here is detailed list of all the configuration attributes:

| attribute | description | optional | default value |
|:-----------|:-----------|:---------|:--------------|
|name|Give a name to this configuration so it can be later referenced by config-ref.|yes||
|publishKey|Your publish key that allows you to send data to the PubNub cloud|no|
|subscribeKey|Your subscribe key that allows you to send data to the PubNub cloud|no|
|secretKey|The secret key given to you when you created the account|no|
|ssl|Whether to use SSL or not when communicating with the PubNub cloud|yes|
















Publish
-------

Send a json message to a channel.



    
    <pubnub:publish channel="mychannel" jsonMessage="world"/>
    

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|channel|     name to publish the message to|no||
|jsonMessage| the message to publish|yes|#[payload]|


Request
-------

Makes a blocking request to receive a message on a channel.



    
    <pubnub:request channel="mychannel"/>
    

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|channel| name to read from|no||
|timeout| how long to wait for a message. The value is expressed in milliseconds. specify for|yes|5000|

History
-------

Load history from a channel.



    
    <pubnub:history channel="mychannel" limit="20"/>
    

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||
|channel| name.|no||
|limit|   history count response.|no||

Server Time
-----------

Get the Timestamp from PubNub Cloud.



    
    <pubnub:server-time/>
    

| attribute | description | optional | default value | possible values |
|:-----------|:-----------|:---------|:--------------|:----------------|
|config-ref|Specify which configuration to use for this invocation|yes||














