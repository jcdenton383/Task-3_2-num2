package com.company;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {






        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });

        SimpleQueue<Integer> Q1 = new SimpleLinkedListQueue<Integer>();
        Q1.add(0);
        Q1.add(2);
        Q1.add(3);
        //for(Integer item: Q1) System.out.println(item);
        SimpleLinkedList<Integer> L1 = new SimpleLinkedList<Integer>();
        L1.addFirst(5);
        L1.addFirst(6);
        L1.addFirst(7);
        //for(Integer item: Q1) System.out.println(item);

        for (Iterator<Integer> it = ((SimpleLinkedListQueue<Integer>) Q1).iterator(); it.hasNext(); ) {
            Object o = it.next();
            System.out.println(o);
        }


    }
}
