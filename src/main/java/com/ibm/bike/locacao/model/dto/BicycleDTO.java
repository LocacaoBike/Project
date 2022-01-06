package com.ibm.bike.locacao.model.dto;

import com.ibm.bike.locacao.model.Bicycle;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class BicycleDTO {

    private Long id;
    private String modelo;
    private String cor;

    public static BicycleDTO create (Bicycle b){
        ModelMapper modelMapper  = new ModelMapper();
        return modelMapper.map(b, BicycleDTO.class);
    }
}
