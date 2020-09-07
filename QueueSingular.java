package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Create a queue class using single linked circular list
 * Uses the enqueue and dequeue methods to and remove from the queue, but the order added to it depends on which method called
 * @param <Item>
 */

public class QueueSingular<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item>{
        private Item item;
        private Node<Item> next;
    }

    /**
     * Create a new queue with zero values
     */
    public QueueSingular(){
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
     * Add an item in last place in the queue and links the item to the first item and the previous last to the item
     * @param item The item to be added
     */
    public void enqueueLast(Item item){
        Node<Item> previousLast = last;
        last = new Node<Item>();
        last.item = item;
        if(isEmpty()) {
            first = last;
            last.next = last;
        }
        else {
            last.next = first;
            previousLast.next = last;

        }
        n++;
    }

    /**
     * Remove the last item in the queue and cycles through all nodes until it comes the next to last node and link that node to the first
     * @return The item removed
     */
    public Item dequeueLast(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        n--;
        if(isEmpty()){
            first = null;
            last = null;
        }
        else{
            Node<Item> newLast;
            newLast = first;
            while (newLast.next != last)
                newLast = newLast.next;
            newLast.next = first;
            last = newLast;
        }
        return item;
    }

    /**
     * Add item to the first place and links it
     * @param item Item to be added
     */
    public void enqueueFirst(Item item){
        Node<Item> previousFirst = first;
        first = new Node<Item>();
        first.item = item;
        if(isEmpty()) {
            last = first;
            first.next = first;
        }
        else {
            first.next = previousFirst;
            last.next = first;
        }
        n++;
    }

    /**
     * Removes the first item in the queue and links the other nodes
     * @return The item that was removed
     */
    public Item dequeueFirst(){
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
            stringBuilder.append('['+node.item.toString()+ "]");
            node = node.next;
            if(node != first)
                stringBuilder.append(",");
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
     * Some simple tests that test removal from both orders work
     * @param args
     */
    public static void main(String[] args) {
        QueueSingular<Character> queueFirst = new QueueSingular<Character>();

        QueueSingular<Character> queueLast = new QueueSingular<Character>();

        String testString = "Test string";
        char [] testChar = testString.toCharArray();

        for(char ch: testChar){
            queueFirst.enqueueFirst(ch);
            System.out.println(queueFirst);
        }

        while(!queueFirst.isEmpty()){
            queueFirst.dequeueFirst();
            System.out.println(queueFirst);
        }

        for(char ch: testChar){
            queueLast.enqueueLast(ch);
            System.out.println(queueLast);
        }

        while(!queueLast.isEmpty()){
            queueLast.dequeueLast();
            System.out.println(queueLast);
        }

    }
}
