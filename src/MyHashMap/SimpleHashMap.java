package MyHashMap;

import MyLinkedLIst.Linked;

import javax.xml.soap.Node;
import java.util.Iterator;
import java.util.*;
import java.io.*;

import static java.util.Objects.hash;


public class SimpleHashMap<K, V> implements MyHashMap<K, V> {
    private Node<K, V>[] hashTable;
    private int size = 0;
    private float threshold;

    public SimpleHashMap() {
        hashTable = new Node[16];
        threshold = hashTable.length * 0.75f;
    }


    @Override
    public boolean put(final K key, final V value) {
        if (size + 1 >= threshold) {
            threshold *= 2;
            arrayDoubling();
        }

        Node<K, V> newNode = new Node<>(key, value);
        int index = newNode.hash();

        if (hashTable[index] == null) {
            return simpleAdd(index, newNode);
        }

        List<Node<K, V>> nodeList = hashTable[index].getNodes();

        for (Node<K, V> node : nodeList) {
            if (keyExistButValueNew(node, newNode, value) || collisionProcessing(node, newNode, nodeList)) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    public boolean remove(K key) {
//        return false;
//    }

    private boolean simpleAdd(int index, Node<K, V> newNode) {
        hashTable[index] = new Node<>(null, null);
        hashTable[index].getNodes().add(newNode);
        size++;
        return true;
    }

    private boolean keyExistButValueNew(final Node<K, V> nodeFromList,
                                        final Node<K, V> newNode,
                                        final V value) {
        if (newNode.getKey().equals(nodeFromList.getKey()) && !newNode.getValue().equals(nodeFromList.getValue())) {
            nodeFromList.setValue(value);
            return true;
        }
        return false;
    }

    private boolean collisionProcessing(final Node<K, V> nodeFromList,
                                        final Node<K, V> newNode,
                                        final List<Node<K, V>> nodes) {
        if (newNode.hashCode() == nodeFromList.hashCode() &&
                !Objects.equals(newNode.key, nodeFromList.key) &&
                !Objects.equals(newNode.value, nodeFromList.value)) {
            nodes.add(newNode);
            size++;
            return true;
        }
        return false;
    }

    private void arrayDoubling() {
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = new Node[oldHashTable.length * 2];
        size = 0;
        for (Node<K, V> node : oldHashTable) {
            if (node != null) {
                for (Node<K, V> n : node.getNodes()) {
                    put(n.key, n.value);
                }
            }
        }
    }

    @Override
    public boolean delete(K key) {
        int index = hash(key);//todo
        if (hashTable[index] == null) {
            return false;
        }

        if (hashTable[index].getNodes().size() == 1) {
            hashTable[index].getNodes().remove(0);
        }

        List<Node<K, V>> nodeList = hashTable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            if (key.equals(node.getKey())) {
                nodeList.remove(node);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        if (index < hashTable.length &&
                hashTable[index] != null) {
            List<Node<K, V>> list = hashTable[index].getNodes();
            for (Node<K, V> node : list) {
                if (key.equals(node.getKey())) {
                    return node.getValue();
                }
            }
        }
        return null;
    }

//    @Override
//    public Iterator<V> iterator() {
//        return null;
//    }

    private class Node<K, V> {
        private List<Node<K, V>> nodes;
        private int hash;
        private K key;
        private V value;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private List<Node<K, V>> getNodes() {
            return nodes;
        }

        private int hash() {
            return hashCode() % hashTable.length;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            hash = hash * 17 + value.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Node) {
                Node<K, V> node = (Node) obj;
                return (Objects.equals(key, node.getKey()) &&
                        Objects.equals(value, node.getValue()) ||
                        Objects.equals(hash, node.hashCode()));
            }
            return false;
        }
    }

}
