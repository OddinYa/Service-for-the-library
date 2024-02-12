package org.course_work.struct.map;

import org.course_work.entity.User;

public class Bucket {

   private int key;
   private User user;

    public Bucket(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
