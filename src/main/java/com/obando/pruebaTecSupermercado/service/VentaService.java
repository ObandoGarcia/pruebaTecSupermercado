package com.obando.pruebaTecSupermercado.service;

import com.obando.pruebaTecSupermercado.dto.DetalleVentaDto;
import com.obando.pruebaTecSupermercado.dto.VentaDto;
import com.obando.pruebaTecSupermercado.exception.NotFoundException;
import com.obando.pruebaTecSupermercado.mapper.Mapper;
import com.obando.pruebaTecSupermercado.model.DetalleVenta;
import com.obando.pruebaTecSupermercado.model.Producto;
import com.obando.pruebaTecSupermercado.model.Sucursal;
import com.obando.pruebaTecSupermercado.model.Venta;
import com.obando.pruebaTecSupermercado.repository.ProductoRepository;
import com.obando.pruebaTecSupermercado.repository.SucursalRepository;
import com.obando.pruebaTecSupermercado.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final SucursalRepository sucursalRepository;

    public VentaService(ProductoRepository productoRepository, VentaRepository ventaRepository, SucursalRepository sucursalRepository) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public List<VentaDto> traerVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDto> ventaDto = new ArrayList<>();

        VentaDto dto;

        for (Venta v: ventas){
            dto= Mapper.toDto(v);
                ventaDto.add(dto);
            }

        return ventaDto;
    }

    @Override
    public VentaDto crearVenta(VentaDto ventaDto) {
        if (ventaDto == null) throw new RuntimeException("VentaDto es null");

        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar una sucursal");

        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw  new RuntimeException("Debe incluir al menos un producto");

            //Buscar sucursal

        Sucursal sucursal = sucursalRepository
                .findById(ventaDto.getIdSucursal())
                .orElse(null);
        if (sucursal == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }

        //Crear venta
        Venta venta = new Venta();
        venta.setFecha(ventaDto.getFecha());
        venta.setEstado(ventaDto.getEstado());
        venta.setSucursal(sucursal);
        venta.setTotal(venta.getTotal());

        List<DetalleVenta> detalles = new ArrayList<>();
        double totalCalculado = 0.0;

        for (DetalleVentaDto detDto : ventaDto.getDetalle()){
            Producto p = productoRepository.findByNombre(detDto.getNombreProducto())
                    .orElse(null);

            if (p == null){
                throw new RuntimeException("Producto no encontrado: " + detDto.getNombreProducto());
            }

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(p);
            detalleVenta.setPrecio(detDto.getPrecio());
            detalleVenta.setCantidad(detDto.getCantidad());
            detalleVenta.setVenta(venta);

            detalles.add(detalleVenta);
            totalCalculado = totalCalculado + (detalleVenta.getPrecio() * detalleVenta.getCantidad());
        }

        //Seteamos la lista de detalles
        venta.setDetalleVentas(detalles);

        //Guardar detalle
        venta = ventaRepository.save(venta);

        return Mapper.toDto(venta);
    }

    @Override
    public VentaDto actualizarVenta(Long id, VentaDto ventaDto) {

        //Buscar si la venta existe
        Venta v = ventaRepository.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if (ventaDto.getFecha() != null){
            v.setFecha(ventaDto.getFecha());
        }

        if (ventaDto.getEstado() != null){
            v.setEstado(ventaDto.getEstado());
        }

        if (ventaDto.getTotal() != null){
            v.setTotal(ventaDto.getTotal());
        }

        if (ventaDto.getIdSucursal() != null){
            Sucursal s = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);

            if (s == null) throw new NotFoundException("Sucursl no encontrda");

            v.setSucursal(s);
        }

        ventaRepository.save(v);

        return Mapper.toDto(v);
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepository.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepository.delete(v);
    }
}
