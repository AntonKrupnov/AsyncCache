package com.github.antonkrupnov;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.supplyAsync;


@SuppressWarnings("WeakerAccess")
public class AsyncCache<K, V> {

    private final long expiration;
    private ConcurrentMap<K, Value<V>> cache = new ConcurrentHashMap<>();

    public AsyncCache(long expiration) {
        this.expiration = expiration;
    }

    public CompletableFuture<V> getOrCompute(K key, Function<K, CompletableFuture<V>> supplier) {
        if (containsKey(key)) {
            return completedFuture(cache.get(key).value);
        } else {
            return supplier.apply(key)
                    .thenCompose(v -> {
                        Value<V> value = new Value<>(v, System.currentTimeMillis());
                        cache.put(key, value);
                        return supplyAsync(() -> v);
                    });
        }
    }

    public boolean containsKey(K key) {
        Value value = cache.get(key);
        if (isExpired(value)) {
            cache.remove(key);
            return false;
        }
        return value != null;
    }

    private boolean isExpired(Value value) {
        return value != null && System.currentTimeMillis() > value.timestamp + expiration;
    }

    private static class Value<V> {
        private V value;
        private long timestamp;

        Value(V value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}

