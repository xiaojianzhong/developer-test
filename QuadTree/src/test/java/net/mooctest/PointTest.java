package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PointTest {
  private static Field getField(Class clazz, String name) throws Throwable {
    Field field = clazz.getDeclaredField(name);
    field.setAccessible(true);
    return field;
  }

  private double x(Point point) throws Throwable {
    return getField(Point.class, "x").getDouble(point);
  }
  private void x(Point point, double x) throws Throwable {
    getField(Point.class, "x").set(point, x);
  }
  private double y(Point point) throws Throwable {
    return getField(Point.class, "y").getDouble(point);
  }
  private void y(Point point, double y) throws Throwable {
    getField(Point.class, "y").set(point, y);
  }
  private Object opt_value(Point point) throws Throwable {
    return getField(Point.class, "opt_value").get(point);
  }
  private void opt_value(Point point, Object opt_value) throws Throwable {
    getField(Point.class, "opt_value").set(point, opt_value);
  }

  private Point point;
  private Point point1;
  private Point point2;

  @Before
  public void setup() throws Throwable {
    point = new Point(1, 1, null);
    point1 = new Point(1, 1, null);
    point2 = new Point(1, 1, null);
  }

  @Test
  public void testPoint() throws Throwable {
    Object opt_value = new Object(){};

    point = new Point(1, 1, opt_value);

    assertEquals(1, x(point), 0.01);
    assertEquals(1, y(point), 0.01);
    assertSame(opt_value, opt_value(point));
  }

  @Test
  public void testGetX() throws Throwable {
    x(point, 2);

    assertEquals(x(point), point.getX(), 0.01);
  }

  @Test
  public void testGetY() throws Throwable {
    y(point, 2);

    assertEquals(y(point), point.getY(), 0.01);
  }

  @Test
  public void testGetValue() throws Throwable {
    Object opt_value = new Object(){};
    opt_value(point, opt_value);

    assertSame(opt_value(point), point.getValue());
  }

  @Test
  public void testSetX() throws Throwable {
    point.setX(2);

    assertEquals(2, x(point), 0.01);
  }

  @Test
  public void testSetY() throws Throwable {
    point.setY(2);

    assertEquals(2, y(point), 0.01);
  }

  @Test
  public void testSetValue() throws Throwable {
    Object opt_value = new Object(){};

    point.setValue(opt_value);

    assertSame(opt_value, opt_value(point));
  }

  @Test
  public void testToString() throws Throwable {
    x(point, 1);
    y(point, 1);

    assertEquals("(1.0, 1.0)", point.toString());
  }

  @Test
  public void testCompareTo1() throws Throwable {
    x(point1, 1);
    x(point2, 2);

    assertEquals(-1, point1.compareTo(point2));
  }
  @Test
  public void testCompareTo2() throws Throwable {
    x(point1, 2);
    x(point2, 1);

    assertEquals(1, point1.compareTo(point2));
  }
  @Test
  public void testCompareTo3() throws Throwable {
    x(point1, 1);
    y(point1, 1);
    x(point2, 1);
    y(point2, 2);

    assertEquals(-1, point1.compareTo(point2));
  }
  @Test
  public void testCompareTo4() throws Throwable {
    x(point1, 1);
    y(point1, 2);
    x(point2, 1);
    y(point2, 1);

    assertEquals(1, point1.compareTo(point2));
  }
  @Test
  public void testCompareTo5() throws Throwable {
    x(point1, 1);
    y(point1, 1);
    x(point2, 1);
    y(point2, 1);

    assertEquals(0, point1.compareTo(point2));
  }
}
