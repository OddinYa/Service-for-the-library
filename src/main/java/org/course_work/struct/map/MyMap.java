package org.course_work.struct.map;

import org.course_work.entity.User;
import org.course_work.service.BMSearch;
import org.course_work.struct.MyStruct;

import java.util.Objects;


public class MyMap extends MyStruct {
    //TODO 9.1.3
    private int step = 2;
    private int length = 16;

    private int capacity = 0;

    private final BMSearch search = new BMSearch();

    private Bucket[] table = new Bucket[length];

    public void put(String key, User user) {

        boolean flag = false;

        if (capacity < length * 0.8) {
            capacity++;
            int index = hash(key);
            if (table[index] == null) {
                table[index] = new Bucket(key, user);
            } else {
                if (user.getNumberOfTheTicket().equals(table[index].getKey())) {
                    table[index].setUser(user);
                } else {
                    index = index + step;
                    while (!flag) {
                        if (table[index] == null) {
                            flag = true;
                            table[index] = new Bucket(key, user);
                        } else {
                            step = (int) Math.pow(step, 2);
                            index = (index + step) % length;
                        }
                    }
                }

            }
        } else {
            length = length * 3;
            Bucket[] newTable = new Bucket[length];
            reHash(newTable, table);
            table = newTable;
        }
        step = 2;
    }

    public User get(String key) {
        User user = null;
        if (!Objects.equals(key, "")) {

            int index = hash(key);
            if (!table[index].getKey().equals(key)) {
                user = find(key, index);

            } else {
                user = table[index].getUser();
            }
        }
        return user;
    }

    public void remove(String key) {
        int index = hash(key);
        int initialIndex = index;

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                table[index] = null;
                capacity--;
                return;
            }
            index = (index + step) % length;
            step = (int) Math.pow(step, 2);

            if (index == initialIndex) {
                return;
            }
        }
    }

    public MyMap findByName(String fullName) {

        MyMap result = new MyMap();

        for (Bucket b : this.table) {
            if (b != null && b.getUser() != null) {
                if (search.job(b.getUser().getFullName(), fullName)) {
                    result.put(b.getUser().getNumberOfTheTicket(), b.getUser());
                }
            }
        }
        return result;
    }

    private int hash(String key) {
        int result = 17;
        int prime = 3;
        char first = key.charAt(0);
        int second = Integer.parseInt(key.substring(1, 5));
        int third = Integer.parseInt(key.substring(6));

        result = result * prime + first;
        result = result * prime + third;
        result = result * prime + second;

        return result % length;
    }

    private void reHash(Bucket[] newTable, Bucket[] oldTable) {
        for (Bucket b : oldTable) {
            if (b != null) {
                int index = hash(b.getKey());
                while (newTable[index] != null) {
                    index = (index + step) % length;
                    step = (int) Math.pow(step, 2);
                }
                newTable[index] = b;
            }
        }
    }


    public void printMap() {
        for (Bucket b : table) {
            if (b != null && b.getUser() != null) {
                System.out.println(b.toString());
            }
        }
    }

    public User[] toArray() {
        User[] result = new User[this.capacity];
        int count = 0;

        for (Bucket table : table) {
            if (table != null) {
                result[count] = table.getUser();
                count++;
            }
            if(count==capacity){
                break;
            }
        }
        return result;
    }

    private User find(String key, int index) {
        boolean flag = false;
        boolean flagLength = false;
        User user = null;
        while (!flag) {

            int nextIndex = index + step;

            if (nextIndex >= table.length) {
                nextIndex = nextIndex % table.length;
                if (flagLength == true) {
                    break;
                }
                flagLength = true;

            }

            if (table[nextIndex].getKey().equals(key)) {
                flag = true;
                user = table[nextIndex].getUser();
            } else {
                index = nextIndex;
                step = (int) Math.pow(step, 2);
            }
        }
        return user;
    }

}
