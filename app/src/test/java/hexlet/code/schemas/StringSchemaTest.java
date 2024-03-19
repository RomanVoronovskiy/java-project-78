package hexlet.code.schemas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StringSchemaTest {
    private BaseSchema stringSchema;

    @BeforeEach
    void setUp() {
        stringSchema = new StringSchema();
    }

    @Test
    public void testWithoutRequired() {
        Assertions.assertTrue(stringSchema.isValid(null));
    }

    @Test
    void testAddRequiredPositive() {
        stringSchema.required();

        Assertions.assertTrue(stringSchema.isValid("One"));
    }

    @Test
    void testAddRequiredNegative() {
        stringSchema.required();

        Assertions.assertFalse(stringSchema.isValid(""));
        Assertions.assertFalse(stringSchema.isValid(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"OneAndTwo", "TwoAndFour"})
    void testMinLengthTrue(String value) {
        ((StringSchema) stringSchema).minLength(5);

        Assertions.assertTrue(stringSchema.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "qwe"})
    void testMinLengthFalse(String value) {
        ((StringSchema) stringSchema).minLength(5);

        Assertions.assertFalse(stringSchema.isValid(value));
    }
}
