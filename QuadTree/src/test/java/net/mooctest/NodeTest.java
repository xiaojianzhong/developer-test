package net.mooctest;

import java.lang.reflect.*;
import org.junit.*;
import static org.junit.Assert.*;

public class NodeTest {
  private static Field getField(Class clazz, String name) throws Throwable {
    Field field = clazz.getDeclaredField(name);
    field.setAccessible(true);
    return field;
  }

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

  private Node node;

  @Before
  public void setup() throws Throwable {
    node = new Node(1, 1, 1, 1, null);
  }

  @Test
  public void testNode() throws Throwable {
    Node opt_parent = new Node(1, 1, 1, 1, null);

    node = new Node(1, 1, 1, 1, opt_parent);

    assertEquals(1, x(node), 0.01);
    assertEquals(1, y(node), 0.01);
    assertEquals(1, w(node), 0.01);
    assertEquals(1, h(node), 0.01);
    assertSame(opt_parent, opt_parent(node));
  }

  @Test
  public void testGetX() throws Throwable {
    x(node, 2);

    assertEquals(x(node), node.getX(), 0.01);
  }

  @Test
  public void testGetY() throws Throwable {
    y(node, 2);

    assertEquals(y(node), node.getY(), 0.01);
  }

  @Test
  public void testGetW() throws Throwable {
    w(node, 2);

    assertEquals(w(node), node.getW(), 0.01);
  }

  @Test
  public void testGetH() throws Throwable {
    h(node, 2);

    assertEquals(h(node), node.getH(), 0.01);
  }

  @Test
  public void testGetParent() throws Throwable {
    Node opt_parent = new Node(1, 1, 1, 1, null);
    opt_parent(node, opt_parent);

    assertSame(opt_parent(node), node.getParent());
  }

  @Test
  public void testGetPoint() throws Throwable {
    Point point = new Point(1, 1, null);
    point(node, point);

    assertSame(point(node), node.getPoint());
  }

  @Test
  public void testGetNodeType() throws Throwable {
    nodetype(node, NodeType.EMPTY);

    assertSame(nodetype(node), node.getNodeType());
  }

  @Test
  public void testGetNw() throws Throwable {
    Node nw = new Node(1, 1, 1, 1, null);

    nw(node, nw);

    assertSame(nw(node), node.getNw());
  }

  @Test
  public void testGetNe() throws Throwable {
    Node ne = new Node(1, 1, 1, 1, null);

    ne(node, ne);

    assertSame(ne(node), node.getNe());
  }

  @Test
  public void testGetSw() throws Throwable {
    Node sw = new Node(1, 1, 1, 1, null);

    sw(node, sw);

    assertSame(sw(node), node.getSw());
  }

  @Test
  public void testGetSe() throws Throwable {
    Node se = new Node(1, 1, 1, 1, null);

    se(node, se);

    assertSame(se(node), node.getSe());
  }

  @Test
  public void testSetX() throws Throwable {
    node.setX(2);

    assertEquals(2, x(node), 0.01);
  }

  @Test
  public void testSetY() throws Throwable {
    node.setY(2);

    assertEquals(2, y(node), 0.01);
  }

  @Test
  public void testSetW() throws Throwable {
    node.setW(2);

    assertEquals(2, w(node), 0.01);
  }

  @Test
  public void testSetH() throws Throwable {
    node.setH(2);

    assertEquals(2, h(node), 0.01);
  }

  @Test
  public void testSetParent() throws Throwable {
    Node opt_parent = new Node(1, 1, 1, 1, null);

    node.setParent(opt_parent);

    assertSame(opt_parent, opt_parent(node));
  }

  @Test
  public void testSetPoint() throws Throwable {
    Point point = new Point(1, 1, null);

    node.setPoint(point);

    assertSame(point, point(node));
  }

  @Test
  public void testSetNodeType() throws Throwable {
    node.setNodeType(NodeType.EMPTY);

    assertSame(NodeType.EMPTY, nodetype(node));
  }

  @Test
  public void testSetNw() throws Throwable {
    Node nw = new Node(1, 1, 1, 1, null);

    node.setNw(nw);

    assertSame(nw, nw(node));
  }

  @Test
  public void testSetNe() throws Throwable {
    Node ne = new Node(1, 1, 1, 1, null);

    node.setNe(ne);

    assertSame(ne, ne(node));
  }

  @Test
  public void testSetSw() throws Throwable {
    Node sw = new Node(1, 1, 1, 1, null);

    node.setSw(sw);

    assertSame(sw, sw(node));
  }

  @Test
  public void testSetSe() throws Throwable {
    Node se = new Node(1, 1, 1, 1, null);

    node.setSe(se);

    assertSame(se, se(node));
  }
}
