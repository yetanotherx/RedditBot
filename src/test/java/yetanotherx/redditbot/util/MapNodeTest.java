package yetanotherx.redditbot.util;

import java.util.Map;
import junit.framework.TestCase;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author yetanotherx
 */
public class MapNodeTest extends TestCase {
    
    public MapNodeTest(String testName) {
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
     * Test of getProperty method, of class MapNode.
     */
    public void testGetProperty() throws ParseException {
        
        String json = "{\"yaml\":{\"is\":\"quite\",\"the\":[\"awesomeness\"]}}";
        
        @SuppressWarnings("unchecked")
        MapNode instance = new MapNode((Map<String, Object>) new JSONParser().parse(json));
        
        assertEquals("quite", instance.getObjectFromPath("yaml/is"));
        assertEquals("[\"awesomeness\"]", instance.getObjectFromPath("yaml/the").toString());
        assertEquals("{\"is\":\"quite\",\"the\":[\"awesomeness\"]}", instance.getObjectFromPath("yaml").toString());
        assertEquals(json, instance.getObjectFromPath("").toString());
        
    }
}
