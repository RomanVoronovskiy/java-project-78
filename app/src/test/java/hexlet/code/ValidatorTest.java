package hexlet.code;


import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void test() {
        Validator validator = new Validator();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("test req firstname", validator.string().required());
        schemas.put("testLen req lastname", validator.string().required().minLength(2));

        MapSchema schema = validator.map();
        schema.shape(schemas);

        Map<String, String> jojo = new HashMap<>();
        jojo.put("test req firstname", "Joseph");
        jojo.put("testLen req lastname", "Joestar");
        assertTrue(schema.isValid(jojo));
    }
}
