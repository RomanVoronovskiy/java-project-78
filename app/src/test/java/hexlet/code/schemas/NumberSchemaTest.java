package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberSchemaTest {
    private BaseSchema numberSchema;

    @BeforeEach
    void setUp() {
        numberSchema = new NumberSchema();
    }

    @Test
    public void testWithoutRequired() {
        assertTrue(numberSchema.isValid(null));
    }

    @Test
    void testAddPositive() {
        ((NumberSchema) numberSchema).positive();

        assertTrue(numberSchema.isValid(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30})
    void testAddRequiredPositive(int value) {
        numberSchema.required();

        assertTrue(numberSchema.isValid(value));
    }

    @ParameterizedTest
    @CsvSource({"0", "-10"})
    void testAddRequiredNegative(Integer value) {
        ((NumberSchema) numberSchema).positive();
        numberSchema.required();

        assertFalse(numberSchema.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10})
    void testRangeTrue(int value) {
        ((NumberSchema) numberSchema).range(5, 10);

        assertTrue(numberSchema.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 11})
    void testRangeFalse(int value) {
        ((NumberSchema) numberSchema).range(5, 10);

        assertFalse(numberSchema.isValid(value));
    }


}

