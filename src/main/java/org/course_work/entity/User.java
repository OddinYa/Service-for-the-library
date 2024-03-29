package org.course_work.entity;


import org.course_work.exception.AccessRightsException;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class User implements Comparable<User>, Entity {

    private String numberOfTheTicket;
    private char accessRights;
    private static int lastAssignedNumber = 0;
    private int numbOfRegistration;
    private String fullName;

    private int yearOfBirth;

    private String address;

    private String placeOfWorkOrStudy;

    public User(char accessRights, String fullName,
                int yearOfBirth, String address, String placeOfWorkOrStudy) throws AccessRightsException {

        if (checkAccessRights(accessRights)) {

            this.accessRights = accessRights;
            this.fullName = fullName;
            this.yearOfBirth = yearOfBirth;
            this.address = address;
            this.placeOfWorkOrStudy = placeOfWorkOrStudy;
            this.numberOfTheTicket = createNumber(accessRights);

        } else
            throw new AccessRightsException("Ошибка права доступа!");
    }

    public User(String numberOfTheTicket, char accessRights, int numbOfRegistration, String fullName,
                int yearOfBirth, String address, String placeOfWorkOrStudy) throws AccessRightsException {

        if (checkAccessRights(accessRights)) {
            this.numberOfTheTicket = numberOfTheTicket;
            this.accessRights = accessRights;
            this.numbOfRegistration = numbOfRegistration;
            this.fullName = fullName;
            this.yearOfBirth = yearOfBirth;
            this.address = address;
            this.placeOfWorkOrStudy = placeOfWorkOrStudy;

        } else {
            throw new AccessRightsException("Ошибка права доступа!");
        }
    }

    private boolean checkAccessRights(Character c) {
        return switch (c) {
            case 'А', 'Ч', 'В' -> true;
            default -> false;
        };
    }

    private String createNumber(char accessRights) {

        StringBuilder stringBuilder = new StringBuilder(accessRights);
        stringBuilder.append(accessRights);
        lastAssignedNumber++;
        this.numbOfRegistration = lastAssignedNumber;
        String string = String.format("%04d", numbOfRegistration);

        stringBuilder.append(string);

        stringBuilder.append("-");

        stringBuilder.append(toGetYear());

        return stringBuilder.toString();
    }

    private int toGetYear() {

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        calendar.setTime(date);

        return calendar.get(Calendar.YEAR) % 100;
    }


    public String getNumberOfTheTicket() {
        return numberOfTheTicket;
    }


    public char getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(char accessRights) {
        this.accessRights = accessRights;
    }

    public Integer getNumbOfRegistration() {
        return numbOfRegistration;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceOfWorkOrStudy() {
        return placeOfWorkOrStudy;
    }

    public void setPlaceOfWorkOrStudy(String placeOfWorkOrStudy) {
        this.placeOfWorkOrStudy = placeOfWorkOrStudy;
    }

    @Deprecated
    public void setNumberOfTheTicket(String numberOfTheTicket) {
        this.numberOfTheTicket = numberOfTheTicket;
    }

    @Override
    public String toString() {
        return "User{" +
                "numberOfTheTicket='" + numberOfTheTicket + '\'' +
                ", accessRights=" + accessRights +
                ", numbOfRegistration=" + numbOfRegistration +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", address='" + address + '\'' +
                ", placeOfWorkOrStudy='" + placeOfWorkOrStudy + '\''+'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return accessRights == user.accessRights &&
                yearOfBirth == user.yearOfBirth &&
                fullName.equals(user.fullName) &&
                Objects.equals(address, user.address) &&
                Objects.equals(placeOfWorkOrStudy, user.placeOfWorkOrStudy);
    }

    @Override
    public int hashCode() {
        int result = 17;
        int prime = 31;

        result = result * prime + Objects.hash(fullName, address, placeOfWorkOrStudy);
        result = result * prime + yearOfBirth;
        result = result * prime + accessRights;

        return Math.abs(result);
    }


    @Override
    public void getCart(int numb) {
        System.out.println(numb + ". Номер билета: " + this.getNumberOfTheTicket() + ",ФИО: " + this.getFullName());
    }

    @Override
    public int compareTo(@NotNull User o) {
        return this.fullName.compareTo(o.fullName);
    }
}
