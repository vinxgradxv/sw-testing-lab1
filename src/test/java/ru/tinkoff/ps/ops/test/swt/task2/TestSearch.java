package ru.tinkoff.ps.ops.test.swt.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class TestSearch {

    @Test
    public void testOneNodeGraph() {
        var root = new Node(0);
        Assertions.assertEquals(0, Node.search(root, root));
    }

    @Test
    public void testNotConnectedNodes() {
        var start = new Node(0);
        var end = new Node(1);
        end.setNeighbors(Set.of(start));
        Assertions.assertNull(Node.search(start, end));
    }

    @Test
    public void testCycledNodes() {
        var start = new Node(0);
        var end = new Node(1);
        start.setNeighbors(Set.of(end));
        end.setNeighbors(Set.of(start));
        Assertions.assertEquals(1, Node.search(start, end));
    }

    @Test
    public void severalPaths() {
        var node0 = new Node(0);
        var node1 = new Node(1);
        var node2 = new Node(2);
        var node3 = new Node(3);
        node0.setNeighbors(Set.of(node1, node3));
        node1.setNeighbors(Set.of(node2));
        node2.setNeighbors(Set.of(node3));
        Assertions.assertEquals(1, Node.search(node0, node3));
    }
}
