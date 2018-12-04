package org.elasticsearch.xpack.core.ml.utils;

import java.util.function.Supplier;

/**
 * A {@link Supplier} that caches its return value. This may be useful to make
 * a {@link Supplier} idempotent or for performance reasons if always returning
 * the same instance is acceptable.
 */
public final class CachedSupplier<T> implements Supplier<T> {

    private Supplier<T> supplier;
    private T result;
    private boolean resultSet;

    public CachedSupplier(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public synchronized T get() {
        if (resultSet == false) {
            result = supplier.get();
            resultSet = true;
        }
        return result;
    }

}
