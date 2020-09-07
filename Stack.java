package com.company;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creates a stack class supporting generic items by using a resizing array.
 * Uses push and pop to put and remove from the stack using a FILO
 * @param <Item>
 */

public class Stack<Item> implements Iterable<Item> {

    private Item[] a;
    private int n;

    public Stack(){
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty(){
        return n == 0;
    }

    /**
     * Resize the stack to a new size so to be able to handle a variables
     * @param capacity New the size
     */
    private void resize(int capacity){
        assert (n <= capacity);
        Item [] b = (Item[]) new Object[capacity];
        for( int i = 0; i < n; i++){
            b[i] = a[i];
        }
        a = b;
    }

    /**
     * Add a new item to the stack at the last position, if the stack is full, double the size of the stack
     * @param item The item to add to the stack
     */
    public void push(Item item){
        if( n == a.length)
            resize(a.length*2);
        a[n++] = item;
    }

    /**
     * Remove the last added item from the stack. If the stack is half empty, half the size of stack
     * @return The item that was removed
     */
    public Item pop(){
        if(isEmpty())
            throw new NoSuchElementException();
        Item item = a[--n];
        a[n] = null;
        if(n > 4 && n == a.length/2)
            resize(a.length/2);
        return item;
    }

    public int size(){
        return n;
    }

    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item>{

        private int i;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if(!hasNext())throw new NoSuchElementException();
            return a[i--];
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        if(isEmpty())
            stringBuilder.append("[],");
        for(int i = 0; i < n; i++){
            stringBuilder.append('['+a[i].toString()+']'+',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }


    /**
     * Make some simple test, to see that the simpler requests are possible
     */
    public static void main(String[] args) {

        Stack<String> stack = new Stack<String>();

        String testString = "test string";
        char [] testChar = testString.toCharArray();

        for(char ch: testChar) {
            stack.push(Character.toString(ch));
            System.out.println(stack);
        }
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
            System.out.println(stack);
        }

        System.out.println("Is empty" + stack.isEmpty());
        System.out.println("Size of stack" + stack.size());
    }
}
