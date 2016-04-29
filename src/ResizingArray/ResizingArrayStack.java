package ResizingArray;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ResizingArrayStack<Item> implements Iterable<Item>{

	private int size = 0;
	@SuppressWarnings("unchecked")
	private Item[] items = (Item[])new Object[1];
	private int numberOfIncreases = 0;
	private int numberOfDecreases = 0;

	 
	//Devolve o nº de items na pilha
	public int size(){
		return size;
	}

	//verifica se a pilha esta vazia. Se estiver mostra true, caso contrario false
	public boolean isEmpty(){
		return size == 0;
	}

	//Adiciona items e aumenta tamanho da pilha
	public void push(final Item newItem) {
		if (size == items.length){ //neste caso temos que criar uma nova pilha 
			resizeTo(2 * items.length); //com o dobro do tamanho da existente 
			numberOfIncreases++;//contador para os incrementos que acontece na pilha
		}
		items[size] = newItem;
		size++; 
	}

	//Remove espaço não utilizado da pilha
	public Item pop(){
		if(isEmpty())
			throw new NoSuchElementException("Stack underflow");
		size--;
		final Item item = items[size];
		items[size] = null;

		//Remove espaço não utilizado da pilha 
		if(size !=0 && size == items.length / 4){ // caso o tamanho seja 1/4 da capacidade da pilha 
			resizeTo(items.length /2);//diminuimos o tamanho da pilha 
			numberOfDecreases++;//contador quando é decrementado que acontece na pilha
		}
		return item;
	}

	// Retorna um iterador para esta pilha que percorre pela ordem LIFO
	@Override
	public Iterator<Item> iterator(){
		return new ArrayIterator();
	}

	//verifica se o item um proximo elemento
	private class ArrayIterator implements Iterator<Item> {
		int current = size;

		//devolve true caso o elemento analisado tenha next e não seja null
		@Override
		public boolean hasNext() {
			return current != 0;
		}

		@Override
		public Item next(){
			if(!hasNext())
				throw new NoSuchElementException("Sem Items no Iterador");
			current--;
			return items[current];
		}

		@Override
		public void remove(){
			throw new UnsupportedOperationException("Cannot remove Stack items using an iterator.");
		}
	}

	// Redimensionamento da pilha
	private void resizeTo(final int newCapacity) {
		@SuppressWarnings("unchecked")
		final Item[] newItems = (Item[]) new Object[newCapacity];

		for(int i = 0; i != size; i++)
			newItems[i] = items[i];

		items = newItems;
	}

	//devolve o numero de aumentos que foram feitos no array
	public int getNumberOfIncreases() {
		return numberOfIncreases;
	}

	//devolve o numero de diminuições que foram feitas no array
	public int getNumberOfDecreases() {
		return numberOfDecreases;
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
