package se.group9.gicCafe.service.Implementation;

import org.springframework.stereotype.Service;

import se.group9.gicCafe.model.Tables;
import se.group9.gicCafe.repository.TablesRepo;
import se.group9.gicCafe.service.TablesService;

@Service
public class TablesServiceImp implements TablesService {
    private TablesRepo tablesRepo;

    public TablesServiceImp(TablesRepo tablesRepo) {
        super();
        this.tablesRepo = tablesRepo;
    }

    @Override
    public Tables saveTables(Tables tables) {
        return tablesRepo.save(tables);
    }

	@Override
	public void deleteAllTables() {
		tablesRepo.deleteAll();	
	}
}
