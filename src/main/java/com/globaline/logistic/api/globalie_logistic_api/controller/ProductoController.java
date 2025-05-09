package com.globaline.logistic.api.globalie_logistic_api.controller;

import com.globaline.logistic.api.globalie_logistic_api.model.Producto;
import com.globaline.logistic.api.globalie_logistic_api.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, @RequestParam(required = false) String usuario) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.obtenerProductoPorId(id)
                .map(productoExistente -> {
                    producto.setId(id);
                    Producto productoActualizado = productoService.guardarProducto(producto);
                    return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
                .map(producto -> {
                    productoService.eliminarProducto(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();

        Map<String, Object> estadisticas = new HashMap<>();

        // Total de productos
        estadisticas.put("totalProductos", productos.size());

        // Total de unidades
        int totalUnidades = productos.stream()
                .mapToInt(Producto::getCantidad)
                .sum();
        estadisticas.put("totalUnidades", totalUnidades);

        // Categorías únicas
        long categorias = productos.stream()
                .map(Producto::getCategoria)
                .distinct()
                .count();
        estadisticas.put("categorias", categorias);

        // Clientes únicos
        long clientes = productos.stream()
                .map(Producto::getCliente)
                .distinct()
                .count();
        estadisticas.put("clientes", clientes);

        // Productos por categoría
        Map<String, Long> productosPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.counting()
                ));
        estadisticas.put("productosPorCategoria", productosPorCategoria);

        return new ResponseEntity<>(estadisticas, HttpStatus.OK);
    }
}
