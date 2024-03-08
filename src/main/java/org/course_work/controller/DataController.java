package org.course_work.controller;

import org.course_work.DAO.DataDAOImpl;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;

import java.io.IOException;

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
   public void registrationData(String ticket, String cipher){
        dataDAO.add(ticket,cipher);
   }
   public void removeDataCipher(String cipher){
        dataDAO.removeCipher(cipher);
    }
   public void removeDataTicket(String ticket){
        dataDAO.removeTicket(ticket);
   }

   public void closeWR(){
        dataDAO.close();
   }

   public void loadData() throws IOException {
        dataDAO.loadData();
   }
}
