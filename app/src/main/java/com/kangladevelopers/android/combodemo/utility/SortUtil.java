package com.kangladevelopers.android.combodemo.utility;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by DARSHAN HUIDROJM on 7/22/2016.
 */
public class SortUtil {


    static public class CustomComparator implements Comparator<Combo> {// may be it would be Model

        public static final int SORT_BY_TITLE = 1;
        public static final int SORT_BY_DATE = 2;
        public static final int SORT_BY_RATING = 3;
        private int currentSort = 0;

        public CustomComparator(int sortBy) {
            currentSort = sortBy;

        }

        @Override
        public int compare(Combo obj1, Combo obj2) {
            int status = 0;

            switch (currentSort) {
                case SORT_BY_TITLE:
                    status = obj1.getTitle().compareTo(obj2.getTitle());
                    break;
                case SORT_BY_DATE:
                    status = obj1.getDate().compareTo(obj2.getDate());
                    break;
                case SORT_BY_RATING:
                    status = String.valueOf(obj1.getRatings()).compareTo(String.valueOf(obj2.getRatings()));
                    break;
                default:
            }
            return status;

        }
    }

    static class Combo {
        private Date date;
        private String title;
        private int ratings;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getRatings() {
            return ratings;
        }

        public void setRatings(int ratings) {
            this.ratings = ratings;
        }
    }
}
