package SymbolTables;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchST<Key, Value> {

    private int size;
    private Node<Key, Value> first;

   
    private static class Node<Key, Value> {

        private final Key key;
        private Value value;
        private Node<Key, Value> next;

        public Node(final Key key, final Value value,
                final Node<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    /**
     * Initializes an empty symbol table.
     */
    public SequentialSearchST() {
        size = 0;
        first = null;

        checkInvariant();
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        checkInvariant();

        return size;
    }

    /**
     * Is this symbol table empty?
     * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        checkInvariant();

        return size() == 0;
    }


    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return <tt>true</tt> if this symbol table contains <tt>key</tt> and
     *     <tt>false</tt> otherwise
     */
    public boolean contains(final Key key) {
        checkInvariant();

        return valueFor(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and <tt>null</tt> if the key is not in the symbol table
     */
    public Value valueFor(final Key key) {
        checkInvariant();

        for (Node<Key, Value> node = first; node != null; node = node.next)
            if (key.equals(node.key))
                return node.value;

        return null;
    }

    /**
     * Returns all keys in the symbol table as an <tt>Iterable</tt>.
     * To iterate over all of the keys in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.keys())</tt>.
     * @return all keys in the sybol table as an <tt>Iterable</tt>
     */
    public Iterable<Key> keys() {
        checkInvariant();

        final Queue<Key> queue = new Queue<Key>();

        for (Node<Key, Value> node = first; node != null; node = node.next)
            queue.enqueue(node.key);

        return queue;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is <tt>null</tt>, this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     */
    public void put(final Key key, final Value value) {
        checkInvariant();

        if (value == null) {
            delete(key);
            return;
        }

        for (Node<Key, Value> node = first; node != null; node = node.next)
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }

        first = new Node<Key, Value>(key, value, first);

        size++;

        checkInvariant();
    }

    // Iterative version (see commented out recursive version below):
    public void delete(final Key key) {
        checkInvariant();

        if (isEmpty())
            return;

        if (key.equals(first.key)) {
            size--;
            first = first.next;
        } else {
            Node<Key, Value> node = first;
            while (node.next != null && !key.equals(node.next.key))
                node = node.next;
            if (node.next != null) {
                size--;
                node.next = node.next.next;
            }
        }

        checkInvariant();
    }

    // public void delete(final Key key) {
    // checkInvariant();
    //
    // first = deleteFrom(first, key);
    //
    // checkInvariant();
    // }
    //
    // private Node<Key, Value> deleteFrom(final Node<Key, Value> node, final
    // Key key) {
    // if (node == null)
    // return null;
    //
    // if (key.equals(node.key)) {
    // size--;
    // return node.next;
    // }
    //
    // node.next = deleteFrom(node.next, key);
    //
    // return node;
    // }
    //
    private void checkInvariant() {
        assert isSizeConsistent() : "Table array capacities not consistent with size.";
        assert keysAreNonNull() : "Table contains null keys.";
        assert valuesAreNonNull() : "Table contains null values.";
    }

    private boolean isSizeConsistent() {
        int numberOfNodes = 0;
        for (Node<Key, Value> node = first; node != null; node = node.next)
            numberOfNodes++;

        return numberOfNodes == size;
    }

    private boolean keysAreNonNull() {
        for (Node<Key, Value> node = first; node != null; node = node.next)
            if (node.key == null)
                return false;
        return true;
    }

    private boolean valuesAreNonNull() {
        for (Node<Key, Value> node = first; node != null; node = node.next)
            if (node.value == null)
                return false;
        return true;
    }

}

/*
 * Copyright 2015 and 2016, Robert Sedgewick and Kevin Wayne.
 * 
 * Copyright 2015 and 2016, Manuel Menezes de Sequeira.
 * 
 * This file is a derivative work of the code which accompanies the textbook
 * 
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 * 
 * This code is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this code. If not, see http://www.gnu.org/licenses.
 * 
 * Any errors found in this code should be assumed to be the responsibility of
 * the author of the modifications to the original code (viz. Manuel Menezes de
 * Sequeira).
 */