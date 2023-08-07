package com.techforb.unicomer.dto;

import com.techforb.unicomer.model.Balance;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BalanceHistoryDTO {
    List<Balance> thisWeek;
    List<Balance> lastWeek;
}
