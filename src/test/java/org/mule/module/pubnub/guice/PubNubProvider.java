package org.mule.module.pubnub.guice;/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
import org.mule.module.pubnub.PubNubCloudConnector;

public class PubNubProvider implements com.google.inject.Provider<PubNubCloudConnector>
{
    public PubNubCloudConnector get()
    {
        return new PubNubCloudConnector("demo", "demo", "");
    }
}