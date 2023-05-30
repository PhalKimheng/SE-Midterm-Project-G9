package se.group9.gicCafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.group9.gicCafe.model.Tables;
import se.group9.gicCafe.service.TablesService;

@RequestMapping("/admin")
@Controller
public class TablesController {
    private TablesService tablesService;

    public TablesController(TablesService tablesService) {
        super();
        this.tablesService = tablesService;
    }

    @GetMapping("/manageTables")
	public String manageTablesForm(Model model) {
		
		return "admin/manageTables";
	}

    @PostMapping("/saveTables")
	public String saveTables(@RequestParam("number") int tables_number) {

        tablesService.deleteAllTables();
        
        for(int i=0; i<tables_number; i++) {
            Tables tables = new Tables();
            tables.setStatus("available");
            tablesService.saveTables(tables);
        }

		return "redirect:/admin/cashiers";
	}
}
