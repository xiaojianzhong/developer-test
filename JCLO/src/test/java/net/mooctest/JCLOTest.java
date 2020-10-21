package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import org.junit.contrib.java.lang.system.*;
import org.junit.rules.*;
import static org.junit.Assert.*;

enum Color {
  RED, GREEN, BLUE;
}

public class JCLOTest {
  private static Field getField(Class clazz, String name) throws Throwable {
    Field field = clazz.getDeclaredField(name);
    field.setAccessible(true);
    return field;
  }

  private static Method getMethod(Class clazz, String name, Class<?>... parameterTypes) throws Throwable {
    Method method = clazz.getDeclaredMethod(name, parameterTypes);
    method.setAccessible(true);
    return method;
  }

  /* 成员变量 getter & setter，无视访问修饰符 */
  private Field[] fields(JCLO jclo) throws Throwable {
    return (Field[]) getField(JCLO.class, "fields").get(jclo);
  }
  private void fields(JCLO jclo, Field[] fields) throws Throwable {
    getField(JCLO.class, "fields").set(jclo, fields);
  }
  private Object object(JCLO jclo) throws Throwable {
    return getField(JCLO.class, "object").get(jclo);
  }
  private void object(JCLO jclo, Object object) throws Throwable {
    getField(JCLO.class, "object").set(jclo, object);
  }
  private boolean doubleDashes(JCLO jclo) throws Throwable {
    return getField(JCLO.class, "doubleDashes").getBoolean(jclo);
  }
  private void doubleDashes(JCLO jclo, boolean doubleDashes) throws Throwable {
    getField(JCLO.class, "doubleDashes").set(jclo, doubleDashes);
  }
  private boolean hasEquals(JCLO jclo) throws Throwable {
    return getField(JCLO.class, "hasEquals").getBoolean(jclo);
  }
  private void hasEquals(JCLO jclo, boolean hasEquals) throws Throwable {
    getField(JCLO.class, "hasEquals").set(jclo, hasEquals);
  }
  private String prefix(JCLO jclo) throws Throwable {
    return (String) getField(JCLO.class, "prefix").get(jclo);
  }
  private void prefix(JCLO jclo, String prefix) throws Throwable {
    getField(JCLO.class, "prefix").set(jclo, prefix);
  }
  private String[][] aliases(JCLO jclo) throws Throwable {
    return (String[][]) getField(JCLO.class, "aliases").get(jclo);
  }
  private void aliases(JCLO jclo, String[][] aliases) throws Throwable {
    getField(JCLO.class, "aliases").set(jclo, aliases);
  }

  /* 成员方法调用器，无视访问修饰符 */
  private Object getObject(JCLO jclo, Field f) throws Throwable {
    return getMethod(JCLO.class, "getObject", Field.class).invoke(jclo, f);
  }
  private Field getField(JCLO jclo, String key) throws Throwable {
    return (Field) getMethod(JCLO.class, "getField", String.class).invoke(jclo, key);
  }
  private void setObject(JCLO jclo, Field f, Object o) throws Throwable {
    getMethod(JCLO.class, "setObject", Field.class, Object.class).invoke(jclo, f, o);
  }
  private String getArrayType(JCLO jclo, Class type) throws Throwable {
    return (String) getMethod(JCLO.class, "getArrayType", Class.class).invoke(jclo, type);
  }
  private String getUsageType(JCLO jclo, Class type) throws Throwable {
    return (String) getMethod(JCLO.class, "getUsageType", Class.class).invoke(jclo, type);
  }
  private void parseAdditional(JCLO jclo, String[] args, int i) throws Throwable {
    getMethod(JCLO.class, "parseAdditional", String[].class, int.class).invoke(jclo, args, i);
  }
  private Object makeObject(JCLO jclo, String type, String val) throws Throwable {
    return getMethod(JCLO.class, "makeObject", String.class, String.class).invoke(jclo, type, val);
  }
  private Object addToArray(JCLO jclo, Field field, Object o) throws Throwable {
    return (Object) getMethod(JCLO.class, "addToArray", Field.class, Object.class).invoke(jclo, field, o);
  }
  private String getKey(JCLO jclo, String arg) throws Throwable {
    return (String) getMethod(JCLO.class, "getKey", String.class).invoke(jclo, arg);
  }
  private String getBooleanValue(JCLO jclo, String arg) throws Throwable {
    return (String) getMethod(JCLO.class, "getBooleanValue", String.class).invoke(jclo, arg);
  }
  private String getEqualsValue(JCLO jclo, String arg) throws Throwable {
    return (String) getMethod(JCLO.class, "getEqualsValue", String.class).invoke(jclo, arg);
  }

  @Rule
  public final ExpectedException thrown = ExpectedException.none();
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();
  @Rule
  public final SystemErrRule err = new SystemErrRule().enableLog();

  private JCLO jclo;

  @Before
  public void setup() throws Throwable {
    Object object = new Object(){};
    String[][] aliases = new String[][]{};
    jclo = new JCLO("", object, aliases);
  }

  @Test
  public void testJCLO1() throws Throwable {
    Object object = new Object(){};

    jclo = new JCLO(object);

    assertSame(object, object(jclo));
    assertNull(prefix(jclo));
    assertNull(aliases(jclo));
  }
  @Test
  public void testJCLO2() throws Throwable {
    Object object = new Object(){};
    String[][] aliases = new String[][]{
      { "k1", "key1" },
      { "k2", "key2" },
    };

    jclo = new JCLO(object, aliases);

    assertSame(object, object(jclo));
    assertNull(prefix(jclo));
    assertArrayEquals(aliases, aliases(jclo));
  }
  @Test
  public void testJCLO3() throws Throwable {
    Object object = new Object(){};

    jclo = new JCLO("PREFIX", object);

    assertSame(object, object(jclo));
    assertEquals("PREFIX", prefix(jclo));
    assertNull(aliases(jclo));
  }
  @Test
  public void testJCLO4() throws Throwable {
    Object object = new Object(){};
    String[][] aliases = new String[][]{
      { "k1", "key1" },
      { "k2", "key2" },
    };

    jclo = new JCLO("PREFIX", object, aliases);

    assertSame(object, object(jclo));
    assertEquals("PREFIX", prefix(jclo));
    assertArrayEquals(aliases, aliases(jclo));
    assertArrayEquals(object.getClass().getDeclaredFields(), fields(jclo));
    for (Field field : fields(jclo)) {
      assertTrue(field.isAccessible());
    }
  }

  @Test
  public void testToString() throws Throwable {
    Object object = new Object(){
      private int num;
      private Object[] nums;
    };
    getField(object.getClass(), "num").set(object, 1);
    getField(object.getClass(), "nums").set(object, new Object[]{ 1, 2, 3 });
    fields(jclo, new Field[]{
      getField(object.getClass(), "num"),
      getField(object.getClass(), "nums"),
    });
    object(jclo, object);

    assertEquals("int: num = 1\nObject[]: nums = [1, 2, 3]", jclo.toString());
  }

  @Test
  public void testGetObject1() throws Throwable {
    Object object = new Object(){
      private int num;
    };
    getField(object.getClass(), "num").set(object, 1);
    object(jclo, object);

    assertEquals(1, (int)getObject(jclo, getField(object.getClass(), "num")));
  }
  @Test
  public void testGetObject2() throws Throwable {
    thrown.expect(InvocationTargetException.class);
    exit.expectSystemExitWithStatus(1);

    Object object = new Object(){
      private int num;
    };
    object(jclo, object);

    getObject(jclo, object.getClass().getDeclaredField("num"));
    assertNotEquals("", err.getLog());
  }

  @Test
  public void testGetField1() throws Throwable {
    {
      fields(jclo, new Field[]{});
      prefix(jclo, null);

      assertNull(getField(jclo, "key"));
    }
    {
      fields(jclo, new Field[]{});
      prefix(jclo, "");

      assertNull(getField(jclo, "key"));
    }
  }
  @Test
  public void testGetField2() throws Throwable {
    Object object = new Object(){
      private int num1;
      private int num2;
    };
    fields(jclo, new Field[]{
      getField(object.getClass(), "num1"),
      getField(object.getClass(), "num2"),
    });
    prefix(jclo, null);

    assertEquals(getField(object.getClass(), "num2"), getField(jclo, "num2"));
  }

  @Test
  public void testSetObject1() throws Throwable {
    Object object = new Object(){
      private int num;
    };
    object(jclo, object);

    setObject(jclo, getField(object.getClass(), "num"), 2);

    assertEquals(2, (int)getField(object.getClass(), "num").get(object));
  }
  @Test
  public void testSetObject2() throws Throwable {
    Object object = new Object(){
      private int num;
    };
    object(jclo, object);

    setObject(jclo, object.getClass().getDeclaredField("num"), 2);

    assertNotEquals("", err.getLog());
  }

  @Test
  public void testGetArrayType() throws Throwable {
    assertEquals("int", getArrayType(jclo, int[].class));
  }

  @Test
  public void testGetUsageType1() throws Throwable {
    doubleDashes(jclo, true);

    assertEquals("[=boolean]", getUsageType(jclo, boolean.class));
  }
  @Test
  public void testGetUsageType2() throws Throwable {
    doubleDashes(jclo, false);

    assertEquals("", getUsageType(jclo, boolean.class));
  }
  @Test
  public void testGetUsageType3() throws Throwable {
    doubleDashes(jclo, false);

    assertEquals(" int...", getUsageType(jclo, int[].class));
  }
  @Test
  public void testGetUsageType4() throws Throwable {
    doubleDashes(jclo, false);

    assertEquals(" [RED, GREEN, BLUE]", getUsageType(jclo, Color.class));
  }
  @Test
  public void testGetUsageType5() throws Throwable {
    doubleDashes(jclo, false);

    assertEquals(" int", getUsageType(jclo, int.class));
  }

  @Test
  public void testUsage() throws Throwable {
    {
      {
        Object object = new Object(){
          private String[] additional;
          private final int num1 = 0;
          private double num2 = 0.0;
        };
        fields(jclo, new Field[]{
          getField(object.getClass(), "additional"),
          getField(object.getClass(), "num1"),
          getField(object.getClass(), "num2"),
        });
        doubleDashes(jclo, true);
        prefix(jclo, null);

        assertEquals("--num2=double\n", jclo.usage());
      }
      {
        {
          Object object = new Object(){
            private String[] additional;
            private int num = 0;
          };
          fields(jclo, new Field[]{
            getField(object.getClass(), "additional"),
            getField(object.getClass(), "num"),
          });
          doubleDashes(jclo, true);
          prefix(jclo, null);

          assertEquals("--num=int\n", jclo.usage());
        }
        {
          Object object = new Object(){
            private String[] additional;
            private int num = 0;
          };
          fields(jclo, new Field[]{
            getField(object.getClass(), "additional"),
            getField(object.getClass(), "num"),
          });
          doubleDashes(jclo, false);
          prefix(jclo, null);

          assertEquals("-num int\n", jclo.usage());
        }
      }
    }
    {
      Object object = new Object(){
        private String[] additional;
        private int PREFIXnum1 = 0;
        private double num2 = 0.0;
      };
      fields(jclo, new Field[]{
        getField(object.getClass(), "additional"),
        getField(object.getClass(), "PREFIXnum1"),
        getField(object.getClass(), "num2"),
      });
      doubleDashes(jclo, true);
      prefix(jclo, "PREFIX");

      assertEquals("--num1=int\n", jclo.usage());
    }
  }

  @Test
  public void testParseAdditional1() throws Throwable {
    Object object = new Object(){
      private String[] additional;
    };
    fields(jclo, new Field[]{
      getField(object.getClass(), "additional"),
    });
    String[] args = new String[]{
      "ARG1",
      "ARG2",
    };
    prefix(jclo, null);
    object(jclo, object);

    parseAdditional(jclo, args, 0);

    assertArrayEquals(args, (String[])getField(object.getClass(), "additional").get(object));
  }
  @Test
  public void testParseAdditional2() throws Throwable {
    fields(jclo, new Field[]{});
    prefix(jclo, null);

    parseAdditional(jclo, new String[]{}, 0);

    assertEquals("No varible 'additional' found\n", err.getLogWithNormalizedLineSeparator());
  }

  @Test
  public void testAddToArray1() throws Throwable {
    Object object = new Object(){
      private int[] nums;
    };
    getField(object.getClass(), "nums").set(object, null);
    object(jclo, object);

    assertArrayEquals(new int[]{ 1 }, (int[])addToArray(jclo, getField(object.getClass(), "nums"), 1));
  }
  @Test
  public void testAddToArray2() throws Throwable {
    Object object = new Object(){
      private int[] nums;
    };
    getField(object.getClass(), "nums").set(object, new int[]{ 1 });
    object(jclo, object);

    assertArrayEquals(new int[]{ 1, 2 }, (int[])addToArray(jclo, getField(object.getClass(), "nums"), 2));
  }

  @Test
  public void testGetKey1() throws Throwable {
    {
      {
        doubleDashes(jclo, true);
        hasEquals(jclo, false);
        aliases(jclo, null);

        assertEquals("key", getKey(jclo, "--key"));
      }
      {
        doubleDashes(jclo, false);
        hasEquals(jclo, false);
        aliases(jclo, null);

        assertEquals("key", getKey(jclo, "-key"));
      }
    }
    {
      doubleDashes(jclo, true);
      hasEquals(jclo, true);
      aliases(jclo, null);

      assertEquals("key", getKey(jclo, "--key=value"));
    }
  }
  @Test
  public void testGetKey2() throws Throwable {
    doubleDashes(jclo, true);
    hasEquals(jclo, false);
    aliases(jclo, new String[][]{
      { "k1", "key1" },
      { "k2", "key2" },
    });

    assertEquals("key2", getKey(jclo, "--k2"));
  }

  @Test
  public void testBooleanValue1() throws Throwable {
    hasEquals(jclo, false);

    assertEquals("true", getBooleanValue(jclo, "key"));
  }
  @Test
  public void testBooleanValue2() throws Throwable {
    hasEquals(jclo, true);

    assertEquals("true", getBooleanValue(jclo, "key=true"));
  }
  @Test
  public void testBooleanValue3() throws Throwable {
    hasEquals(jclo, true);

    assertEquals("false", getBooleanValue(jclo, "key=value"));
  }

  @Test
  public void testMakeObject1() throws Throwable {
    assertEquals(true, (boolean)makeObject(jclo, "boolean", "true"));
  }
  @Test
  public void testMakeObject2() throws Throwable {
    assertEquals((byte)1, (byte)makeObject(jclo, "byte", "1"));
  }
  @Test
  public void testMakeObject3() throws Throwable {
    assertEquals((short)1, (short)makeObject(jclo, "short", "1"));
  }
  @Test
  public void testMakeObject4() throws Throwable {
    assertEquals(1, (int)makeObject(jclo, "int", "1"));
  }
  @Test
  public void testMakeObject5() throws Throwable {
    assertEquals((float)1.0, (float)makeObject(jclo, "float", "1"), 0.01);
  }
  @Test
  public void testMakeObject6() throws Throwable {
    assertEquals(1.0, (double)makeObject(jclo, "double", "1"), 0.01);
  }
  @Test
  public void testMakeObject7() throws Throwable {
    assertEquals((long)1, (long)makeObject(jclo, "long", "1"));
  }
  @Test
  public void testMakeObject8() throws Throwable {
    assertEquals("STRING", (String)makeObject(jclo, "string", "STRING"));
  }
  @Test
  public void testMakeObject9() throws Throwable {
    assertEquals('S', (char)makeObject(jclo, "char", "STRING"));
  }
  @Test
  public void testMakeObject10() throws Throwable {
    thrown.expect(InvocationTargetException.class);
    // thrown.expectMessage("Unknown type: object");

    makeObject(jclo, "object", "OBJECT");
  }

  @Test
  public void testGetEqualsValue1() throws Throwable {
    thrown.expect(InvocationTargetException.class);
    // thrown.expectMessage("'key' requires '=VALUE'");

    getEqualsValue(jclo, "key");
  }
  @Test
  public void testGetEqualsValue2() throws Throwable {
    assertEquals("value", getEqualsValue(jclo, "key=value"));
  }

  @Test
  public void testParse1() throws Throwable {
    Object object = new Object(){
      private String[] additional;
    };
    fields(jclo, new Field[]{
      getField(object.getClass(), "additional"),
    });
    prefix(jclo, "");
    String[] args = new String[]{ "additional" };
    object(jclo, object);

    jclo.parse(args);

    assertArrayEquals(args, (String[])getField(object.getClass(), "additional").get(object));
  }
  @Test
  public void testParse2() throws Throwable {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("No such option: \"key\"");

    fields(jclo, new Field[]{});
    String[] args = new String[]{ "-key=value", };

    jclo.parse(args);
  }
  @Test
  public void testParse3() throws Throwable {
    Object object = new Object(){
      private int[] nums;
      private boolean is;
      private int num1;
      private int num2;
      private Color color;
    };
    fields(jclo, new Field[]{
      getField(object.getClass(), "nums"),
      getField(object.getClass(), "is"),
      getField(object.getClass(), "num1"),
      getField(object.getClass(), "num2"),
      getField(object.getClass(), "color"),
    });
    prefix(jclo, null);
    object(jclo, object);
    String[] args = new String[]{
      "-nums", "1",
      "-is=true",
      "--num1=1",
      "-num2", "1",
      "-color", "RED",
    };

    jclo.parse(args);

    assertArrayEquals(new int[]{ 1 }, (int[])getField(object.getClass(), "nums").get(object));
    assertEquals(true, (boolean)getField(object.getClass(), "is").get(object));
    assertEquals(1, (int)getField(object.getClass(), "num1").get(object));
    assertEquals(1, (int)getField(object.getClass(), "num2").get(object));
    assertEquals(Color.RED, (Color)getField(object.getClass(), "color").get(object));
  }
}
