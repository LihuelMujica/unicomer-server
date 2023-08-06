package com.techforb.unicomer.controller;

import com.techforb.unicomer.Exception.ResourceAlreadyExistsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.OptionMenuCreateDTO;
import com.techforb.unicomer.model.OptionMenu;
import com.techforb.unicomer.service.OptionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/optionmenu")
@CrossOrigin(origins="*")
public class OptionsMenuController {
    private final OptionMenuService optionsMenuService;

    @Autowired
    public OptionsMenuController(OptionMenuService optionsMenuService) {
        this.optionsMenuService = optionsMenuService;
    }

    @GetMapping
    public ResponseEntity<List<OptionMenu>> getMenu() {
        return ResponseEntity.ok(optionsMenuService.getMenu());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionMenu> getOptionById(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(optionsMenuService.getOptionById(id));
    }

    @PostMapping
    public ResponseEntity<OptionMenu> addOptionMenu(@RequestBody OptionMenuCreateDTO optionMenu) throws ResourceAlreadyExistsException {
        return ResponseEntity.ok(optionsMenuService.addOptionMenu(optionMenu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionMenu> updateOptionMenu(@PathVariable Integer id, @RequestBody OptionMenuCreateDTO optionMenu) throws ResourceNotFoundException {
        return ResponseEntity.ok(optionsMenuService.updateOptionMenu(id, optionMenu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOptionMenu(@PathVariable Integer id) throws ResourceNotFoundException {
        optionsMenuService.deleteOptionMenu(id);
        return ResponseEntity.ok().build();
    }

}
