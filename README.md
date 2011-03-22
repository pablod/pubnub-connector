Mule pubnub Cloud Connector
===========================

PubNub is an Internet-Wide Messaging Service for Real-time Web and Mobile apps and games. PubNub is a massively
scalable Real-time Bidirectional Messaging Service in the Cloud.

This connector supports version 3 of the PubNub REST API. PubNub is a freemium service where users get 5,000
messages per day for free.  For more information go to PubNub: http://pubnub.com

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













