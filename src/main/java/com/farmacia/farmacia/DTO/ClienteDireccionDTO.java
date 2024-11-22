package com.farmacia.farmacia.DTO;

import com.farmacia.farmacia.entity.Cliente;
import com.farmacia.farmacia.entity.Direccion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDireccionDTO {

    private Cliente cliente;

    private Direccion direccion;
}
