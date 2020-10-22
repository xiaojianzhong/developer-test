package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import static org.junit.Assert.*;

public class QuadTreeExceptionTest {
  private static Field getField(Class clazz, String name) throws Throwable {
    Field field = clazz.getDeclaredField(name);
    field.setAccessible(true);
    return field;
  }

  private String detailMessage(Throwable throwable) throws Throwable {
    return (String)getField(Throwable.class, "detailMessage").get(e);
  }
  private void detailMessage(Throwable throwable, String message) throws Throwable {
    getField(Throwable.class, "detailMessage").set(throwable, message);
  }

  private QuadTreeException e;

  @Before
  public void setup() throws Throwable {
    e = new QuadTreeException("message");
  }

  @Test
  public void testQuadTreeException() throws Throwable {
    e = new QuadTreeException("message");

    assertEquals("message", detailMessage(e));
  }
}
