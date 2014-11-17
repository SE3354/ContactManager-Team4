package com.example.zxa108020.contactmanager;

import android.os.Parcelable;

public class Contact
{
     private String firstName;
     private String lastName;
     private String phoneNumber;
     private String secondNumber;

     public Contact(String f, String l, String pN, String sN)
     {
         this.firstName = f;
         this.lastName = l;
         this.phoneNumber = pN;
         this.secondNumber = sN;
     }
     public void setFirstName(String f)
     {
         this.firstName = f;
     }
     public void setLastName(String l)
     {
         this.lastName = l;
     }
     public void setPhoneNumber(String pN)
     {
         this.phoneNumber = pN;
     }
     public void setSecondNumber(String sN)
     {
         this.secondNumber = sN;
     }
     public String getFirstName()
    {
        return this.firstName;
    }
     public String getLastName()
    {
        return this.lastName;
    }
     public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
     public String getSecondNumber()
    {
        return this.secondNumber;
    }
}
