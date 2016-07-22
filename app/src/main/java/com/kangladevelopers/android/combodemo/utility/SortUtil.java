package com.kangladevelopers.android.combodemo.utility;

import com.kangladevelopers.android.combodemo.pojo.User;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by DARSHAN HUIDROJM on 7/22/2016.
 */
public class SortUtil {


    static public class CustomComparator implements Comparator<User> {// may be it would be Model

        @Override
        public int compare(User obj1, User obj2) {

            return obj1.getFirstName().compareTo(obj2.getFirstName());
        }
    }

    static class Combo {
        private Date date;

    }
}
