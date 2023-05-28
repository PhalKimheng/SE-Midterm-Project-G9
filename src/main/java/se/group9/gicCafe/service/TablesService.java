package se.group9.gicCafe.service;

import se.group9.gicCafe.model.Tables;

public interface TablesService {
	Tables saveTables(Tables tables);
	
	void deleteAllTables();
}
