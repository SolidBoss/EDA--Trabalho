package LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedStack<Item> implements Iterable<Item> {
    private int N;                // tamanho da pilha
    private Node<Item> first;     // primeira posicao da pilha

    //criar a classe node
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //inicializa uma pilha vazia
    public LinkedStack() {
        first = null;
        N = 0;
    }

    //verifica se a pilha esta vazia. Se estiver mostra true, caso contrario false
    public boolean isEmpty() {
        return first == null;
    }

    //Devolve o nº de items na pilha
    public int size() {
        return N;
    }
    
    //Adiciona item a pilha
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    //Remove o item da pilha
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;       
        first = first.next;          
        N--;
        return item;                  
    }

    //Mostra o ultimo item inserido
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    // Retorna a representação da pilha na ordem LIFO
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
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
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
