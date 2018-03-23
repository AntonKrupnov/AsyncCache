package com.github.antonkrupnov;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OddSortingTest {

    @Test
    public void sort() {
        assertTrue(OddSorting.canBeSorted(new int[]{3, 7, 8}));
        assertTrue(OddSorting.canBeSorted(new int[]{3, 8, 7}));
        assertTrue(OddSorting.canBeSorted(new int[]{3, 8, 7, 9}));
        assertTrue(OddSorting.canBeSorted(new int[]{3, 3, 2}));
        assertTrue(OddSorting.canBeSorted(new int[]{3, 3, 2, 2}));
        assertTrue(OddSorting.canBeSorted(new int[]{3, 3, 2, 2, 3}));
        System.out.println();
        assertFalse(OddSorting.canBeSorted(new int[]{3, 8, 7, 6}));
        assertFalse(OddSorting.canBeSorted(new int[]{3, 8, 7, 6, 5}));
    }
}
