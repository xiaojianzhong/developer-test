package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TriangleTest {
  private static Field getField(Class clazz, String name) throws Throwable {
    Field field = clazz.getDeclaredField(name);
    field.setAccessible(true);
    return field;
  }

  private static Field lborderA;
  private static Field lborderB;
  private static Field lborderC;

  private Triangle t;

  @Before
  public void setup() throws Throwable {
    lborderA = getField(Triangle.class, "lborderA");
    lborderB = getField(Triangle.class, "lborderB");
    lborderC = getField(Triangle.class, "lborderC");

    t = new Triangle(1, 2, 3);
  }

  @Test
  public void testTriangle() throws Throwable {
    t = new Triangle(1, 2, 3);
    assertEquals(1, lborderA.getLong(t));
    assertEquals(2, lborderB.getLong(t));
    assertEquals(3, lborderC.getLong(t));
  }

  @Test
  public void testGetBorders() throws Throwable {
    assertArrayEquals(new long[]{
      lborderA.getLong(t),
      lborderB.getLong(t),
      lborderC.getLong(t),
    }, t.getBorders());
  }

  @Test
  public void testIsTriangle1() throws Throwable {
    lborderA.setLong(t, -1);
    assertFalse(t.isTriangle(t));
  }
  @Test
  public void testIsTriangle2() throws Throwable {
    lborderA.setLong(t, 1);
    lborderB.setLong(t, 2);
    lborderC.setLong(t, 3);
    assertFalse(t.isTriangle(t));
  }
  @Test
  public void testIsTriangle3() throws Throwable {
    lborderA.setLong(t, 1);
    lborderB.setLong(t, 1);
    lborderC.setLong(t, 1);
    assertTrue(t.isTriangle(t));
  }

  @Test
  public void testGetType1() throws Throwable {
    lborderA.setLong(t, -1);
    assertEquals("Illegal", t.getType(t));
  }
  @Test
  public void testGetType2() throws Throwable {
    lborderA.setLong(t, 1);
    lborderB.setLong(t, 1);
    lborderC.setLong(t, 1);
    assertEquals("Regular", t.getType(t));
  }
  @Test
  public void testGetType3() throws Throwable {
    lborderA.setLong(t, 2);
    lborderB.setLong(t, 3);
    lborderC.setLong(t, 4);
    assertEquals("Scalene", t.getType(t));
  }
  @Test
  public void testGetType4() throws Throwable {
    lborderA.setLong(t, 2);
    lborderB.setLong(t, 2);
    lborderC.setLong(t, 3);
    assertEquals("Isosceles", t.getType(t));
  }

  @Test
  public void testDiffOfBorders1() throws Throwable {
    assertEquals(1, t.diffOfBorders(2, 1));
  }
  @Test
  public void testDiffOfBorders2() throws Throwable {
    assertEquals(1, t.diffOfBorders(1, 2));
  }
}
