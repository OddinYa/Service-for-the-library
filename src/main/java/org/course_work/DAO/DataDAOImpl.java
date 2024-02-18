package org.course_work.DAO;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.struct.linkedList.List;

public class DataDAOImpl implements DataDAO {

    private List list;
    public DataDAOImpl(){
        list = new List();


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
}
