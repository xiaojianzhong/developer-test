package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import org.junit.rules.*;
import static org.junit.Assert.*;

public class QuadTreeTest {
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

  /* Node 成员变量 getter & setter，无视访问修饰符 */
  private double x(Node node) throws Throwable {
    return getField(Node.class, "x").getDouble(node);
  }
  private void x(Node node, double x) throws Throwable {
    getField(Node.class, "x").set(node, x);
  }
  private double y(Node node) throws Throwable {
    return getField(Node.class, "y").getDouble(node);
  }
  private void y(Node node, double y) throws Throwable {
    getField(Node.class, "y").set(node, y);
  }
  private double w(Node node) throws Throwable {
    return getField(Node.class, "w").getDouble(node);
  }
  private void w(Node node, double w) throws Throwable {
    getField(Node.class, "w").set(node, w);
  }
  private double h(Node node) throws Throwable {
    return getField(Node.class, "h").getDouble(node);
  }
  private void h(Node node, double h) throws Throwable {
    getField(Node.class, "h").set(node, h);
  }
  private Node opt_parent(Node node) throws Throwable {
    return (Node)getField(Node.class, "opt_parent").get(node);
  }
  private void opt_parent(Node node, Node opt_parent) throws Throwable {
    getField(Node.class, "opt_parent").set(node, opt_parent);
  }
  private Point point(Node node) throws Throwable {
    return (Point)getField(Node.class, "point").get(node);
  }
  private void point(Node node, Point point) throws Throwable {
    getField(Node.class, "point").set(node, point);
  }
  private NodeType nodetype(Node node) throws Throwable {
    return (NodeType)getField(Node.class, "nodetype").get(node);
  }
  private void nodetype(Node node, NodeType nodetype) throws Throwable {
    getField(Node.class, "nodetype").set(node, nodetype);
  }
  private Node nw(Node node) throws Throwable {
    return (Node)getField(Node.class, "nw").get(node);
  }
  private void nw(Node node, Node nw) throws Throwable {
    getField(Node.class, "nw").set(node, nw);
  }
  private Node ne(Node node) throws Throwable {
    return (Node)getField(Node.class, "ne").get(node);
  }
  private void ne(Node node, Node ne) throws Throwable {
    getField(Node.class, "ne").set(node, ne);
  }
  private Node sw(Node node) throws Throwable {
    return (Node)getField(Node.class, "sw").get(node);
  }
  private void sw(Node node, Node sw) throws Throwable {
    getField(Node.class, "sw").set(node, sw);
  }
  private Node se(Node node) throws Throwable {
    return (Node)getField(Node.class, "se").get(node);
  }
  private void se(Node node, Node se) throws Throwable {
    getField(Node.class, "se").set(node, se);
  }

  /* Point 成员变量 getter & setter，无视访问修饰符 */
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

  /* QuadTree 成员变量 getter & setter，无视访问修饰符 */
  private Node root(QuadTree tree) throws Throwable {
    return (Node)getField(QuadTree.class, "root_").get(tree);
  }
  private void root(QuadTree tree, Node root) throws Throwable {
    getField(QuadTree.class, "root_").set(tree, root);
  }
  private int count(QuadTree tree) throws Throwable {
    return getField(QuadTree.class, "count_").getInt(tree);
  }
  private void count(QuadTree tree, int count) throws Throwable {
    getField(QuadTree.class, "count_").set(tree, count);
  }

  /* QuadTree 成员方法调用器，无视访问修饰符 */
  private boolean intersects(QuadTree tree, double left, double bottom, double right, double top, Node node) throws Throwable {
    return (boolean)getMethod(QuadTree.class, "intersects", double.class, double.class, double.class, double.class, Node.class).invoke(tree, left, bottom, right, top, node);
  }
  private boolean insert(QuadTree tree, Node parent, Point point) throws Throwable {
    return (boolean)getMethod(QuadTree.class, "insert", Node.class, Point.class).invoke(tree, parent, point);
  }
  private void split(QuadTree tree, Node node) throws Throwable {
    getMethod(QuadTree.class, "split", Node.class).invoke(tree, node);
  }
  private Node getQuadrantForPoint(QuadTree tree, Node parent, double x, double y) throws Throwable {
    return (Node)getMethod(QuadTree.class, "getQuadrantForPoint", Node.class, double.class, double.class).invoke(tree, parent, x, y);
  }
  private void setPointForNode(QuadTree tree, Node node, Point point) throws Throwable {
    getMethod(QuadTree.class, "setPointForNode", Node.class, Point.class).invoke(tree, node, point);
  }

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  private QuadTree tree;

  @Before
  public void setup() throws Throwable {
    tree = new QuadTree(1, 1, 2, 2);
  }

  @Test
  public void testQuadTree() throws Throwable {
    tree = new QuadTree(1, 1, 2, 2);

    assertEquals(1, x(root(tree)), 0.01);
    assertEquals(1, y(root(tree)), 0.01);
    assertEquals(1, w(root(tree)), 0.01);
    assertEquals(1, h(root(tree)), 0.01);
  }

  @Test
  public void testGetRootNode() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    root(tree, root);

    assertSame(root(tree), tree.getRootNode());
  }

  @Test
  public void testGetCount() throws Throwable {
    count(tree, 2);

    assertEquals(count(tree), tree.getCount());
  }

  @Test
  public void testClone() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    x(root, 1);
    y(root, 1);
    w(root, 1);
    h(root, 1);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    Object opt_value = new Object(){};
    opt_value(point, opt_value);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    root(tree, root);

    tree = tree.clone();

    assertEquals(1, x(root(tree)), 0.01);
    assertEquals(1, y(root(tree)), 0.01);
    assertEquals(1, w(root(tree)), 0.01);
    assertEquals(1, h(root(tree)), 0.01);
    assertNull(opt_parent(root(tree)));
    assertEquals(1, x(point(root(tree))), 0.01);
    assertEquals(1, y(point(root(tree))), 0.01);
    assertSame(opt_value, opt_value(point(root(tree))));
  }

  @Test
  public void testTraverse1() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.LEAF);
    Func func = new Func(){
      public boolean called = false;
      public void call(QuadTree t, Node n) {
        called = true;
        assertSame(tree, t);
        assertSame(node, n);
      }
    };

    tree.traverse(node, func);

    if (!getField(func.getClass(), "called").getBoolean(func)) {
      fail();
    }
  }
  @Test
  public void testTraverse2() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.LEAF);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    nodetype(ne, NodeType.LEAF);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    nodetype(sw, NodeType.LEAF);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    nodetype(se, NodeType.LEAF);
    se(node, se);
    Func func = new Func(){
      public int ncalled = 0;
      public void call(QuadTree t, Node n) {
        ncalled++;
      }
    };

    tree.traverse(node, func);

    if (getField(func.getClass(), "ncalled").getInt(func) != 4) {
      fail();
    }
  }
  @Test
  public void testTraverse3() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.EMPTY);
    Func func = new Func(){
      public boolean called = false;
      public void call(QuadTree t, Node n) {
        called = true;
      }
    };

    tree.traverse(node, func);

    if (getField(func.getClass(), "called").getBoolean(func)) {
      fail();
    }
  }

  @Test
  public void testGet1() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    Object opt_value = new Object(){};
    opt_value(point, opt_value);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    root(tree, root);

    assertSame(opt_value, tree.get(1, 1, null));
  }
  @Test
  public void testGet2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, NodeType.EMPTY);
    root(tree, root);
    Object opt_default = new Object(){};

    assertSame(opt_default, tree.get(1, 1, opt_default));
  }

  @Test
  public void testSet1() throws Throwable {
    thrown.expect(QuadTreeException.class);
    thrown.expectMessage("Out of bounds : (1.0, 1.0)");

    Node root = new Node(1, 1, 1, 1, null);
    x(root, 2);
    y(root, 2);
    root(tree, root);

    tree.set(1, 1, null);
  }
  @Test
  public void testSet2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    x(root, 1);
    y(root, 1);
    w(root, 1);
    h(root, 1);
    Point point1 = new Point(1, 1, null);
    x(point1, 1);
    y(point1, 1);
    point(root, point1);
    nodetype(root, NodeType.LEAF);
    root(tree, root);
    count(tree, 0);
    Object value = new Object(){};

    tree.set(1, 1, value);

    assertEquals(0, count(tree));
  }
  @Test
  public void testSet3() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    x(root, 1);
    y(root, 1);
    w(root, 1);
    h(root, 1);
    nodetype(root, NodeType.EMPTY);
    root(tree, root);
    count(tree, 0);
    Object value = new Object(){};

    tree.set(1, 1, value);

    assertEquals(1, count(tree));
  }

  @Test
  public void testRemove1() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    Object opt_value = new Object(){};
    opt_value(point, opt_value);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    root(tree, root);
    count(tree, 10);

    assertSame(opt_value, tree.remove(1, 1));
    assertNull(point(root));
    assertEquals(NodeType.EMPTY, nodetype(root));
    assertEquals(9, count(tree));
  }
  @Test
  public void testRemove2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, NodeType.EMPTY);
    root(tree, root);

    assertNull(tree.remove(1, 1));
  }

  @Test
  public void testContains1() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    Object opt_value = new Object(){};
    opt_value(point, opt_value);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    root(tree, root);

    assertTrue(tree.contains(1, 1));
  }
  @Test
  public void testContains2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, NodeType.EMPTY);
    root(tree, root);

    assertFalse(tree.contains(1, 1));
  }

  @Test
  public void testIsEmpty1() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, NodeType.EMPTY);
    root(tree, root);

    assertTrue(tree.isEmpty());
  }
  @Test
  public void testIsEmpty2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, NodeType.POINTER);
    root(tree, root);

    assertFalse(tree.isEmpty());
  }

  @Test
  public void testClear() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    Node nw = new Node(1, 1, 1, 1, null);
    nw(root, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    ne(root, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    sw(root, sw);
    Node se = new Node(1, 1, 1, 1, null);
    se(root, se);
    root(tree, root);
    count(tree, 10);

    tree.clear();

    assertNull(nw(root(tree)));
    assertNull(ne(root(tree)));
    assertNull(sw(root(tree)));
    assertNull(se(root(tree)));
    assertEquals(NodeType.EMPTY, nodetype(root(tree)));
    assertNull(point(root(tree)));
    assertEquals(0, count(tree));
  }

  @Test
  public void testGetKeys() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    Point point1 = new Point(1, 1, null);
    point(nw, point1);
    nodetype(nw, NodeType.LEAF);
    nw(root, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    Point point2 = new Point(1, 1, null);
    point(ne, point2);
    nodetype(ne, NodeType.LEAF);
    ne(root, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    Point point3 = new Point(1, 1, null);
    point(sw, point3);
    nodetype(sw, NodeType.LEAF);
    sw(root, sw);
    Node se = new Node(1, 1, 1, 1, null);
    Point point4 = new Point(1, 1, null);
    point(se, point4);
    nodetype(se, NodeType.LEAF);
    se(root, se);
    root(tree, root);

    assertArrayEquals(new Point[]{
      point2,
      point4,
      point3,
      point1,
    }, tree.getKeys());
  }

  @Test
  public void testGetValues() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    Point point1 = new Point(1, 1, null);
    Object opt_value1 = new Object(){};
    opt_value(point1, opt_value1);
    point(nw, point1);
    nodetype(nw, NodeType.LEAF);
    nw(root, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    Point point2 = new Point(1, 1, null);
    Object opt_value2 = new Object(){};
    opt_value(point2, opt_value2);
    point(ne, point2);
    nodetype(ne, NodeType.LEAF);
    ne(root, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    Point point3 = new Point(1, 1, null);
    Object opt_value3 = new Object(){};
    opt_value(point3, opt_value3);
    point(sw, point3);
    nodetype(sw, NodeType.LEAF);
    sw(root, sw);
    Node se = new Node(1, 1, 1, 1, null);
    Point point4 = new Point(1, 1, null);
    Object opt_value4 = new Object(){};
    opt_value(point4, opt_value4);
    point(se, point4);
    nodetype(se, NodeType.LEAF);
    se(root, se);
    root(tree, root);

    assertArrayEquals(new Object[]{
      opt_value2,
      opt_value4,
      opt_value3,
      opt_value1,
    }, tree.getValues());
  }

  @Test
  public void testSearchIntersect1() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    root(tree, root);

    assertArrayEquals(new Point[]{}, tree.searchIntersect(2, 2, 3, 3));
  }
  @Test
  public void testSearchIntersect2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    root(tree, root);

    assertArrayEquals(new Point[]{
      point,
    }, tree.searchIntersect(0, 0, 2, 2));
  }

  @Test
  public void testSearchWithin1() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    root(tree, root);

    assertArrayEquals(new Point[]{}, tree.searchWithin(2, 2, 3, 3));
  }
  @Test
  public void testSearchWithin2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    point(root, point);
    nodetype(root, NodeType.LEAF);
    root(tree, root);

    assertArrayEquals(new Point[]{
      point,
    }, tree.searchWithin(0, 0, 2, 2));
  }

  @Test
  public void testNavigate1() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.LEAF);
    Func func = new Func(){
      public boolean called = false;
      public void call(QuadTree t, Node n) {
        called = true;
        assertSame(tree, t);
        assertSame(node, n);
      }
    };

    tree.navigate(node, func, 0, 0, 0, 0);

    if (!getField(func.getClass(), "called").getBoolean(func)) {
      fail();
    }
  }
  @Test
  public void testNavigate2() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    x(nw, 2);
    nodetype(nw, NodeType.LEAF);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    x(ne, 2);
    nodetype(ne, NodeType.LEAF);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    x(sw, 2);
    nodetype(sw, NodeType.LEAF);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    x(se, 2);
    nodetype(se, NodeType.LEAF);
    se(node, se);
    Func func = new Func(){
      public int ncalled = 0;
      public void call(QuadTree t, Node n) {
        ncalled++;
      }
    };

    tree.navigate(node, func, 1, 1, 1, 1);

    if (getField(func.getClass(), "ncalled").getInt(func) != 0) {
      fail();
    }
  }
  @Test
  public void testNavigate3() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    x(nw, 2);
    nodetype(nw, NodeType.LEAF);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    x(ne, 2);
    nodetype(ne, NodeType.LEAF);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    x(sw, 2);
    nodetype(sw, NodeType.LEAF);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    x(se, 2);
    nodetype(se, NodeType.LEAF);
    se(node, se);
    Func func = new Func(){
      public int ncalled = 0;
      public void call(QuadTree t, Node n) {
        ncalled++;
      }
    };

    tree.navigate(node, func, 2, 2, 2, 2);

    if (getField(func.getClass(), "ncalled").getInt(func) != 4) {
      fail();
    }
  }
  @Test
  public void testNavigate4() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.EMPTY);
    Func func = new Func(){
      public boolean called = false;
      public void call(QuadTree t, Node n) {
        called = true;
      }
    };

    tree.navigate(node, func, 0, 0, 0, 0);

    if (getField(func.getClass(), "called").getBoolean(func)) {
      fail();
    }
  }

  @Test
  public void testIntersects1() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    x(node, 2);

    assertFalse(intersects(tree, 0, 0, 1, 0, node));
  }
  @Test
  public void testIntersects2() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    x(node, 1);
    y(node, 1);
    w(node, 1);
    h(node, 1);

    assertTrue(intersects(tree, 1, 2, 2, 1, node));
  }

  @Test
  public void testFind1() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, NodeType.EMPTY);

    assertNull(tree.find(root, 1, 1));
  }
  @Test
  public void testFind2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 1);
    y(point, 1);
    point(root, point);
    nodetype(root, NodeType.LEAF);

    assertSame(root, tree.find(root, 1, 1));
  }
  @Test
  public void testFind3() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    x(point, 2);
    y(point, 2);
    point(root, point);
    nodetype(root, NodeType.LEAF);

    assertNull(tree.find(root, 1, 1));
  }
  @Test
  public void testFind4() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    x(root, 1);
    y(root, 1);
    w(root, 2);
    h(root, 2);
    nodetype(root, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.EMPTY);
    nw(root, nw);

    assertNull(tree.find(root, 1, 1));
  }
  @Test
  public void testFind5() throws Throwable {
    thrown.expect(QuadTreeException.class);
    thrown.expectMessage("Invalid nodeType");

    Node root = new Node(1, 1, 1, 1, null);
    nodetype(root, null);

    tree.find(root, 1, 1);
  }

  @Test
  public void testInsert1() throws Throwable {
    Node parent = new Node(1, 1, 1, 1, null);
    nodetype(parent, NodeType.EMPTY);
    Point point = new Point(1, 1, null);

    assertTrue(insert(tree, parent, point));
  }
  @Test
  public void testInsert2() throws Throwable {
    Node parent = new Node(1, 1, 1, 1, null);
    Point point1 = new Point(1, 1, null);
    x(point1, 1);
    y(point1, 1);
    point(parent, point1);
    nodetype(parent, NodeType.LEAF);
    Point point2 = new Point(1, 1, null);
    x(point2, 1);
    y(point2, 1);

    assertFalse(insert(tree, parent, point2));
  }
  @Test
  public void testInsert3() throws Throwable {
    Node parent = new Node(1, 1, 1, 1, null);
    Point point1 = new Point(1, 1, null);
    x(point1, 1);
    y(point1, 1);
    point(parent, point1);
    nodetype(parent, NodeType.LEAF);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.EMPTY);
    nw(parent, nw);
    Point point2 = new Point(1, 1, null);
    x(point2, 2);
    y(point2, 2);

    assertTrue(insert(tree, parent, point2));
  }
  @Test
  public void testInsert4() throws Throwable {
    Node parent = new Node(1, 1, 1, 1, null);
    x(parent, 1);
    y(parent, 1);
    w(parent, 2);
    h(parent, 2);
    nodetype(parent, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.EMPTY);
    nw(parent, nw);
    Point point = new Point(1, 1, null);

    assertTrue(insert(tree, parent, point));
  }
  @Test
  public void testInsert5() throws Throwable {
    thrown.expect(InvocationTargetException.class);
    // thrown.expectMessage("Invalid nodeType in parent");

    Node parent = new Node(1, 1, 1, 1, null);
    nodetype(parent, null);

    insert(tree, parent, null);
  }

  @Test
  public void testSplit() throws Throwable {
    Node node = new Node(1, 1, 1 , 1, null);
    x(node, 1);
    y(node, 1);
    w(node, 2);
    h(node, 2);
    Point point = new Point(1, 1, null);
    point(node, point);

    split(tree, node);

    assertNull(point(node));
    assertEquals(NodeType.POINTER, nodetype(node));
    assertEquals(1, x(nw(node)), 0.01);
    assertEquals(1, y(nw(node)), 0.01);
    assertEquals(1, w(nw(node)), 0.01);
    assertEquals(1, h(nw(node)), 0.01);
    assertSame(node, opt_parent(nw(node)));
    assertEquals(2, x(ne(node)), 0.01);
    assertEquals(1, y(ne(node)), 0.01);
    assertEquals(1, w(ne(node)), 0.01);
    assertEquals(1, h(ne(node)), 0.01);
    assertSame(node, opt_parent(ne(node)));
    assertEquals(1, x(sw(node)), 0.01);
    assertEquals(2, y(sw(node)), 0.01);
    assertEquals(1, w(sw(node)), 0.01);
    assertEquals(1, h(sw(node)), 0.01);
    assertSame(node, opt_parent(nw(node)));
    assertEquals(2, x(se(node)), 0.01);
    assertEquals(2, y(se(node)), 0.01);
    assertEquals(1, w(se(node)), 0.01);
    assertEquals(1, h(se(node)), 0.01);
    assertSame(node, opt_parent(se(node)));
  }

  @Test
  public void testBalance1() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.EMPTY);

    tree.balance(node);
  }
  @Test
  public void testBalance2() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    opt_parent(node, null);
    nodetype(node, NodeType.LEAF);

    tree.balance(node);
  }
  @Test
  public void testBalance3() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    Node opt_parent = new Node(1, 1, 1, 1, null);
    opt_parent(node, opt_parent);
    nodetype(node, NodeType.LEAF);

    tree.balance(node);
  }
  @Test
  public void testBalance4() throws Throwable {
    thrown.expect(NullPointerException.class);

    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.EMPTY);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    nodetype(ne, NodeType.EMPTY);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    nodetype(sw, NodeType.EMPTY);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    nodetype(se, NodeType.EMPTY);
    se(node, se);

    tree.balance(node);

    /*
    assertEquals(NodeType.EMPTY, nodetype(node));
    assertNull(nw(node));
    assertNull(ne(node));
    assertNull(sw(node));
    assertNull(se(node));
    */
  }
  @Test
  public void testBalance5() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.POINTER);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    nodetype(ne, NodeType.EMPTY);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    nodetype(sw, NodeType.EMPTY);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    nodetype(se, NodeType.EMPTY);
    se(node, se);

    tree.balance(node);

    assertEquals(NodeType.POINTER, nodetype(node));
    assertNotNull(nw(node));
    assertNotNull(ne(node));
    assertNotNull(sw(node));
    assertNotNull(se(node));
  }
  @Test
  public void testBalance6() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    Node opt_parent = new Node(1, 1, 1, 1, null);
    nodetype(opt_parent, NodeType.EMPTY);
    opt_parent(node, opt_parent);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    point(nw, point);
    nodetype(nw, NodeType.LEAF);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    nodetype(ne, NodeType.EMPTY);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    nodetype(sw, NodeType.EMPTY);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    nodetype(se, NodeType.EMPTY);
    se(node, se);

    tree.balance(node);

    assertEquals(NodeType.LEAF, nodetype(node));
    assertNull(nw(node));
    assertNull(ne(node));
    assertNull(sw(node));
    assertNull(se(node));
    assertSame(point, point(node));
  }
  @Test
  public void testBalance7() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    Node opt_parent = new Node(1, 1, 1, 1, null);
    nodetype(opt_parent, NodeType.EMPTY);
    opt_parent(node, opt_parent);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.EMPTY);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    point(ne, point);
    nodetype(ne, NodeType.LEAF);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    nodetype(sw, NodeType.EMPTY);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    nodetype(se, NodeType.EMPTY);
    se(node, se);

    tree.balance(node);

    assertEquals(NodeType.LEAF, nodetype(node));
    assertNull(nw(node));
    assertNull(ne(node));
    assertNull(sw(node));
    assertNull(se(node));
    assertSame(point, point(node));
  }
  @Test
  public void testBalance8() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    Node opt_parent = new Node(1, 1, 1, 1, null);
    nodetype(opt_parent, NodeType.EMPTY);
    opt_parent(node, opt_parent);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.EMPTY);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    nodetype(ne, NodeType.EMPTY);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    point(sw, point);
    nodetype(sw, NodeType.LEAF);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    nodetype(se, NodeType.EMPTY);
    se(node, se);

    tree.balance(node);

    assertEquals(NodeType.LEAF, nodetype(node));
    assertNull(nw(node));
    assertNull(ne(node));
    assertNull(sw(node));
    assertNull(se(node));
    assertSame(point, point(node));
  }
  @Test
  public void testBalance9() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    Node opt_parent = new Node(1, 1, 1, 1, null);
    nodetype(opt_parent, NodeType.EMPTY);
    opt_parent(node, opt_parent);
    nodetype(node, NodeType.POINTER);
    Node nw = new Node(1, 1, 1, 1, null);
    nodetype(nw, NodeType.EMPTY);
    nw(node, nw);
    Node ne = new Node(1, 1, 1, 1, null);
    nodetype(ne, NodeType.EMPTY);
    ne(node, ne);
    Node sw = new Node(1, 1, 1, 1, null);
    nodetype(sw, NodeType.EMPTY);
    sw(node, sw);
    Node se = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    point(se, point);
    nodetype(se, NodeType.LEAF);
    se(node, se);

    tree.balance(node);

    assertEquals(NodeType.LEAF, nodetype(node));
    assertNull(nw(node));
    assertNull(ne(node));
    assertNull(sw(node));
    assertNull(se(node));
    assertSame(point, point(node));
  }
  @Test
  public void testBalance10() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, null);

    tree.balance(node);
  }

  @Test
  public void testGetQuadrantForPoint1() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    x(root, 1);
    y(root, 1);
    w(root, 2);
    h(root, 2);
    Node nw = new Node(1, 1, 1, 1, null);
    nw(root, nw);

    assertSame(nw, getQuadrantForPoint(tree, root, 1, 1));
  }
  @Test
  public void testGetQuadrantForPoint2() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    x(root, 1);
    y(root, 1);
    w(root, 2);
    h(root, 2);
    Node sw = new Node(1, 1, 1, 1, null);
    sw(root, sw);

    assertSame(sw, getQuadrantForPoint(tree, root, 1, 3));
  }
  @Test
  public void testGetQuadrantForPoint3() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    x(root, 1);
    y(root, 1);
    w(root, 2);
    h(root, 2);
    Node ne = new Node(1, 1, 1, 1, null);
    ne(root, ne);

    assertSame(ne, getQuadrantForPoint(tree, root, 3, 1));
  }
  @Test
  public void testGetQuadrantForPoint4() throws Throwable {
    Node root = new Node(1, 1, 1, 1, null);
    x(root, 1);
    y(root, 1);
    w(root, 2);
    h(root, 2);
    Node se = new Node(1, 1, 1, 1, null);
    se(root, se);

    assertSame(se, getQuadrantForPoint(tree, root, 3, 3));
  }

  @Test
  public void testSetPointForNode1() throws Throwable {
    thrown.expect(InvocationTargetException.class);
    // thrown.expectMessage("Can not set point for node of type POINTER");

    Node node = new Node(1, 1, 1, 1, null);
    nodetype(node, NodeType.POINTER);

    setPointForNode(tree, node, null);
  }
  @Test
  public void testSetPointForNode2() throws Throwable {
    Node node = new Node(1, 1, 1, 1, null);
    Point point = new Point(1, 1, null);
    nodetype(node, NodeType.EMPTY);

    setPointForNode(tree, node, point);

    assertEquals(NodeType.LEAF, nodetype(node));
    assertSame(point, point(node));
  }
}
