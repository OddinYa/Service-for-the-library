package org.course_work.DAO;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.service.DataIABFile;
import org.course_work.struct.linkedList.List;

import java.io.IOException;

public class DataDAOImpl implements DataDAO {

    private final List list;
    private final DataIABFile data;
    public DataDAOImpl(){
        data = new DataIABFile();
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
        list.removeCipher(cipher);
    }

    @Override
    public void removeTicket(String ticket) {
        list.removeTicket(ticket);
    }

    public DataOnTheIssuanceAndAcceptanceOfBooks[] getDataArr(String tNumb){
        List listResult = list.getDataWithCardNumber(tNumb);
        DataOnTheIssuanceAndAcceptanceOfBooks[] result = listResult.listToArray();

        return result;
    }

    public DataOnTheIssuanceAndAcceptanceOfBooks getTicketAndCipher(String ticket , String cipher){
      return  list.find(ticket,cipher);
    }

    public void add(String ticket, String cipher){
        list.add(new DataOnTheIssuanceAndAcceptanceOfBooks(ticket,cipher));
    }
    public void loadData() throws IOException {
        DataOnTheIssuanceAndAcceptanceOfBooks[] arr = list.listToArray();
        data.load(arr);

    }


    public void close(){
        data.closeFile();
    }
}
