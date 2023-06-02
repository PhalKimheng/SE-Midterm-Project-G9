package se.group9.gicCafe.service;

import java.util.List;

import se.group9.gicCafe.model.Order;
import se.group9.gicCafe.model.Tables;

public interface TablesService {
    public List<Tables> getAllTables();

    public Tables getTableByID(int id);

    public Tables saveAndFlushTable(Tables table);

    /**
     * a table can only have one order that has status 0 mean not finish or pending so get that order...
     * @param tid
     * @return :Order that has status 0 (pending)
     */
    public Order getPendingOrderByTableID(int tid);

}
