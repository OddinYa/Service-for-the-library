package org.course_work.struct.map;

import org.course_work.entity.User;
import org.jetbrains.annotations.NotNull;

public class MyMap {
    //TODO 9.1.3
    private int length = 16;

    private int capacity = 0;

    private Bucket[] table = new Bucket[length];

    public void put(String key, User user){
        if(capacity < length*0.8){
            table[hash(key)]= new Bucket(key,user);
        }else {
            capacity = 0;
            length = length * 3;
            Bucket[] newTable = new Bucket[length];
            reHash(newTable,table);
            table = newTable;
        }
    }
    public User get(String key){
        int indx = hash(key);
        return table[indx].getUser();
    }

    public void remove(String key){

    }

    public User findByName(String fullName){
        return null;
    }

    private int hash(String key){
        int result = 17;
        int prime = 3;
        char first = key.charAt(0);
        int second = Integer.parseInt(key.substring(1,5));
        int third = Integer.parseInt(key.substring(6));

        result = result * prime + first;
        result = result * prime + third;
        result = result * prime + second;

        return result%length;
    }

    private void reHash(Bucket[] newTable, Bucket [] oldTable){
        for (Bucket b: oldTable) {
            newTable[hash(b.getKey())]= b;
        }
    }

}
