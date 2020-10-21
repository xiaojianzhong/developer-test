package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import org.junit.rules.*;
import static org.junit.Assert.*;

public class MonthTest {
  private static Field getField(Class clazz, String name) throws Throwable {
    Field field = clazz.getDeclaredField(name);
    field.setAccessible(true);
    return field;
  }

  private int currentPos(Object obj) throws Throwable {
    return getField(CalendarUnit.class, "currentPos").getInt(obj);
  }
  private void currentPos(Object obj, int currentPos) throws Throwable {
	  getField(CalendarUnit.class, "currentPos").setInt(obj, currentPos);
  }
  private Year y(Month m) throws Throwable {
    return (Year)getField(Month.class, "y").get(m);
  }
  private void y(Month m, Year y) throws Throwable {
	  getField(Month.class, "y").set(m, y);
  }
  private int[] sizeIndex(Month m) throws Throwable {
    return (int[])getField(Month.class, "sizeIndex").get(m);
  }
  private void sizeIndex(Month m, int[] sizeIndex) throws Throwable {
	  getField(Month.class, "sizeIndex").set(m, sizeIndex);
  }

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  private Year y;
  private Month m;
  private Month m1;
  private Month m2;

  @Before
  public void before() throws Throwable {
    y = new Year(2020);
    m = new Month(1, y);
    m1 = new Month(1, y);
    m2 = new Month(1, y);
  }

  @Test
  public void testMonth() throws Throwable {
    m = new Month(1, y);
    assertEquals(1, currentPos(m));
    assertSame(y, y(m));
  }

  @Test
  public void testGetMonth() throws Throwable {
    assertEquals(currentPos(m), m.getMonth());
  }

  @Test
  public void testSetMonth1() throws Throwable {
    m.setMonth(2, y);
    assertEquals(2, currentPos(m));
    assertSame(y, y(m));
  }
  @Test
  public void testSetMonth2() throws Throwable {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Not a valid month");
    m.setMonth(2, null);
  }

  @Test
  public void testEquals1() throws Throwable {
    assertFalse(m.equals(1));
  }
  @Test
  public void testEquals2() throws Throwable {
    currentPos(m1, 1);
    currentPos(m2, 2);
    assertFalse(m1.equals(m2));
  }
  @Test
  public void testEquals3() throws Throwable {
    currentPos(m1, 1);
    currentPos(m2, 1);
    assertTrue(m1.equals(m2));
  }

  @Test
  public void testGetMonthSize1() throws Throwable {
    currentPos(y(m), 2020);
    currentPos(m, 1);
    assertEquals(31, m.getMonthSize());
    assertEquals(29, sizeIndex(m)[1]);
  }
  @Test
  public void testGetMonthSize2() throws Throwable {
    currentPos(y(m), 2019);
    currentPos(m, 1);
    assertEquals(31, m.getMonthSize());
    assertEquals(28, sizeIndex(m)[1]);
  }

  @Test
  public void testIncrement1() throws Throwable {
    currentPos(m, 12);
    assertFalse(m.increment());
    assertEquals(13, currentPos(m));
  }
  @Test
  public void testIncrement2() throws Throwable {
    currentPos(m, 1);
    assertTrue(m.increment());
    assertEquals(2, currentPos(m));
  }

  @Test
  public void testIsValid1() throws Throwable {
    y(m, null);
    assertFalse(m.isValid());
  }
  @Test
  public void testIsValid2() throws Throwable {
    y(m, y);
    currentPos(m, 0);
    assertFalse(m.isValid());
  }
  @Test
  public void testIsValid3() throws Throwable {
    y(m, y);
    currentPos(m, 1);
    assertTrue(m.isValid());
  }
}
