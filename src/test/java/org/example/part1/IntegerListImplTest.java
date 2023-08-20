package org.example.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    private IntegerListImpl sut;

    @BeforeEach
    private void initSut() {
        sut = new IntegerListImpl(
                new Integer[]{234, 546, 678, 890, 35, 324}
        );
    }

    @Test
    void add_shouldReturnAddedInteger() {
        var actual = sut.add(154);
        assertEquals(154, actual);
        assertArrayEquals(new Integer[]{234, 546, 678, 890, 35, 324, 154}, sut.toArray());
    }

    @Test
    void add_shouldThrowNullPointerExceptionIfNullPassed() {
        assertThrows(NullPointerException.class, () -> sut.add(null));
    }

    @Test
    void add_shouldReturnAddedIntegerInsertedIntoIndexEqualToSize() {
        var actual = sut.add(6, 123);
        assertEquals(123, actual);
        assertArrayEquals(new Integer[]{234, 546, 678, 890, 35, 324, 123}, sut.toArray());
    }

    @Test
    void add_shouldThrowIndexOutOfBoundsExceptionIfIndexBiggerThanSizeOrLessThanZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(7, 123));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(-1, 123));
    }

    @Test
    void set_shouldReturnInsertedIntegerSetIntoProvidedIndex() {
        var actual = sut.set(0, 123);
        assertEquals(123, actual);
        assertArrayEquals(new Integer[]{123, 546, 678, 890, 35, 324}, sut.toArray());
    }

    @Test
    void set_shouldThrowOutOfBoundsExceptionIfIndexProvidedIsHigherThanLengthOfTheArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.set(-1, 123));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.set(6, 123));
    }

    @Test
    void remove_shouldRemoveIntegerFromArrayAndReturnDeletedElement() {
        var actual = sut.remove((Integer) 890);
        assertEquals(890, actual);
        assertArrayEquals(new Integer[] {35,234, 324,546,678}, sut.toArray());
    }

    @Test
    void remove_shouldThrowNoSuchElementExceptionIfElementNotFound() {
        assertThrows(NoSuchElementException.class, () -> sut.remove(-1));
    }

    @Test
    void contains_shouldReturnTrueIfElementIsPresent() {
        assertTrue(sut.contains(890));
    }

    @Test
    void contains_shouldReturnFalseIfElementIsAbsent() {
        assertFalse(sut.contains(89));
    }

    @Test
    void indexOf_shouldReturnIndexOfFirstFoundElement() {
        var actual = sut.indexOf(890);
        assertEquals(5, actual);
    }

    @Test
    void indexOf_shouldReturnMinusOneIfElementNotFound() {
        var actual = sut.indexOf(-8);
        assertEquals(-1, actual);
    }

    @Test
    void lastIndexOf_shouldReturnIndexOfLastFoundElement() {
        sut.add(35);
        var actual = sut.lastIndexOf(35);
        assertEquals(1, actual);
    }

    @Test
    void lastIndexOf_shouldReturnMinusOneIfElementNotFound() {
        var actual = sut.indexOf(8765273);
        assertEquals(-1, actual);
    }

    @Test
    void get_shouldReturnElementOfProvidedIndex() {
        var actual = sut.get(2);
        assertEquals(678, actual);
    }

    @Test
    void get_shouldThrowOutOfBoundsExceptionIfIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(6));
    }

    @Test
    void equals_shouldReturnTrueIfListsAreEqual() {
        var check = new IntegerListImpl(new Integer[]{234, 546, 678, 890, 35, 324});
        var check2 = sut;
        assertTrue(sut.equals(check));
        assertTrue(sut.equals(check2));
    }

    @Test
    void equals_shouldReturnFalseIfListsAreNotEqual() {
        var check = new IntegerListImpl(new Integer[]{234, 1546, 678, 890, 35, 324});
        assertFalse(sut.equals(check));
    }

    @Test
    void equals_shouldThrowNullExceptionIfNullProvided() {
        assertThrows(NullPointerException.class, () -> sut.equals(null));
    }

    @Test
    void size_shouldReturnArrayLength() {
        assertEquals(6, sut.size());
    }

    @Test
    void isEmpty_shouldReturnFalseIfThereAreElements() {
        assertFalse(sut.isEmpty());
    }

    @Test
    void isEmpty_shouldReturnTrueIfThereAreNoElements() {
        assertTrue(new StringListImpl().isEmpty());
    }

    @Test
    void clear_shouldClearArray() {
        sut.clear();
        assertTrue(sut.isEmpty());
    }

    @Test
    void toArray_shouldReturnArray() {
        var actual = sut.toArray();
        assertArrayEquals(actual, new Integer[]{234, 546, 678, 890, 35, 324});
    }
}