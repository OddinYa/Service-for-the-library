package org.course_work;



import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;

import org.course_work.struct.linkedList.List;
import org.course_work.struct.map.MyMap;


public class Main {
    public static void main(String[] args) {
     // ConsoleDisplay consoleDisplay = new ConsoleDisplay();
        DataOnTheIssuanceAndAcceptanceOfBooks data = new DataOnTheIssuanceAndAcceptanceOfBooks(
                "Ч0003-23",
                "020.060");

        DataOnTheIssuanceAndAcceptanceOfBooks data1 = new DataOnTheIssuanceAndAcceptanceOfBooks("Ч0004-23","001.002");
        DataOnTheIssuanceAndAcceptanceOfBooks data2= new DataOnTheIssuanceAndAcceptanceOfBooks("Ч0005-23","010.003");

        List list = new List();
        list.add(data);
        list.add(data1);
        list.add(data2);

        list.sort();
        System.out.println(data1.toString());
        list.print();
        User user1 = null;
        User user2 = null;
        User user3 = null;

        try {
            user1 = new User('Ч',"Genadiy",1992,"ew","-");
            user2 = new User('Ч',"Genadiy",1992,"ew","-");
            user3 = new User('Ч',"Genadiy",1992,"ew","-");
        } catch (AccessRightsException e) {

        }

        MyMap map = new MyMap();
        map.put(user1.getNumberOfTheTicket(),user1);
        map.put(user2.getNumberOfTheTicket(),user2);
        map.put(user3.getNumberOfTheTicket(),user3);



        System.out.println(map.get(user1.getNumberOfTheTicket()).toString());
        System.out.println(map.get(user2.getNumberOfTheTicket()).toString());
        System.out.println(map.get(user3.getNumberOfTheTicket()).toString());
        System.out.println();
        MyMap map1 = map.findByName("Gen");
        map1.printMap();

    }}