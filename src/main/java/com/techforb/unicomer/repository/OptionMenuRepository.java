package com.techforb.unicomer.repository;

import com.techforb.unicomer.model.OptionMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionMenuRepository extends JpaRepository<OptionMenu, Integer> {
    boolean existsByNameOrUrl(String name, String url);
}
