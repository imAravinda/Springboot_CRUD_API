package com.example.crud.spring_crud.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private String transId;
    private String transTms;
    private String rcNum;
    private String clientId;
    private List<EventData> events;
}
