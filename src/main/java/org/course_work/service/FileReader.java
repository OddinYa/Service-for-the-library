package org.course_work.service;

import org.course_work.struct.MyStruct;
import org.course_work.struct.map.MyMap;

public interface FileReader<T extends MyStruct> {
    T readerFile();
}
