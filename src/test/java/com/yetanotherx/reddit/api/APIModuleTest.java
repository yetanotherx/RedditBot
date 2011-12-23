package com.yetanotherx.reddit.api;

import com.yetanotherx.reddit.api.APIModule;
import junit.framework.TestCase;

/**
 *
 * @author yetanotherx
 */
public class APIModuleTest extends TestCase {
    
    public APIModuleTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getError method, of class APIModule.
     */
    public void testGetError() {
        System.out.println("getError");
        assertEquals("RATELIMIT", APIModule.getError("\"call\", [\".error.RATELIMIT.field-ratelimit\"]], [11, 12, \"attr\""));
        assertEquals("RATELIT", APIModule.getError("\"call\", [\".error.RATELIT\"]], [11, 12, \"attr\""));

    }

}
