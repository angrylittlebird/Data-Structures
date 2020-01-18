import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * @Author: ZHANG
 * @Date: 2020/1/18
 * @Description:
 */
public class ArrayTest {

    @Test
    public void initialzie() {
        Array<Integer> arr = new Array<>();
        assertEquals(10, arr.getCapacity());
        assertEquals(0, arr.getSize());
    }

    @Test
    public void addLast() throws NoSuchFieldException, IllegalAccessException {
        Array<Integer> arr = new Array<>(2);

        arr.addLast(1);
        arr.addLast(2);
        assertEquals(2, arr.getSize());

        Field data = arr.getClass().getDeclaredField("data");
        data.setAccessible(true);
        Object[] o = (Object[]) data.get(arr);
        assertArrayEquals(new Integer[]{1, 2}, o);
    }

    @Test
    public void get() {
        Array<Integer> arr = new Array<>(2);
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);

        assertTrue(1 == arr.getFirst());
        assertTrue(3 == arr.getLast());
        assertTrue(2 == arr.get(1));
    }

    @Test
    public void resize() {
        Array<Integer> arr = new Array<>(2);
        arr.addLast(1);
        arr.addLast(2);
        assertEquals(2, arr.getCapacity());
        assertEquals(2, arr.getSize());

        arr.addLast(3);
        assertEquals(4, arr.getCapacity());
        assertEquals(3, arr.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowException() {
        Array<Integer> arr = new Array<>(2);
        arr.add(2, null);
    }

    @Test
    public void add() throws NoSuchFieldException, IllegalAccessException {
        Array<Integer> arr = new Array<>(2);
        arr.addLast(1);
        arr.addLast(2);

        arr.add(0, 0);
        assertTrue(0 == arr.getFirst());
        assertEquals(3, arr.getSize());
        assertEquals(4, arr.getCapacity());

        arr.add(3, 3);
        assertTrue(3 == arr.getLast());

        arr.add(3, 100);
        Field data = arr.getClass().getDeclaredField("data");
        data.setAccessible(true);
        Object[] o = (Object[]) data.get(arr);
        assertArrayEquals(new Integer[]{0, 1, 2, 100, 3, null, null, null}, o);
    }

    @Test
    public void addFirst() {
        Array<Integer> arr = new Array<>(2);
        arr.addFirst(1);
        arr.addFirst(2);

        assertTrue(2 == arr.getFirst());
        assertTrue(1 == arr.getLast());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setShouldThrowException() {
        Array<Integer> arr = new Array<>(2);
        arr.add(2, 2);
    }

    @Test
    public void set() {
        Array<Integer> arr = new Array<>(2);
        arr.addLast(1);
        arr.addLast(2);

        arr.set(0, 0);
        assertTrue(0 == arr.get(0));
    }

    @Test
    public void remove(){
        Array<Integer> arr = new Array<>(3);
        arr.addLast(1);
        arr.addLast(2);
        arr.addLast(3);

        Integer remove = arr.remove(1);
        assertTrue(2 == remove);
        assertEquals(2, arr.getSize());

        remove = arr.removeFirst();
        assertTrue(1 == remove);
        remove = arr.removeLast();
        assertTrue(3 == remove);
    }
}
