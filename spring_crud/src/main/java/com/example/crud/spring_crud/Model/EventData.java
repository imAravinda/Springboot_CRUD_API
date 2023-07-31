package com.example.crud.spring_crud.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventData {
    private int eventCnt;
    private String locationCd;
    private String locationId1;
    private String locationId2;
    private String addrNbr;
}
