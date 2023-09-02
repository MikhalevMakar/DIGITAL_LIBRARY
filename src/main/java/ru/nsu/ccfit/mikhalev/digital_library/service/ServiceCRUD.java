package ru.nsu.ccfit.mikhalev.digital_library.service;

import java.util.List;

public interface ServiceCRUD<T> {

    T getInfoByTitle(String title);

    void add(T typeDto);

    void edit(String oldTitle, T typeDto);

    void delete(String title);

    List<T> getPage(Integer numberPage);
}
