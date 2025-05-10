package com.globaline.logistic.api.globaline_logistic_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String cliente;

    @Column
    private String categoria;

    @Column
    private String ubicacion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column
    private LocalDateTime fechaEntrada;

    @Column
    private LocalDateTime fechaSalida;
}
