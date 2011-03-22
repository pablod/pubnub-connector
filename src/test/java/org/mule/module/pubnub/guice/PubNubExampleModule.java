/*
 * Created by IntelliJ IDEA.
 * User: ross
 * Date: 22/03/2011
 * Time: 09:53
 */
package org.mule.module.pubnub.guice;

import org.mule.module.pubnub.PubNubCloudConnector;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;


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
