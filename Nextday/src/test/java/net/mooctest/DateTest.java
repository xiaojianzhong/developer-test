package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;
import static org.junit.Assert.*;

public class DateTest {
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
  private Day d(Date date) throws Throwable {
    return (Day)getField(Date.class, "d").get(date);
  }
  private void d(Date date, Day d) throws Throwable {
	  getField(Date.class, "d").set(date, d);
  }
  private Month m(Date date) throws Throwable {
    return (Month)getField(Date.class, "m").get(date);
  }
  private void m(Date date, Month m) throws Throwable {
	  getField(Date.class, "m").set(date, m);
  }
  private Year y(Date date) throws Throwable {
    return (Year)getField(Date.class, "y").get(date);
  }
  private void y(Date date, Year y) throws Throwable {
	  getField(Date.class, "y").set(date, y);
  }

  @Rule
  public final SystemOutRule out = new SystemOutRule().enableLog();

  private Date date;
  private Date date1;
  private Date date2;

  @Before
  public void before() throws Throwable {
    date = new Date(1, 1, 2020);
    date1 = new Date(1, 1, 2020);
    date2 = new Date(1, 1, 2020);
  }

  @Test
  public void testDate() throws Throwable {
    date = new Date(1, 1, 2020);
    assertEquals(2020, currentPos(y(date)));
    assertEquals(1, currentPos(m(date)));
    assertEquals(1, currentPos(d(date)));
  }

  @Test
  public void testGetDay() throws Throwable {
    assertEquals(currentPos(d(date)), currentPos(date.getDay()));
  }

  @Test
  public void testGetMonth() throws Throwable {
    assertEquals(currentPos(m(date)), currentPos(date.getMonth()));
  }

  @Test
  public void testGetYear() throws Throwable {
    assertEquals(currentPos(y(date)), currentPos(date.getYear()));
  }

  @Test
  public void testEquals1() throws Throwable {
    assertFalse(date.equals(1));
  }
  @Test
  public void testEquals2() throws Throwable {
    currentPos(d(date1), 1);
    currentPos(m(date1), 1);
    currentPos(y(date1), 2020);
    currentPos(d(date2), 1);
    currentPos(m(date2), 1);
    currentPos(y(date2), 2019);
    assertFalse(date1.equals(date2));
  }
  @Test
  public void testEquals3() throws Throwable {
    currentPos(d(date1), 1);
    currentPos(m(date1), 1);
    currentPos(y(date1), 2020);
    currentPos(d(date2), 1);
    currentPos(m(date2), 1);
    currentPos(y(date2), 2020);
    assertTrue(date1.equals(date2));
  }

  @Test
  public void testToString() throws Throwable {
    currentPos(d(date), 1);
    currentPos(m(date), 1);
    currentPos(y(date), 2020);
    assertEquals("1/1/2020", date.toString());
  }

  @Test
  public void testIncrement1() throws Throwable {
    currentPos(d(date), 1);
    currentPos(m(date), 1);
    currentPos(y(date), 2020);
    date.increment();
    assertEquals(2, currentPos(d(date)));
    assertEquals(1, currentPos(m(date)));
    assertEquals(2020, currentPos(y(date)));
  }
  @Test
  public void testIncrement2() throws Throwable {
    currentPos(d(date), 31);
    currentPos(m(date), 1);
    currentPos(y(date), 2020);
    date.increment();
    assertEquals(1, currentPos(d(date)));
    assertEquals(2, currentPos(m(date)));
    assertEquals(2020, currentPos(y(date)));
  }
  @Test
  public void testIncrement3() throws Throwable {
    currentPos((Day)d(date), 31);
    currentPos((Month)m(date), 12);
    currentPos((Year)y(date), 2020);
    date.increment();
    assertEquals(1, currentPos((Day)d(date)));
    assertEquals(1, currentPos((Month)m(date)));
    assertEquals(2021, currentPos((Year)y(date)));
  }

  @Test
  public void testPrintDate() throws Throwable {
    currentPos(d(date), 1);
    currentPos(m(date), 1);
    currentPos(y(date), 2020);
    date.printDate();
    assertEquals("1/1/2020\n", out.getLogWithNormalizedLineSeparator());
  }
}
