package se.group9.gicCafe.service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import se.group9.gicCafe.model.Tables;
import se.group9.gicCafe.repository.TableRepo;
import se.group9.gicCafe.service.TableService;

@Service
public class TableServiceImp implements TableService{
    private TableRepo tableRepo;
    public TableServiceImp(TableRepo tableRepo){
        super();
        this.tableRepo=tableRepo;
    }

    @Override
    public List<Tables> getAllTables() {
        return tableRepo.findAll();
    }

    @Override
    public Tables getTableByID(int id) {
        return tableRepo.findById(id).get();
    }

    @Override
    public Tables saveAndFlushTable(Tables table) {
        return tableRepo.saveAndFlush(table);
    }
    
}
