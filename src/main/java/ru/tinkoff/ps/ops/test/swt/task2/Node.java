package ru.tinkoff.ps.ops.test.swt.task2;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Node {
    private int value;
    private Set<Node> neighbors;

    public Node(int value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    public void connect(Node node) {
        if (this == node) throw new IllegalArgumentException("Can't connect node to itself");
        this.neighbors.add(node);
        node.neighbors.add(this);
    }

    public static Integer search(Node start, Node end) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);


        Node currentNode;

        var distance = new HashMap<Node, Integer>();
        distance.put(start, 0);

        Set<Node> alreadyVisited = new HashSet<>();


        while (!queue.isEmpty()) {
            currentNode = queue.remove();

            if (currentNode.getValue() == end.getValue()) {
                return distance.get(currentNode);
            } else {
                alreadyVisited.add(currentNode);
                queue.addAll(currentNode.getNeighbors());

                for (var neighbor :currentNode.getNeighbors()) {
                    if (distance.get(neighbor) == null || distance.get(neighbor) > distance.get(currentNode) + 1) {
                        distance.put(neighbor, distance.get(currentNode) + 1);
                    }
                }
                queue.removeAll(alreadyVisited);
            }
        }

        return null;
    }
}


