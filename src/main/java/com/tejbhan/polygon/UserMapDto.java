package com.tejbhan.polygon;

import lombok.Data;

@Data
public class UserMapDto {

    private Long userId;
    private String name;
    private ServiceAreaDto serviceArea;

}
