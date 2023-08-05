package com.techforb.unicomer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "options_menu")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String url;
    private String icon;
}
