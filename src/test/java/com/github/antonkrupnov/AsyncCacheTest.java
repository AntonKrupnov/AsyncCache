package com.github.antonkrupnov;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Anton 22.03.2018
 */
public class AsyncCacheTest {

    @Test
    public void shouldNotReturnExpiredValues() throws Exception {
        int expiration = 1000;
        AsyncCache<String, Integer> cache = new AsyncCache<>(expiration);
        cache.getOrCompute("key", s -> CompletableFuture.completedFuture(7));

        Thread.sleep(expiration + 1);
        CompletableFuture<Integer> result = cache.getOrCompute("key", s -> CompletableFuture.completedFuture(3));

        assertEquals(3, result.get().intValue());
    }

    @Test
    public void shouldReturnValueFromCache() throws Exception {
        AsyncCache<String, Integer> cache = new AsyncCache<>(100_000);
        cache.getOrCompute("key", s -> CompletableFuture.completedFuture(7));

        CompletableFuture<Integer> result = cache.getOrCompute("key", s -> CompletableFuture.completedFuture(3));

        assertTrue(cache.containsKey("key"));
        assertEquals(7, result.get().intValue());
    }

    @Test
    public void shouldNotStoreFail() throws Exception {
        AsyncCache<String, Integer> cache = new AsyncCache<>(100_000);
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        completableFuture.cancel(false);
        CompletableFuture<Integer> firstResult = cache.getOrCompute("key", s -> completableFuture);

        CompletableFuture<Integer> result = cache.getOrCompute("key", s -> CompletableFuture.completedFuture(3));

        assertTrue(firstResult.isCompletedExceptionally());
        assertTrue(cache.containsKey("key"));
        assertEquals(3, result.get().intValue());
    }
}