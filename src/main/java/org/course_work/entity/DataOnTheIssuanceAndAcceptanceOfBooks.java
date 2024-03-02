package org.course_work.entity;

import org.course_work.controller.DataController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DataOnTheIssuanceAndAcceptanceOfBooks implements Entity {
   private String libraryCardNumber;
   private String cipher;
   private String dateOfIssue;
   private String returnDate;

   private boolean flagActive;

   public DataOnTheIssuanceAndAcceptanceOfBooks(String libraryCardNumber, String cipher) {

         this.flagActive = true;
         this.libraryCardNumber = libraryCardNumber;
         this.cipher = cipher;
         dateOfIssue = dateToString();

   }

   public static DataOnTheIssuanceAndAcceptanceOfBooks buildData(String libraryCardNumber,String cipher, String dateOfIssue,String returnDate ){

      DataOnTheIssuanceAndAcceptanceOfBooks data = new DataOnTheIssuanceAndAcceptanceOfBooks(libraryCardNumber,cipher);

      if(returnDate.equals("")){
         data.flagActive = true;
      }else {
         data.returnDate = returnDate;
      }
      data.dateOfIssue = dateOfIssue;

      return data;
   }

   public String getLibraryCardNumber() {
      return libraryCardNumber;
   }

   public void setLibraryCardNumber(String libraryCardNumber) {
      this.libraryCardNumber = libraryCardNumber;
   }

   public String getCipher() {
      return cipher;
   }

   public void setCipher(String cipher) {
      this.cipher = cipher;
   }

   public String getDateOfIssue() {
      return dateOfIssue;
   }
   public String getReturnDate() {
      return returnDate;
   }

   public void setReturnDate() {
      this.returnDate = dateToString();
      this.flagActive = false;
   }

   public String dateToString(){
      Calendar calendar = Calendar.getInstance();
      Date date = calendar.getTime();
      return formatDateToString(date);
   }

   public String formatDateToString(Date date){
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      return formatter.format(date);
   }

   @Override
   public String toString() {
      return "DataOnTheIssuanceAndAcceptanceOfBooks{" +
              "libraryCardNumber='" + libraryCardNumber + '\'' +
              ", cipher='" + cipher + '\'' +
              ", dateOfIssue='" + dateOfIssue + '\'' +
              ", returnDate='" + returnDate + '\'' +
              '}';
   }


   public int compare(DataOnTheIssuanceAndAcceptanceOfBooks o1) {
      String[] parts1 = this.getCipher().split("\\.");
      String[] parts2 = o1.getCipher().split("\\.");


      int fractionalComparison = Integer.compare(Integer.parseInt(parts1[1]), Integer.parseInt(parts2[1]));
      if (fractionalComparison != 0) {
         return fractionalComparison;
      }

      return Integer.compare(Integer.parseInt(parts1[0]), Integer.parseInt(parts2[0]));
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      DataOnTheIssuanceAndAcceptanceOfBooks that = (DataOnTheIssuanceAndAcceptanceOfBooks) o;
      return libraryCardNumber.equals(that.libraryCardNumber) &&
              cipher.equals(that.cipher) &&
              Objects.equals(dateOfIssue, that.dateOfIssue) &&
              Objects.equals(returnDate, that.returnDate);
   }
   @Override
   public int hashCode() {
      return Objects.hash(libraryCardNumber, cipher, dateOfIssue, returnDate);
   }

   @Override
   public void getCart(int numb) {

   }

}
