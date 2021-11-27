package com.mieker.santaApi.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Gift {

    @Id
    private String giftId;
    private String name;
    private String description;
    private String link;
    private BigDecimal price;
    private User wantedBy;
    private User givingBy;

}
