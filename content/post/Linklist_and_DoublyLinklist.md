---
title: "Linklist and DoublyLinklist"
date: 2021-09-16
lastmod: 2021-09-17
draft: false
tags: ["Java", "Leetcode","Linear"]
categories: ["Leetcode"]
author: "Achilles"
---


**This demo is to help us identify the different between the Linklist and DoublyLinklist**


> what we need to notice

 Compare with the Linklist and DoublyLinklist,
 the Linklist compact with two elements, one is item, another is next point.
 the DoublyLinklist concact three elements, one is item as well, two others are preview ponit and next point.



# Linklist 
> Linklist

please notice some boundary conditions, like when we insert the elements the index need to be security check (index < 0 || index > N is not allowed)



``` Java

public class LinkList<T> implements Iterable<T>{

    public static void main(String[] args){
        System.out.println("LinkList!");

        LinkList<String> link = new LinkList<>();
        link.insert(0, "number01");
        link.insert(1, "number02");
        link.insert(2, "number03");
        link.insert(3, "number04");

        for (String string : link) {
            System.out.println(string);
        }

        System.out.println(link.length());

        System.out.println("=======================");
    }

    //this is the first Node
    private Node head;

    //this is the length of the LinkList
    private int N;

    //construct function
    public LinkList(){
        //initial the head Node
        head = new Node(null, null);
        N = 0;
    }

    //this is the InnerClass of Node
    private class Node<T>{
        public T item;
        public Node next;

        public Node(T item, Node next){
            this.item = item;
            this.next = next;
        }
    }
    // clear the LinkList
    public void clear(){
        head.next = null;
        head.item = null;
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int length(){
        return N;
    }

    public T get(int i){

        if (i < 0 || i >N) {
            throw new RuntimeException("position is illegal!");
        }
        Node n = head.next;

        for (int index = 0; index < i; index++) {
                n = n.next;
        }

        return (T) n.item;

    }

    public void insert(T t){
        //add the Node to the end of the list.
        Node n = head;
        while(n.next != null){
            n = n.next;
        }

        Node newNode = new Node(t, null);

        n.next = newNode;

        N++;



    }


    public void insert(int i, T t){

        if (i<0 || i> N) {
            throw new RuntimeException("the position is not correct!");
        }
        //find the front Node.
        Node pre = head;

        for(int index = 0; index <= i-1; index++) {
            pre = pre.next;
        }

        //find current i position
        Node cursor = pre.next;

        //contruct new Node then make the new pointer.
        Node newNode = new Node(t,cursor);
        //make the front Node point newNode
        pre.next = newNode;

        N++;

    }

    public T remove(int i){


        //check Boundary conditions
        if (i<0 || i>N) {
            throw new RuntimeException("this is wrong position");
        }
        //find the front position
        Node pre = head;
        for (int index = 0; index < i-1; index++) {
            pre = pre.next;
        }

        //find the current i position
        Node cursor = pre.next;

        //make front point toward next point, the current element will remove automactically
        pre.next = cursor.next;

        //Important Notice N--.
        N--;

        return (T) cursor.item;
    }

    public int indexOf(T t){

        Node n = head;

        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new LIterator();
    }

    private class LIterator implements Iterator<T>{

        private Node n;
        public LIterator(){
            n = head;
        }
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return n.next != null;
        }

        @Override
        public T next() {
            // TODO Auto-generated method stub
            n = n.next;
            return (T)n.item;
        }


    }

}
````
# DoublyLinklist
> DoublyLinklist

The function insert(t, i) is very similar with the function remove(t),
when we need to keep in mind is make the preview and next point figure out currect place.

``` Java

package linear;

import java.util.Iterator;

public class DoublyLinklist<T> implements Iterable<T>{

    public static void main(String[] args){

        System.out.println("DoublyLinklist!");
        DoublyLinklist<String> doublelist = new DoublyLinklist<>(); 
        doublelist.insert("Nash");
        doublelist.insert("Yao");
        doublelist.insert("James");

        doublelist.insert("Macgrady",1);

        //doublelist.insert("who",8);
        doublelist.insert("right One",3);
        

        for (String string : doublelist) {
            System.out.println(string);
        }

        System.out.println("=========================");
        System.out.println("How long list we have :"+ doublelist.length());
        String two = doublelist.get(2);
        System.out.println(two);
        System.out.println("=======================");
        String remove = doublelist.remove(3);
        System.out.println(remove);
        System.out.println("new length :"+ doublelist.length());
    }

    private class Node{

        public Node(T item, Node pre, Node next){
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
        //store the data
        public T item;
        //point toward preview Node
        public Node pre;
        //point toward next Node
        public Node next;
    }
    //first Node
    private Node head;
    //last Node
    private Node last;
    //the length of link
    private int N;

    //constuct initial the paramater
    public DoublyLinklist(){
        last = null;
        head = new Node(null,null,null);
        N=0;
    }

    //clear the link
    public void clear(){
        N = 0;
        last = null;
        head.item = null;
        head.pre = null;
        head.next = null;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    //get the length of the linklist
    public int length(){
        return N;
    }

    public T get(int i){

        if (i<0 || i>N) {
            throw new RuntimeException("the position is illegal");
        }

        Node current = head.next;
        for (int index = 0; index < i; index++) {
            current = current.next;
        }
        return current.item;
    }

    public void insert(T t){

        if (last == null) {
            last = new Node(t,head, null);
            head.next = last;
        }else{
            Node oldLast = last;
            Node newLast = new Node(t, oldLast, null);
            oldLast.next = newLast;
            // this is important make the newLast become the last
            last =  newLast;

        }
        N++;
    }

    public void insert(T t, int i){

        if (i<0 || i>N) {
            throw new RuntimeException("The position is not correct!");
        }

        //find the preview Node
        Node preview = head;
        for (int index = 0; index < i; index++) {
            preview = preview.next;
        }

        //current Node
        Node current = preview.next;

        Node newNode = new Node(t, preview, current);
        

        //this area must have good debugging test.
        preview.next = newNode;
        current.pre = newNode;
        N++;


    }

    //the function remove is similar with the function insert(t,i);
    public T remove(int i){

        if (i < 0 || i> N) {
            throw new RuntimeException("The position is not currect!");
        }

        // find the preview Node
        Node preview = head;
        for (int index = 0; index < i; index++) {
            preview = preview.next;
        }

        //find current (i) Node
        Node current = preview.next;

        // find the i position next
        Node current_next = current.next;

        preview.next = current_next;
        current_next.pre = preview;

        N--;
        return current.item;
    }

    public int indexOf(T t){

        Node current = head;

        for (int i = 0; current.next != null; i++) {
            current = current.next;
            if (current.next.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public T getFirst(){

        if(isEmpty()){
           return null;
        }
        return head.next.item;
    }

    public T getLast(){

        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new LIterator();
    }

    private class LIterator implements Iterator{    

        private Node node = head;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return node.next != null;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stub
            node = node.next;
            return node.item;
        }

    }
    
   
    
}
