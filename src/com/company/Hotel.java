package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Hotel
{
    final String ADD = "a";
    final String DELETE = "d";
    static Scanner in = new Scanner(System.in);
    final int ROOM_NUMBER = 8;
    private final Room[] rooms = new Room[ROOM_NUMBER];
    private Queue queue = new Queue();
    public Hotel()
    {
        for(int i = 0; i < this.rooms.length; i++)
        {
            this.rooms[i] = new Room();
        }
    }

    private int userGetRoomNumber(String a)
    {
        int roomNum = 0;
        boolean loop = true;
        do
        {
            System.out.print("PLease enter a room number(1-8 or 9 to cancel): ");
            try {
                roomNum = Integer.parseInt(in.next());
                if (roomNum > 9 || roomNum < 1)
                {
                    System.out.println("Please try again, enter a number between 1 and 8!\n");
                }
                else if(roomNum == 9)
                {
                    loop = false;
                }
                else if(a.equals("a"))
                {

                    if (!rooms[roomNum-1].getFirstName().equals(""))
                    {
                        System.out.println("This room is already occupied, please try another room!");
                    }
                    else
                    {
                        loop = false;
                    }
                }
                else if(a.equals("d"))
                {
                    if (rooms[roomNum-1].getFirstName().equals("e"))
                    {
                        System.out.println("This room is already empty!");
                    }
                    else
                    {
                        loop = false;
                    }
                }
            } catch (NumberFormatException e)
            {
                System.out.println("Please enter a number between 1 an 8!");
            }
        }
        while (loop);

        return roomNum -1;
    }

    public void displayE()
    {
        for(int i = 0; i < ROOM_NUMBER; i++)
        {
            if(rooms[i].getFirstName().equals(""))
            {
                System.out.printf("Room %d is empty!\n", i+1);
            }
        }
        System.out.println();
    }
    public void displayAll()
    {
        for(int i = 0; i < ROOM_NUMBER; i++)
        {
            String firstName = rooms[i].getFirstName();
            String lastName = rooms[i].getLastName();
            int numberOfGuests = rooms[i].getNumberOfGuests();
            if(rooms[i].getFirstName().equals(""))
            {
                System.out.printf("Room %d is empty!\n", i+1);
            }
            else
            {
                System.out.printf("Room %d is occupied by %s %s and has %d guests!\n", i+1, firstName, lastName, numberOfGuests);
            }
        }
    }
    public void addCustomer()
    {
        if (!isFull()) {
            int roomNumber = userGetRoomNumber(ADD);
            if (roomNumber != ROOM_NUMBER) {
                rooms[roomNumber].add();
            }
        }
        else if(!queue.isFull()) {
                queue.addToQueue();
            }
        else {
            System.out.println("The queue and hotel is full!");
        }
        System.out.println();
    }
    public void deleteCustomer() throws CloneNotSupportedException {
        int roomNumber = userGetRoomNumber(DELETE);
        rooms[roomNumber].delete();
        if (!queue.isEmpty()) {
            rooms[roomNumber] = queue.takeFromQueue();
        }
    }
    public void findCustomer()
    {
        String firstName = firstName();
        String lastName = lastName();
        boolean found = false;
        for (int i = 0; i < ROOM_NUMBER; i++){
            if(firstName.equals(rooms[i].getFirstName()) && lastName.equals(rooms[i].getLastName())) {
                System.out.printf("%s %s is in room %d.\n", firstName, lastName, i + 1);
                found = true;
                boolean loop = true;
                do {
                    System.out.print("Would you like to see the credit card details for the guest?");
                    String answer = in.next();
                    if (answer.equalsIgnoreCase("yes")) {
                        System.out.println("Card Number: " + rooms[i].getCardNumber());
                        loop = false;
                    }
                    if (answer.equalsIgnoreCase("no")) {
                        loop = false;
                    }
                } while (loop);
            }

        }
        if(!found){
            System.out.println("The guest wasn't found!");
        }
        System.out.println();
    }
    private String firstName()
    {
        String firstName;
        boolean loop = true;
        do
        {
            System.out.print("Please enter the first name of the person:");
            firstName  = in.next();
            if (!(firstName.length() <2) && !firstName.matches(".*\\d.*"))
            {
                loop = false ;
            }
        } while(loop);
        return firstName;
    }
    private String lastName(){
        String lastName;
        boolean loop = true;
        do
        {
            System.out.print("Please enter the last name of the person:");
            lastName  = in.next();
            if (!(lastName.length() <2) && !lastName.matches(".*\\d.*"))
            {
                loop = false ;
            }
        } while(loop);
        return lastName;
    }

    public void showAlphabetical(){
        String tempFirst;
        String tempSecond;
        String[][] temporary = new String[ROOM_NUMBER][2];
        for(int i = 0; i < this.rooms.length; i++)
        {
            temporary[i][0] =  this.rooms[i].getFirstName();
            temporary[i][1] = this.rooms[i].getLastName() ;
        }
        for(int i = 0; i < this.rooms.length  ; i++)
        {
            for(int z = 0; z < this.rooms.length ; z++)
            {
                if(temporary[i][0].compareTo(temporary[z][0]) < 0)
                {
                    tempFirst = temporary[i][0];
                    tempSecond = temporary[i][1];
                    temporary[i][0] = temporary[z][0];
                    temporary[i][1] = temporary[z][1];
                    temporary[z][0] = tempFirst;
                    temporary[z][1] = tempSecond;
                }
            }
        }
        for (String[] s : temporary)
        {

            if(!(s[0].equals("e")))
            {
                System.out.println(s[0] + " " +s[1]);
            }
        }
    }

    private String userGetFileLocation()
    {
        String filePath;
        boolean loop = true;
        do
        {
            System.out.print("Please enter the file path to the file(.txt): ");
            filePath = in.next();
            if(filePath.contains(".txt"))
            {
                loop = false;
            }
        } while(loop);
        return filePath;
    }

    public void readFromFile() throws FileNotFoundException {
        File inputFile = new File(userGetFileLocation());
        Scanner in = new Scanner(inputFile);
        for (int i = 0; i < ROOM_NUMBER; i ++)
        {
            String[] rooms = in.nextLine().split(" ");
            String firstName = rooms[2];
            String lastName = rooms[4];
            long cardNumber = Long.parseLong(rooms[6]);
            int guestNum = Integer.parseInt(rooms[8]);
            if(firstName.equals("e"))
            {
                this.rooms[i].reset();
            }
            else
            {
                this.rooms[i].add(firstName, lastName, cardNumber, guestNum);
            }
        }
        while (in.hasNextLine())
        {
            String[] rooms = in.nextLine().split(" ");
            String firstName = rooms[2];
            String lastName = rooms[4];
            long cardNumber = Long.parseLong(rooms[6]);
            int guestNum = Integer.parseInt(rooms[8]);
            if(!firstName.equals("e"))
            {
                queue.add(firstName, lastName, cardNumber, guestNum);
            }
        }
        in.close();
        System.out.printf("%d , %d", queue.getFront(), queue.getRear());
    }

    public void writeToFile() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(userGetFileLocation());

        for (int i = 0; i < ROOM_NUMBER; i++)
        {
            String firstName = rooms[i].getFirstName();
            String lastName = rooms[i].getLastName();
            long cardNumber = rooms[i].getCardNumber();
            int guestNum = rooms[i].getNumberOfGuests();
            if (firstName.equals("e"))
            {
                out.println("Room"+ (i+1) + " FirstName: " + "e" + " LastName: " + "e" + " CardNumber: " + 0
                        + " NumberGuests: " + 0);
            }
            else
            {
                out.println("Room" + (i + 1) + " FirstName: " + firstName + " LastName: " + lastName + " CardNumber: " + cardNumber
                        + " NumberGuests: " + guestNum);
            }
        }

            Room[] rooms = queue.writeToFile();
        for (int i = 0; i < ROOM_NUMBER; i++) {
            Room room = rooms[i];
            String firstName = room.getFirstName();
            String lastName = room.getLastName();
            long cardNumber = room.getCardNumber();
            int guestNum = room.getNumberOfGuests();
            if(firstName.equals("e"))
            {
                out.println("QueueRoom"+ (i+1) + " FirstName: " + "e" + " LastName: " + "e" + " CardNumber: " + 0
                        + " NumberGuests: " + 0);
            }
            else
            {
                out.println("QueueRoom" + (i + 1) + " FirstName: " + firstName + " LastName: " + lastName + " CardNumber: " + cardNumber
                        + " NumberGuests: " + guestNum);
            }
        }
        out.close();
    }

    boolean isFull ()
    {
        int occupied = 0;
        for(int i = 0; i < ROOM_NUMBER; i++)
        {
            if(!rooms[i].getFirstName().equals(""))
            {
                occupied++;
            }
        }
        return occupied == ROOM_NUMBER;
    }
    public void viewQueue() {
        queue.viewQueue();
    }
}

/*
    Robert-Dumitru Oprea
    @TheGoodAres
 */