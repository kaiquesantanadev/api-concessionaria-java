package com.estudos.concessionaria.dto;

import jakarta.validation.constraints.NotNull;

public record CarroDTO(@NotNull String fabricante, @NotNull double preco, @NotNull String nome) {

}
