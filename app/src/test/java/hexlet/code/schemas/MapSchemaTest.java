package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    private BaseSchema mapSchema;
    private Map<String, String> data;

    @BeforeEach
    void setUp() {
        mapSchema = new MapSchema();
        data = new HashMap<>();
        data.put("key1", "value1");
    }

    @Test
    void testWithoutRequired() {
        assertTrue(mapSchema.isValid(null));
    }

    @Test
    void testAddRequiredNegative() {
        mapSchema.required();

        assertFalse(mapSchema.isValid(null));
    }

    @Test
    void testAddRequiredPositive() {
        mapSchema.required();

        assertTrue(mapSchema.isValid(new HashMap<>()));
    }

    @Test
    void testAddRequiredPositiveAndData() {
        mapSchema.required();

        assertTrue(mapSchema.isValid(data));
    }

    @Test
    void testSizeFalse() {
        ((MapSchema) mapSchema).sizeof(2);

        assertFalse(mapSchema.isValid(data));
    }

    @Test
    void testSizeTrue() {
        ((MapSchema) mapSchema).sizeof(2);

        data.put("key2", "value2");

        assertTrue(mapSchema.isValid(data));
    }
}
