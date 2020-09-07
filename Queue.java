package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Create a queue class using double linked circular list
 * Uses the enqueue and dequeue methods to and remove from the queue in a FIFO order
 * @param <Item>
 */

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    /**
     *Create a node with a previous and next node
     */
    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    /**
     * When first created put all values to zero
     */
    public Queue(){
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    /**
     * Add an item to last position and connect to the previous item and the first item
     * @param item The item to be added
     */
    public void enqueue(Item item){
        Node<Item> previousLast = last;
        last = new Node<Item>();
        last.item = item;
        if(isEmpty()) {
            first = last;
            last.next = last.previous = last;
        }
        else {
            last.next = first;
            first.previous = last;
            last.previous = previousLast;
            previousLast.next = last;

        }
        n++;
    }

    /**
     * Remove the first item from the queue and connect the last node to new the first node
     * @return The item that was removed
     */
    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        n--;
        if(isEmpty()){
            first = null;
            last = null;
        }
        else{
            Node<Item> newFirst;
            newFirst = first.next;
            newFirst.previous = last;

            first = newFirst;
            last.next = first;

        }
        return item;
    }

    @Override
    public String toString() {

        if(isEmpty())
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        Node<Item> node = first;
        do{
            stringBuilder.append("["+node.item.toString()+ "]");
            node = node.next;
            if(node != first)
                stringBuilder.append(',');
        } while(node != first);

            return stringBuilder.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator(first);
    }

    private class MyIterator implements Iterator<Item>{

        private Node<Item> i,j;

        public MyIterator(Node<Item> first){
            i = first;
        }

        public boolean hasNext() {
            return i != null;
        }

        public Item next() {
            if(!hasNext())throw new NoSuchElementException();
            Item item = i.item;
            i = i.next;
            return item;
        }
    }

    /**
     * Some simple test to see that program works for the requirements
     * */
    public static void main(String[] args) {
        Queue<Character> queue = new Queue<Character>();

        String testString = "test string";
        char [] testChar = testString.toCharArray();

        for(char ch: testChar){
            queue.enqueue(ch);
            System.out.println(queue);
        }
        while(!queue.isEmpty()){
            queue.dequeue();
            System.out.println(queue);
        }

    }
}
