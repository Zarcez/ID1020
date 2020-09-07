package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Create a queue class using single linked list
 * Uses the enqueue and dequeue methods to add to first and remove from the k:th element in the que starting from 1
 * @param <Item>
 */

public class QueueSorted<Item> extends QueueK {


    public QueueSorted(){
        super();
    }

    /**
     * Go through the queue and put the next integer at in an ascending order
     * @param item The item to be added
     */
    public void enqueueSorted(Item item){
        //if(item instanceof  Integer)
        //    throw new IllegalArgumentException("Please enter appropriate integer.");
        Node<Item> node = new Node<>();

        node.item = item;
        if(isEmpty()) {
            last = first = node;
            first.next = null;
        }
        else if ((Integer)first.item >=  (Integer) node.item) { //The first position is a special case and dealt with separately
            node.next = first;
            first = node;

        }
        else{

            for (Node x = first; x != null; x = x.next) {   //Go through the list until the next integer is higher then the new one, connect the new integer
                if((x.next == null) || ((Integer)x.next.item >=  (Integer) node.item)){
                    node.next = x.next;
                    x.next = node;
                    break;
                }
            }
        }
        n++;
    }




    /**
     * Some simple test to see if the requirements are meet.
     * String should be numbers in an ascending order
     * @param args
     * @throws NoSuchElementException If you try to remove an element that doesn't exist
     */
    public static void main(String[] args) throws IllegalArgumentException{
        QueueSorted<Integer> queue = new QueueSorted<>();

        int [] testInt =  {2,1,2,3,7,9,2,5,15,6,3};

        for(int in: testInt){
            queue.enqueueSorted(in);
            System.out.println(in);
            System.out.println(queue);
        }
        System.out.println(queue);
    }
}
