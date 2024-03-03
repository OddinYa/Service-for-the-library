package org.course_work.service;

import java.io.IOException;

public interface FileLoader<T> {

    void load(T[] arr) throws IOException;
}
