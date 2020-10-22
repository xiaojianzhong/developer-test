package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import static org.junit.Assert.*;

public class NextdayTest {
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

  private Nextday nextday;
  private Date date;

  @Before
  public void setup() throws Throwable {
    nextday = new Nextday();
    date = new Date(1, 1, 2020);
  }

  @Test
  public void testNextDay() throws Throwable {
    currentPos(d(date), 1);
    currentPos(m(date), 1);
    currentPos(y(date), 2020);
    date = Nextday.nextDay(date);
    assertEquals(2, currentPos(d(date)));
    assertEquals(1, currentPos(m(date)));
    assertEquals(2020, currentPos(y(date)));
  }
}
