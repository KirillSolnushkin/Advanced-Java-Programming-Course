package collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    @Test
    void testAdd() {
        list.add("A");
        assertEquals(1, list.size());

        list.add("B");
        assertEquals(2, list.size());

        assertEquals("A", list.put(0, "C"));
        assertEquals("B", list.put(1, "D"));
    }

    @Test
    void testPut() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("B", list.put(1, "X"));

        assertEquals("X", list.put(1, "Y"));

        assertThrows(IndexOutOfBoundsException.class, () -> list.put(-1, "Z"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.put(3, "Z"));
    }

    @Test
    void testRemoveByIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("B", list.remove(1));

        assertEquals(2, list.size());

        assertEquals("A", list.put(0, "X"));
        assertEquals("C", list.put(1, "Y"));

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
    }

    @Test
    void testRemoveByObject() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(null);

        assertTrue(list.remove("B"));

        assertEquals(3, list.size());

        assertFalse(list.contains("B"));

        assertTrue(list.remove(null));
        assertEquals(2, list.size());

        assertFalse(list.remove("Z"));
    }

    @Test
    void testAddAll() {
        java.util.List<String> toAdd = Arrays.asList("A", "B", "C");
        assertTrue(list.addAll(toAdd));

        assertEquals(3, list.size());

        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
        assertTrue(list.contains("C"));

        assertFalse(list.addAll(Arrays.asList()));

        assertEquals(3, list.size());

        assertFalse(list.addAll(null));
    }

    @Test
    void testContains() {
        list.add("A");
        list.add("B");
        list.add(null);

        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
        assertTrue(list.contains(null));

        assertFalse(list.contains("Z"));
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());

        list.add("A");
        assertEquals(1, list.size());

        list.add("B");
        list.add("C");
        assertEquals(3, list.size());

        list.remove("B");
        assertEquals(2, list.size());
    }

    @Test
    void testAutoResize() {
        for (int i = 0; i < 20; i++) {
            list.add("Element " + i);
        }

        assertEquals(20, list.size());

        assertEquals("Element 0", list.put(0, "Element 0"));
        assertEquals("Element 19", list.put(19, "Element 19"));
    }

    @Test
    void testInitialCapacity() {
        ArrayList<String> smallList = new ArrayList<>(2);

        smallList.add("A");
        smallList.add("B");
        smallList.add("C");

        assertEquals(3, smallList.size());

        assertThrows(IllegalArgumentException.class, () -> new ArrayList<>(-1));
    }
}