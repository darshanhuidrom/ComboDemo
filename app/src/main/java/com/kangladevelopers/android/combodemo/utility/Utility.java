package com.kangladevelopers.android.combodemo.utility;

import com.kangladevelopers.android.combodemo.pojo.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by DARSHAN HUIDROJM on 7/21/2016.
 */
public class Utility {


    static public class CustomComparator implements Comparator<User> {// may be it would be Model

        @Override
        public int compare(User obj1, User obj2) {

            return obj1.getFirstName().compareTo(obj2.getFirstName());
        }
    }


    public List<User> convertArrayToUsers(String data,String splitter){

        String[] array= data.split(splitter);
        String temp;
        ArrayList<User> users = new ArrayList<>();
        for (int i=0;i<array.length;i++){
            temp = array[i];
            int pos= temp.indexOf(" ");
            User user = new User(temp.substring(0,pos),temp.substring(pos+1));
            users.add(user);
        }
        return users;
    }
}
