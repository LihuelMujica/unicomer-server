package com.techforb.unicomer.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionMenuCreateDTO {
    private String name;
    private String url;
    private String icon;
}
