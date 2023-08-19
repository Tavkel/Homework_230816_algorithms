package org.example.part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    private StringListImpl sut;

    @BeforeEach
    private void initSut() {
        sut = new StringListImpl(new String[]{"mama", "myla", "ramu", "myla"});
    }

    @Test
    void add_shouldReturnAddedString() {
        var actual = sut.add("asdasd");
        assertEquals("asdasd", actual);
        assertArrayEquals(sut.toArray(), new String[]{"mama", "myla", "ramu", "myla", "asdasd"});
    }

    @Test
    void add_shouldThrowNullPointerExceptionIfNullPassed() {
        assertThrows(NullPointerException.class, () -> sut.add(null));
    }

    @Test
    void add_shouldReturnAddedStringInsertedIntoProvidedIndex() {
        var actual = sut.add(1, "ne");
        assertEquals("ne", actual);
        assertArrayEquals(sut.toArray(), new String[]{"mama", "ne", "myla", "ramu", "myla"});
    }

    @Test
    void add_shouldReturnAddedStringInsertedIntoIndexEqualToArrayLength() {
        var actual = sut.add(4, "ne");
        assertEquals("ne", actual);
        assertArrayEquals(sut.toArray(), new String[]{"mama", "myla", "ramu", "myla", "ne"});
    }

    @Test
    void add_shouldThrowOutOfBoundsExceptionIfIndexProvidedIsHigherThanLengthOfTheArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(-1, "asd"));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(5, "asd"));
    }

    @Test
    void set_shouldReturnAddedStringSetIntoProvidedIndex() {
        var actual = sut.set(0, "papa");
        assertEquals("papa", actual);
        assertArrayEquals(sut.toArray(), new String[]{"papa", "myla", "ramu", "myla"});
    }

    @Test
    void set_shouldThrowOutOfBoundsExceptionIfIndexProvidedIsHigherThanLengthOfTheArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.set(-1, "asd"));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.set(4, "asd"));
    }

    @Test
    void remove_shouldRemoveStringFromArrayAndReturnDeletedElement() {
        var actual = sut.remove("mama");
        assertEquals("mama", actual);
        assertArrayEquals(sut.toArray(), new String[]{"myla", "ramu", "myla"});
    }

    @Test
    void remove_shouldRemoveElementWithProvidedIndexFromArrayAndReturnDeletedElement() {
        var actual = sut.remove(1);
        assertEquals("myla", actual);
        assertArrayEquals(sut.toArray(), new String[]{"mama", "ramu", "myla"});

    }

    @Test
    void remove_shouldThrowOutOfBoundExceptionIndexProvidedIsHigherThanLengthOfTheArray() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(4));
    }

    @Test
    void remove_shouldThrowNoSuchElementExceptionIfElementNotFound() {
        assertThrows(NoSuchElementException.class, () -> sut.remove("papa"));
    }

    @Test
    void contains_shouldReturnTrueIfElementIsPresent() {
        assertTrue(sut.contains("ramu"));
    }

    @Test
    void contains_shouldReturnFalseIfElementIsAbsent() {
        assertFalse(sut.contains("papa"));
    }

    @Test
    void indexOf_shouldReturnIndexOfFirstFoundElement() {
        var actual = sut.indexOf("myla");
        assertEquals(1, actual);
    }

    @Test
    void indexOf_shouldReturnMinusOneIfElementNotFound() {
        var actual = sut.indexOf("asd");
        assertEquals(-1, actual);
    }

    @Test
    void lastIndexOf_shouldReturnIndexOfLastFoundElement() {
        var actual = sut.lastIndexOf("myla");
        assertEquals(3, actual);
    }

    @Test
    void lastIndexOf_shouldReturnMinusOneIfElementNotFound() {
        var actual = sut.indexOf("asd");
        assertEquals(-1, actual);
    }

    @Test
    void get_shouldReturnElementOfProvidedIndex() {
        var actual = sut.get(2);
        assertEquals("ramu", actual);
    }

    @Test
    void get_shouldThrowOutOfBoundsExceptionIfIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(4));
    }

    @Test
    void equals_shouldReturnTrueIfListsAreEqual() {
        var check = new StringListImpl(new String[]{"mama", "myla", "ramu", "myla"});
        var check2 = sut;
        assertTrue(sut.equals(check));
        assertTrue(sut.equals(check2));
    }

    @Test
    void equals_shouldReturnFalseIfListsAreNotEqual() {
        var check = new StringListImpl(new String[]{"mama", "myla", "ramu"});
        assertFalse(sut.equals(check));
    }

    @Test
    void equals_shouldThrowNullExceptionIfNullProvided() {
        assertThrows(NullPointerException.class, () -> sut.equals(null));
    }

    @Test
    void size_shouldReturnArrayLength() {
        assertEquals(4, sut.size());
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
        assertArrayEquals(actual, new String[]{"mama", "myla", "ramu", "myla"});
    }
}