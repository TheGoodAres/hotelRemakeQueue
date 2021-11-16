package com.company;

import java.util.*;

public class Person
{
    static Scanner in = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private long cardNumber;

    Person()
    {
        this.firstName = "";
        this.lastName = "";
        this.cardNumber = 0L;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setCardNumber(long cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public void setFirstName()
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
        this.firstName = firstName;
    }

    public void setLastName()
    {
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

        this.lastName = lastName;
    }


    public void setCardNumber()
    {
        boolean loop = true;
        long cardNumber = 0L;
        do
        {

            try{
                System.out.print("PLease enter the card number: ");
                String cardN = in.next();
                if (cardN.length() != 16)
                {
                    System.out.println("Please try again");
                }
                else
                {
                    cardNumber = Long.parseLong(cardN);
                    if(cardNumber < 0)
                    {
                        System.out.println("Please try again");
                    }
                    else
                    {
                        loop = false;
                    }
                }

            }
            catch (NumberFormatException a){
                System.out.println("Please enter a valid number!");
            }
        } while (loop);
        this.cardNumber = cardNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public long getCardNumber()
    {
        return cardNumber;
    }

    public void delete(){
        firstName = "";
        lastName = "";
        cardNumber = 0L;
    }

    public void reset() {
        this.firstName = "e";
        this.lastName = "e";
        this.cardNumber = 0L;
    }
}
/*
    Robert-Dumitru Oprea
    @TheGoodAres
 */