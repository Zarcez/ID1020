package com.company;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Labb1 {
    static Scanner scanner = new Scanner(System.in);
    static Stack<Character> stack = new Stack<Character>();

    private static void recursive() throws IOException {
        char c = (char) System.in.read();
        if(c !=('\n')){
            stack.push(c);
            System.out.println(stack);
            recursive();
        }
        recursivePush();

    }
    private static void recursivePush(){
        if(!stack.isEmpty()){
            stack.pop();
            System.out.println(stack);
            recursivePush();
        }
    }

    private static void iteration(){
        System.out.println("Enter a string");
        String testString = scanner.nextLine();
        char [] testChar = testString.toCharArray();

        for(char ch: testChar)
            stack.push(ch);

        while(!stack.isEmpty())
            System.out.println(stack.pop());

    }

    private static void singleLinkedCircular(){
        QueueSingular<Character> queueFirst = new QueueSingular<Character>();
        QueueSingular<Character> queueLast = new QueueSingular<Character>();

        System.out.println("Enter a string");
        String testString = scanner.nextLine();
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

    private static void doubleLinkedCircular(){
        Queue queue = new Queue<>();

        System.out.println("Enter a string");
        String testString = scanner.nextLine();
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

    private static void queueK(){
        QueueK queue = new QueueK<>();

        System.out.println("Enter a string");
        String testString = scanner.nextLine();
        char [] testChar = testString.toCharArray();

        for(char ch: testChar){
            queue.enqueue(ch);
            System.out.println(queue);
        }
        System.out.println("Enter the integer of the element to remove");
        try {
            int testInt = scanner.nextInt();
            queue.dequeueK(testInt);
            System.out.println(queue);
        }
        catch (NumberFormatException e) {
            System.out.println("Not an integer");
        }
    }

    private static void queueSorted(){
        QueueSorted queueSorted = new QueueSorted();
        System.out.println("Enter integers to be sorted, non integer to stop and sort");
        while (true){
            try{
                int testInt = scanner.nextInt();
                queueSorted.enqueueSorted(testInt);
                System.out.println(queueSorted);
            }
            catch (InputMismatchException e){
                System.out.println("Printing out");
                break;
            }

        }
        System.out.println(queueSorted);
    }

    public static void main(String[] args) throws IOException{

        System.out.println("Enter integer of assignment");
        String choice = scanner.nextLine();
        switch (choice) {
            case "2":
                System.out.println("Assignment 2");
                System.out.println("Enter a string");
                recursive();
                iteration();
                break;

            case "3":
                System.out.println("Assignment 3");
                doubleLinkedCircular();
                break;

            case "4":
                System.out.println("Assignment 4");
                singleLinkedCircular();
                break;

            case "5":
                System.out.println("Assignment 5");
                queueK();
                break;

            case "6":
                System.out.println("Assignment 6");
                queueSorted();
                break;

            default:
                System.out.println("Not a choice");
                break;
        }
    }

}
