/**
 * Mule Pubnub Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.pubnub.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.mule.module.pubnub.PubNubModule;

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
    public PubNubModule createPubNub() {
        return new PubNubModule("demo", "demo", "");
    }
}
