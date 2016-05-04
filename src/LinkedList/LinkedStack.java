package LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedStack<Item> implements Iterable<Item> {
    private int size;                // tamanho da pilha
    private Node<Item> first;     // primeira posicao da pilha

    //criar a classe node
    private static class Node<Item> { //
        private Item item;//
        private Node<Item> next;
    }

    //inicializa uma pilha vazia
    public LinkedStack() {
        first = null;
        size = 0;
    }

    //verifica se a pilha esta vazia. Se estiver mostra true, caso contrario false
    public boolean isEmpty() {
        return first == null;
    }

    //Devolve o nº de items na pilha
    public int size() {
        return size;
    }
    
    //Adiciona item a pilha
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        size++;
    }

    //Remove o item da pilha
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;       
        first = first.next;          
        size--;
        return item;                  
    }

    //Mostra o ultimo item inserido
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    // Retorna a representação da pilha na ordem LIFO
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Item item : this)
            string.append(item + " ");
        return string.toString();
    }
       
    // Retorna um iterador para esta pilha que percorre pela ordem LIFO
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    @SuppressWarnings("hiding")
	private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext(){ 
        	return current != null;
        }
        
        public void remove(){
        	throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
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
