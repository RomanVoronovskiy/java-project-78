package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean isRequired;

    public abstract BaseSchema required();

    public final void setIsRequiredTrue() {
        isRequired = true;
    }

    public final boolean isValid(T validatingObject) {
        if (validatingObject == null) {
            return !isRequired;
        }

        for (Map.Entry<String, Predicate<T>> check : checks.entrySet()) {
            if (!check.getValue().test(validatingObject)) {
                return false;
            }
        }
        return true;
    }

    protected final void addCheck(String checkToAddName, Predicate<T> checkToAdd) {
        checks.put(checkToAddName, checkToAdd);
    }
}
