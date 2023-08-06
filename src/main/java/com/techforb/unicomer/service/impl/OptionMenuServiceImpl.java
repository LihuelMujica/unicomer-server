package com.techforb.unicomer.service.impl;

import com.techforb.unicomer.Exception.ResourceAlreadyExistsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.OptionMenuCreateDTO;
import com.techforb.unicomer.model.OptionMenu;
import com.techforb.unicomer.repository.OptionMenuRepository;
import com.techforb.unicomer.service.OptionMenuService;
import com.techforb.unicomer.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionMenuServiceImpl implements OptionMenuService {

    private final OptionMenuRepository optionMenuRepository;

    @Autowired
    public OptionMenuServiceImpl(OptionMenuRepository optionMenuRepository) {
        this.optionMenuRepository = optionMenuRepository;
    }

    @Override
    public List<OptionMenu> getMenu() {
        return optionMenuRepository.findAll();
    }

    @Override
    public OptionMenu getOptionById(Integer id) throws ResourceNotFoundException {
        return optionMenuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option Menu not found"));
    }

    @Override
    public OptionMenu addOptionMenu(OptionMenuCreateDTO optionMenu) throws ResourceAlreadyExistsException {
        if (optionMenuRepository.existsByNameOrUrl(optionMenu.getName(), optionMenu.getUrl())) {
            throw new ResourceAlreadyExistsException("Option Menu already exists");
        }
        OptionMenu newOptionMenu = OptionMenu.builder()
                .name(optionMenu.getName())
                .url(optionMenu.getUrl())
                .icon(optionMenu.getIcon())
                .build();
        return optionMenuRepository.save(newOptionMenu);
    }

    @Override
    public OptionMenu updateOptionMenu(Integer id, OptionMenuCreateDTO optionMenu) throws ResourceNotFoundException {
        OptionMenu inputOptionMenu = OptionMenu.builder()
                .name(optionMenu.getName())
                .url(optionMenu.getUrl())
                .icon(optionMenu.getIcon())
                .build();
        OptionMenu newOptionMenu = optionMenuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option Menu not found"));
        Util.mergeObjects(optionMenu, newOptionMenu);
        return optionMenuRepository.save(newOptionMenu);
    }

    @Override
    public void deleteOptionMenu(Integer id) throws ResourceNotFoundException {
        if (!optionMenuRepository.existsById(id)) {
            throw new ResourceNotFoundException("Option Menu not found");
        }
        optionMenuRepository.deleteById(id);
    }
}
