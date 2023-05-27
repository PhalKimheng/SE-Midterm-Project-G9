package se.group9.gicCafe.service;

import java.util.List;

import se.group9.gicCafe.model.Tables;

public interface TableService {
    public List<Tables> getAllTables();

    public Tables getTableByID(int id);

    public Tables saveAndFlushTable(Tables table);

   


}
