package com.tejbhan.polygon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAreaDto {

    private List<List<Double>> coordinates;

}
