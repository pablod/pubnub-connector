package org.mule.module.pubnub;

import org.mule.util.concurrent.Latch;

import java.util.concurrent.atomic.AtomicReference;

import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Assert;
import org.junit.*;

public class PubnubFunctionalJavaTestCase
{
    private final String CHANNEL = "cc_channel";
    // TODO doesn't preserve chars first two chars
//    private final String TEST_VALUE = "Hello World! --> ɂ顶@#$%^&*()!";
    private final String TEST_VALUE = "Hello World!";

    private PubNubCloudConnector connector;
    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void init()
    {
        //Using the PubNub demo account for testing
        connector = new PubNubCloudConnector("demo", "demo", "");
    }

    @Test
    public void time()
    {
        //Not sure how to use this value
        double time = connector.serverTime();
        Assert.assertFalse("Did not receive a valid server time value", time == 0);
    }

    @Test
    public void publish()
    {
        ObjectNode msg = mapper.createObjectNode();
        msg.put("some_val", TEST_VALUE);
        JsonNode info = connector.publish(CHANNEL, msg);
        Assert.assertEquals("TODO what is this value", 1, info.get(0).getIntValue());
        Assert.assertEquals("TODO what is this code", "D", info.get(1).getTextValue()); //TODO what are the return codes?
    }

    @Test
    public void history()
    {
        int limit = 1;
        // Get History
        JsonNode response = connector.history(CHANNEL, limit);
        // Print Response from PubNub JSONP REST Service
        Assert.assertEquals("There is only one history entry on this channel, the message should have been the same as the last publish",
                TEST_VALUE, response.get(0).get("some_val").getTextValue());
    }

    @Test
    public void subscribe() throws InterruptedException
    {
        final Latch latch = new Latch();
        // Callback Interface when a Message is Received
        final MessageListener callback = new MessageListener()
        {
            @Override
            public boolean onMessage(JsonNode message)
            {
                // Print Received Message
                System.out.println("Subscribed Message received: " + message);
                latch.release();
                return false;
            }
        };

        final Latch pubLatch = new Latch();
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // Listen for Messages (Subscribe)
                pubLatch.release();
                connector.subscribe(CHANNEL, callback);
            }
        });

        t.start();
        Assert.assertTrue("Subscriber was not registered in a separate thread", pubLatch.await(5, TimeUnit.SECONDS));

        ObjectNode msg = mapper.createObjectNode();
        msg.put("hello", "you");


        connector.publish(CHANNEL, msg);
        Assert.assertTrue("Message was not received on channel: " + CHANNEL, latch.await(15, TimeUnit.SECONDS));
    }


    @Test
    public void request() throws InterruptedException
    {
        final Latch latch = new Latch();
        final Latch pubLatch = new Latch();
        final StringBuilder errorMessage = new StringBuilder();
        final AtomicReference<JsonNode> result = new AtomicReference<JsonNode>();

        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // Listen for Messages (Subscribe)
                pubLatch.release();
                long start = System.currentTimeMillis();
                JsonNode response = connector.request(CHANNEL, 5000L);
                System.out.println("Request: " + response);
                System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
                if (response==null)
                {
                    errorMessage.append("We should have received one message on the channel");
                }
                result.set(response);
                latch.release();
            }
        });

        t.start();
        Assert.assertTrue("Request was not made in a separate thread", pubLatch.await(15, TimeUnit.SECONDS));
        System.out.println("ready to publish");
        if(errorMessage.length() > 0) {
            Assert.fail(errorMessage.toString());
        }

        ObjectNode msg = mapper.createObjectNode();
        msg.put("hello", "me");


        connector.publish(CHANNEL, msg);

        System.out.println("published");

        Assert.assertTrue("Message was not received on channel: " + CHANNEL, latch.await(15, TimeUnit.SECONDS));

        Assert.assertNotNull(result.get());
        Assert.assertEquals(1, result.get().size());
        System.out.println(result.get().getTextValue());
    }
}


