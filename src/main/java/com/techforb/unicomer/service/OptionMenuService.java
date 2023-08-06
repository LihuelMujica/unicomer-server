package com.techforb.unicomer.service;

import com.techforb.unicomer.Exception.ResourceAlreadyExistsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.OptionMenuCreateDTO;
import com.techforb.unicomer.model.OptionMenu;

import java.util.List;

public interface OptionMenuService {

    List<OptionMenu> getMenu();
    OptionMenu getOptionById(Integer id) throws ResourceNotFoundException;
    OptionMenu addOptionMenu(OptionMenuCreateDTO optionMenu) throws ResourceAlreadyExistsException;
    OptionMenu updateOptionMenu(Integer id, OptionMenuCreateDTO optionMenu) throws ResourceNotFoundException;
    void deleteOptionMenu(Integer id) throws ResourceNotFoundException;
}
