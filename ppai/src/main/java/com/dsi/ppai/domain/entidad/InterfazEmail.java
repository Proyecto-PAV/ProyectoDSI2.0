package com.dsi.ppai.domain.entidad;

import lombok.Data;

@Data
public class InterfazEmail {

    public void enviarEmail(String email){
        System.out.println(email);
    }
}
