package ru.tinkoff.ps.ops.test.swt.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class TestSearch {
    private Node node0, node1, node2, node3;

    @BeforeEach
    void setUp() {
        node0 = new Node(0);
        node1 = new Node(1);
        node2 = new Node(2);
        node3 = new Node(3);
    }

    @Test
    public void testOneNodeGraph() {
        Assertions.assertEquals(0, Node.search(node0, node0));
    }

    @Test
    public void testNotConnectedNodes() {
        Assertions.assertNull(Node.search(node0, node1));
    }

    @Test
    public void testCycledNodes() {
        node0.connect(node1);
        node1.connect(node0);
        Assertions.assertEquals(1, Node.search(node0, node1));
    }

    @Test
    public void severalPaths() {
        node0.connect(node1);
        node0.connect(node3);
        node1.connect(node2);
        node2.connect(node3);
        Assertions.assertEquals(1, Node.search(node0, node3), "Should find the shortest path");
    }

    @Test
    public void testLongerPath() {
        node0.connect(node1);
        node1.connect(node2);
        node2.connect(node3);
        Assertions.assertEquals(3, Node.search(node0, node3), "Should find the correct path length for longer paths");
    }

    @Test
    public void testLargeGraph() {
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);

        node0.connect(node1);
        node1.connect(node2);
        node2.connect(node3);
        node3.connect(node4);
        node4.connect(node5);
        node5.connect(node6);
        node6.connect(node7);
        node7.connect(node8);
        node8.connect(node9);

        node0.connect(node6);
        node6.connect(node8);

        Assertions.assertNotNull(Node.search(node0, node9), "Should work correctly on a large graph");
        Assertions.assertEquals(3, Node.search(node0, node9), "Should find the shortest path in a large graph with multiple paths");
    }
}
