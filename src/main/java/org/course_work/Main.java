package org.course_work;


import org.course_work.DAO.DataDAO;
import org.course_work.DAO.DataDAOImpl;
import org.course_work.entity.Book;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;
import org.course_work.exception.BookTopicNumberException;
import org.course_work.service.UserFile;
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
        User user = null;
        try {
            user = new User('Ч',"Genadiy",1992,"ew","-");
        } catch (AccessRightsException e) {

        }

        MyMap map = new MyMap();
        map.put(user.getNumberOfTheTicket(),user);
        System.out.println(map.get(user.getNumberOfTheTicket()).toString());
    }}