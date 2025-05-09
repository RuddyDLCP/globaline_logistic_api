package com.globaline.logistic.api.globalie_logistic_api.repository;

import com.globaline.logistic.api.globalie_logistic_api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByCliente(String cliente);

    boolean existsByCodigo(String codigo);

    @Query("SELECT COUNT(p) FROM Producto p WHERE p.cantidad <= 5")
    Long contarProductosStockBajo();

    @Query("SELECT COUNT(DISTINCT p.categoria) FROM Producto p")
    Long contarCategorias();

    @Query("SELECT p.categoria, COUNT(p) FROM Producto p GROUP BY p.categoria")
    List<Object[]> contarProductosPorCategoria();

    @Query("SELECT SUM(p.cantidad) FROM Producto p")
    Long contarTotalUnidades();

    @Query("SELECT COUNT(DISTINCT p.cliente) FROM Producto p")
    Long contarClientes();
}