package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    public MapSchema() {
        addCheck("onlyMapsCheck", onlyMapsCheck());
    }

    public MapSchema<T> required() {
        setIsRequiredTrue();
        return this;
    }

    public MapSchema<T> sizeof(int neededSize) {
        addCheck("sizeofCheck", sizeOfCheck(neededSize));
        return this;
    }

    public MapSchema<T> shape(Map<String, BaseSchema<T>> inputSchemas) {
        addCheck("shapeCheck", shapeCheck(inputSchemas));
        return this;
    }

    private Predicate<Map<String, T>> onlyMapsCheck() {
        return Objects::nonNull;
    }

    private Predicate<Map<String, T>> sizeOfCheck(int neededSize) {
        return p -> {
            ObjectMapper mapper = new ObjectMapper();
            Map<?, ?> pMap = mapper.convertValue(p, Map.class);
            return pMap.size() == neededSize;
        };
    }

    private Predicate<Map<String, T>> shapeCheck(Map<String, BaseSchema<T>> inputSchemas) {
        return p -> {
            ObjectMapper mapper = new ObjectMapper();
            Map<?, ?> pMap = mapper.convertValue(p, Map.class);

            for (Map.Entry<String, BaseSchema<T>> schema : inputSchemas.entrySet()) {
                String schemaKey = schema.getKey();
                Object mapToCheckValue = pMap.get(schemaKey);

                if (!schema.getValue().isValid((T) mapToCheckValue)) {
                    return false;
                }
            }
            return true;
        };
    }
}
