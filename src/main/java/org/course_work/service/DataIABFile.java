package org.course_work.service;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.struct.linkedList.List;

public class DataIABFile implements FileWriter<DataOnTheIssuanceAndAcceptanceOfBooks>,FileReader<List> {
    @Override
    public List readerFile() {
        return null;
    }

    @Override
    public void writeToFile(DataOnTheIssuanceAndAcceptanceOfBooks data) {

    }
}
