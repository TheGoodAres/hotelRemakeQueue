package com.company;

public class Queue{
    final int SIZE = 8;
    int sizeQueue = 0;

    public int getFront () {
        return front;
    }

    public int getRear () {
        return rear;
    }

    private int front;
    private int rear;
    private Room[] rooms = new Room[SIZE];

    public Queue() {
        for (int i = 0; i < SIZE; i++) {
           rooms[i] = new Room();
           rooms[i].setFirstName("e");
           rooms[i].setLastName("e");
        }
        this.front = 0;
        this.rear = 0;
    }

    public void addToQueue() {
        if (sizeQueue != 8) {
            rooms[rear].add();
            rear = (rear + 1) % SIZE;
            sizeQueue++;
        }
        else {
            System.out.println("The queue is full!");
        }
    }
    public void viewQueue() {
        for(int i = 0; i < SIZE; i++) {
            System.out.println(rooms[i].getFirstName());
        }
    }
    public void add(String firstName, String lastName, long cardNumber, int guestNum) {
        System.out.println(rear);
        rooms[rear].add(firstName, lastName, cardNumber, guestNum);
        if(!"e".equals(firstName) && !"e".equals(lastName)){
            rear = (rear + 1) % SIZE;
        }
        System.out.printf("%d front, %d rear", front, rear);
        sizeQueue++;
    }
    public Room takeFromQueue() throws CloneNotSupportedException {
        if (sizeQueue != 0) {
            Room room =  rooms[front].clone();
            rooms[front].delete();
            front = (front + 1) % SIZE;
            sizeQueue--;
            return room;
        }
        else {
            System.out.println("The queue is empty!");
            return new Room();
        }
     }
    public boolean isEmpty()
    {
        return sizeQueue == 0;
    }

    public boolean isFull()
    {
        return (sizeQueue == 8);
    }

    public Room[] writeToFile() {
        Room[] ret = new Room[8];
        for (int z = 0; z < SIZE; z++) {
            ret[z] = rooms[sizeQueue];
            sizeQueue++;
        }
        return ret;
    }
}
/*
    Robert-Dumitru Oprea
    @TheGoodAres
 */


