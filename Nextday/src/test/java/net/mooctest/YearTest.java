package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import org.junit.rules.*;
import static org.junit.Assert.*;

public class YearTest {
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

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  private Year y;
  private Year y1;
  private Year y2;

  @Before
  public void setup() throws Throwable {
    y = new Year(2020);
    y1 = new Year(2020);
    y2 = new Year(2020);
  }

  @Test
  public void testYear() throws Throwable {
    y = new Year(2020);
    assertEquals(2020, currentPos(y));
  }

  @Test
  public void testGetYear() throws Throwable {
    assertEquals(currentPos(y), y.getYear());
  }

  @Test
  public void testSetYear1() throws Throwable {
    y.setYear(2019);
    assertEquals(2019, currentPos(y));
  }
  @Test
  public void testSetYear2() throws Throwable {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Not a valid month");
    y.setYear(0);
  }

  @Test
  public void testEquals1() throws Throwable {
    assertFalse(y.equals(1));
  }
  @Test
  public void testEquals2() throws Throwable {
    currentPos(y1, 1);
    currentPos(y2, 2);
    assertFalse(y1.equals(y2));
  }
  @Test
  public void testEquals3() throws Throwable {
    currentPos(y1, 1);
    currentPos(y2, 1);
    assertTrue(y1.equals(y2));
  }

  @Test
  public void testIncrement1() throws Throwable {
    currentPos(y, 2020);
    assertTrue(y.increment());
    assertEquals(2021, currentPos(y));
  }
  @Test
  public void testIncrement2() throws Throwable {
    currentPos(y, -1);
    assertTrue(y.increment());
    assertEquals(1, currentPos(y));
  }

  @Test
  public void testIsLeap1() throws Throwable {
    currentPos(y, 4);
    assertTrue(y.isLeap());
  }
  @Test
  public void testIsLeap2() throws Throwable {
    currentPos(y, -5);
    assertTrue(y.isLeap());
  }
  @Test
  public void testIsLeap3() throws Throwable {
    currentPos(y, 1);
    assertFalse(y.isLeap());
  }

  @Test
  public void testIsValid1() throws Throwable {
    currentPos(y, 0);
    assertFalse(y.isValid());
  }
  @Test
  public void testIsValid2() throws Throwable {
    currentPos(y, 1);
    assertTrue(y.isValid());
  }
}
