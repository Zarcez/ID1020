package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Create a queue class using single linked list
 * Uses the enqueue and dequeue methods to add to first and remove from the k:th element in the que starting from 1
 * @param <Item>
 */

public class QueueK<Item> implements Iterable<Item> {
    protected Node<Item> first;
    protected Node<Item> last;
    protected int n;

    protected static class Node<Item>{
        protected Item item;
        protected Node<Item> next;
    }

    /**
     *Creates a new queue with zero values
     */
    public QueueK(){
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
     * Remove the k:th element from the queue
     * @param k The elements index to be removed
     * @return The item removed
     */
    public Item dequeueK(int k){
        if(isEmpty() || k > n) throw new NoSuchElementException();
        Item item;
        n--;
        if(n == 0){     //Special case where the queue only has one element
            item = first.item;
            first = null;
            last = null;
        }
        else if(k == 1){    //Special case where you remove the first element
            item = first.item;
            first = first.next;
        }
        else{       //The general case where you remove the k:th element by finding the element before and linking it to the element after, thus unable to handle position 1
            Node<Item> currentNode;
            currentNode = first;
            for( int i  = 1; i< k-1; i++)
                currentNode = currentNode.next;
            item = currentNode.next.item;
            currentNode.next = currentNode.next.next;
            if(currentNode.next == null)
                last = currentNode;
        }
        return item;
    }

    /**
     * Add item to the first position in the queue
     * @param item The item to be added
     */
    public void enqueue(Item item){
        Node<Item> previousFirst = first;
        first = new Node<Item>();
        first.item = item;
        if(isEmpty()) {
            last = first;
            first.next = null;
        }
        else {
            first.next = previousFirst;
        }
        n++;
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
            if(node != null)
                stringBuilder.append(',');
        } while(node != null);
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
     * Some simple test to see if the requirements are meet.
     * String should a word when printed
     * @param args
     * @throws NoSuchElementException If you try to remove an element that doesn't exist
     */
    public static void main(String[] args) throws NoSuchElementException{
        QueueK<Character> queue = new QueueK<Character>();

        String testString = "6tse3T1";
        char [] testChar = testString.toCharArray();

        for(char ch: testChar){
            queue.enqueue(ch);
            System.out.println(queue);
        }
        System.out.println(queue.dequeueK(3));
        System.out.println(queue);
        try {
            System.out.println(queue.dequeueK(7));
        }
        catch (NoSuchElementException e) {
            System.out.println(e);
        }
        System.out.println(queue);
        System.out.println(queue.dequeueK(6 ));
        System.out.println(queue);
        System.out.println(queue.dequeueK(1));
        System.out.println(queue);
    }
}
