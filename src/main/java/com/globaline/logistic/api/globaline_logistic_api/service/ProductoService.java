package com.globaline.logistic.api.globaline_logistic_api.service;

import com.globaline.logistic.api.globaline_logistic_api.model.Producto;
import com.globaline.logistic.api.globaline_logistic_api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {
        // Si es un nuevo producto, establecer la fecha de entrada
        if (producto.getId() == null) {
            producto.setFechaEntrada(LocalDateTime.now());
        }

        // Si la cantidad es 0, establecer la fecha de salida
        if (producto.getCantidad() <= 0 && producto.getFechaSalida() == null) {
            producto.setFechaSalida(LocalDateTime.now());
        } else if (producto.getCantidad() > 0) {
            // Si hay cantidad, asegurarse de que no haya fecha de salida
            producto.setFechaSalida(null);
        }

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
