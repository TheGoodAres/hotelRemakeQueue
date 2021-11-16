package com.company;

import java.util.*;

public class Room implements Cloneable
{
    static Scanner in = new Scanner(System.in);
    private int numberOfGuests = 0;
    Person guest = new Person();

    public void setNumberOfGuests()
    {
        boolean loop = true;
        int numGuests = 0;
        do
        {

            try{
                System.out.print("PLease enter the number of guests in the room(1-5): ");
                String Guests = in.next();
                numGuests = Integer.parseInt(Guests);
                if (numGuests <1 || numGuests >5)
                {
                    System.out.println("Please try again");
                }
                else
                {
                    loop = false;
                }

            }
            catch (NumberFormatException | InputMismatchException a){
                System.out.println("Please enter a number between 1 an 5!");
            }
        } while (loop);

        this.numberOfGuests = numGuests;
    }
    public void setNumberOfGuests(int i){
        this.numberOfGuests = i;
    }
    public void add(String firstName, String lastName, long cardNumber, int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setCardNumber(cardNumber);
    }
    public void setFirstName(String firstName) {
        guest.setFirstName(firstName);
    }
    public void setLastName(String lastName) {
        guest.setLastName(lastName);
    }
    public void setFirstName()
    {
        guest.setFirstName();
    }
    public void setLastName()
    {
        guest.setLastName();
    }
    public void setCardNumber()
    {
        guest.setCardNumber();
    }
    public void setCardNumber(long l) {

    }
    public String getFirstName()
    {
        return guest.getFirstName();
    }
    public String getLastName()
    {
        return guest.getLastName();
    }
    public long getCardNumber()
    {
        return guest.getCardNumber();
    }
    public int getNumberOfGuests () {
        return numberOfGuests;
    }
    public void delete()
    {
        guest.delete();
    }
    public void add() {
        setFirstName();
        setLastName();
        setCardNumber();
        setNumberOfGuests();
    }
    public void reset() {
        guest.reset();
        this.numberOfGuests = 0;
    }
    public Room clone() {
        Room temp = new Room();
        temp.setNumberOfGuests(this.numberOfGuests);
        temp.setCardNumber(this.getCardNumber());
        temp.setFirstName(this.getFirstName());
        temp.setLastName(this.getLastName());
        return temp;
    }
}
/*
    Robert-Dumitru Oprea
    @TheGoodAres
 */