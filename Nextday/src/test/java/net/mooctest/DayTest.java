package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import org.junit.rules.*;
import static org.junit.Assert.*;

public class DayTest {
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
  private Month m(Day d) throws Throwable {
    return (Month)getField(Day.class, "m").get(d);
  }
  private void m(Day d, Month m) throws Throwable {
	  getField(Day.class, "m").set(d, m);
  }

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  private Year y;
  private Month m;
  private Day d;
  private Day d1;
  private Day d2;

  @Before
  public void setup() throws Throwable {
    y = new Year(2020);
    m = new Month(1, y);
    d = new Day(1, m);
    d1 = new Day(1, m);
    d2 = new Day(1, m);
  }

  @Test
  public void testDay() throws Throwable {
    d = new Day(1, m);
    assertEquals(1, currentPos(d));
    assertSame(m, m(d));
  }

  @Test
  public void testGetDay() throws Throwable {
    assertEquals(currentPos(d), d.getDay());
  }

  @Test
  public void testSetDay1() throws Throwable {
    d.setDay(30, m);
    assertEquals(30, currentPos(d));
    assertSame(m, m(d));
  }
  @Test
  public void testSetDay2() throws Throwable {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Not a valid day");
    d.setDay(30, null);
  }

  @Test
  public void testEquals1() throws Throwable {
    assertFalse(d.equals(1));
  }
  @Test
  public void testEquals2() throws Throwable {
    currentPos(d1, 1);
    currentPos(d2, 2);
    assertFalse(d1.equals(d2));
  }
  @Test
  public void testEquals3() throws Throwable {
    currentPos(d1, 1);
    currentPos(d2, 1);
    assertTrue(d1.equals(d2));
  }

  @Test
  public void testIncrement1() throws Throwable {
    currentPos(d, 1);
    assertTrue(d.increment());
    assertEquals(2, currentPos(d));
  }
  @Test
  public void testIncrement2() throws Throwable {
    currentPos(d, 31);
    assertFalse(d.increment());
    assertEquals(32, currentPos(d));
  }

  @Test
  public void testIsValid1() throws Throwable {
    m(d, null);
    assertFalse(d.isValid());
  }
  @Test
  public void testIsValid2() throws Throwable {
    m(d, m);
    currentPos(d, 0);
    assertFalse(d.isValid());
  }
  @Test
  public void testIsValid3() throws Throwable {
    m(d, m);
    currentPos(d, 1);
    assertTrue(d.isValid());
  }
}
