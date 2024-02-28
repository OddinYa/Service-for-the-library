package org.course_work.controller;

import org.course_work.DAO.DataDAOImpl;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.entity.User;

public class DataController {
    DataDAOImpl dataDAO;

    public DataController(){
        dataDAO = new DataDAOImpl();
    }

    public DataOnTheIssuanceAndAcceptanceOfBooks[] getList(String tNumb){
       return  dataDAO.getDataArr(tNumb);
    }

   public DataOnTheIssuanceAndAcceptanceOfBooks getData(String ticket,String cipher){
       return  dataDAO.getTicketAndCipher(ticket,cipher);
   }

   public void closeWR(){
        dataDAO.close();
   }
}
