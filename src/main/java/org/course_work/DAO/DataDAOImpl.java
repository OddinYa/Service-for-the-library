package org.course_work.DAO;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.service.DataIABFile;
import org.course_work.struct.linkedList.List;

public class DataDAOImpl implements DataDAO {

    private List list;
    private DataIABFile data;
    public DataDAOImpl(){
        list = data.readerFile();


    }
    @Override
    public void registerBookIssue(String libraryCardNumber, String cipher) {
        DataOnTheIssuanceAndAcceptanceOfBooks data = new DataOnTheIssuanceAndAcceptanceOfBooks(libraryCardNumber,cipher);
    }

    @Override
    public void registerBookReturn(String libraryCardNumber, String cipher) {
        DataOnTheIssuanceAndAcceptanceOfBooks data;

        if((data = list.find(libraryCardNumber,cipher))!=null){
            data.setReturnDate();
        }else{
            System.out.println("Ошибка, пользовалея с такой книгой не найдено!");
        }
    }

    @Override
    public void removeCipher(String cipher) {

    }

    @Override
    public void removeTicket(String ticket) {

    }

    public DataOnTheIssuanceAndAcceptanceOfBooks[] getDataArr(String tNumb){
        List listResult = list.getDataWithCardNumber(tNumb);
        DataOnTheIssuanceAndAcceptanceOfBooks[] result = listResult.listToArray();

        return result;
    }

    public DataOnTheIssuanceAndAcceptanceOfBooks getTicketAndCipher(String ticket , String cipher){
      return  list.find(ticket,cipher);
    }

    public void close(){
        data.closeFile();
    }
}
