/*
 * Created by IntelliJ IDEA.
 * User: ross
 * Date: 22/03/2011
 * Time: 09:53
 */
package org.mule.module.pubnub.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.mule.module.pubnub.PubnubCloudConnector;

/**
 * This is a code example of how to use the PubNub connector from a Guice module
 * IMPORTANT: If anything changes in this class you need yo update the USAGE.md to
 * reflect the change in usage
 */
public class PubNubExampleModule extends AbstractModule {
    protected void configure() {
        //add configuration logic here
    }

    @Provides
    public PubnubCloudConnector createPubNub() {
        return new PubnubCloudConnector("demo", "demo", "");
    }
}
