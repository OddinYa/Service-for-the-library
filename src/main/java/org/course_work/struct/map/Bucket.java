package org.course_work.struct.map;

import org.course_work.entity.User;

class Bucket {

   private String key;
   private User user;

    protected Bucket(String key,User user) {
        this.key = key;
        this.user = user;
    }

    protected String getKey() {
        return key;
    }

    protected void setKey(String key) {
        this.key = key;
    }
    protected User getUser(){
        return user;
    }
}
