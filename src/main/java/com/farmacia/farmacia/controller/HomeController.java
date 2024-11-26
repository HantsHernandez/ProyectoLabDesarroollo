package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.*;
import com.farmacia.farmacia.repository.UsuarioRepository;
import com.farmacia.farmacia.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private MedicamentosService medicamentosService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LaboratorioService laboratorioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private DetalleVentaService detalleVentaService;


    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/fragmentoEmpleados")
    public String fragmentoEmpleados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(value = "palabraBuscar", required = false) String palabraBuscar,
            Model model) {
        Page<Empleado> paginacionEmpleados = empleadoService.listaEmpleadosPaginados(page, size, palabraBuscar);
        model.addAttribute("listaEmpleados", paginacionEmpleados.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginacionEmpleados.getTotalPages());
        model.addAttribute("palabraBuscar", palabraBuscar);
        return "fragments/GestionEmpleados :: contenido";
    }

    @GetMapping("/fragmentoCategorias")
    public String fragmentoCategorias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(value = "palabraBuscar", required = false) String palabraBuscar,
            Model model) {
        Page<Categoria> paginacionCategorias = this.categoriaService.listaCategoriasPaginados(page, size, palabraBuscar);
        model.addAttribute("listaCategorias", paginacionCategorias.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginacionCategorias.getTotalPages());
        model.addAttribute("palabraBuscar", palabraBuscar); // Para persistir la búsqueda en la vista
        return "fragments/GestionCategorias :: contenido";
    }


    @GetMapping("/fragmentoMarcas")
    public String fragmentoMarca(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,

            Model model) {
        Page<Marca> paginacionMarca = this.marcaService.listaMarcasPaginados(page, size);
        model.addAttribute("listaMarcas", paginacionMarca.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginacionMarca.getTotalPages());
        return "fragments/GestionMarcas :: contenido";
    }

    @GetMapping("/fragmentoMedicamentos")
    public String fragmentoMedicamentos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(value = "palabraBuscar", required = false) String palabraBuscar,
            Model model) {
        Page<Medicamento> paginacionMedicamentos = medicamentosService.listaCategoriasPaginados(page, size,palabraBuscar);
        model.addAttribute("listaMedicamentos", paginacionMedicamentos.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginacionMedicamentos.getTotalPages());
        model.addAttribute("palabraBuscar", palabraBuscar);
        return "fragments/GestionMedicamentos :: contenido";
    }

    @GetMapping("/fragmentoUsuario")
    public String fragmentoUsuario(Model model){
        model.addAttribute("listaUsuarios", this.usuarioService.listaUsuarios());
        return "fragments/GestionarUsuario :: contenido";
    }

    @GetMapping("/fragmentoClientes")
    public String fragmentoClientes(Model model) {
        model.addAttribute("listaClientes", this.clienteService.listaClientes());
        return "fragments/GestionClientes :: contenido";
    }

    @GetMapping("/fragmentoLaboratorios")
    public String fragmentoLaboratorio(Model model) {
        model.addAttribute("listaLaboratorio",  this.laboratorioService.listaLaboratorios());
        return "fragments/GestionLaboratorios :: contenido";
    }


    @GetMapping("/factura")
    public String prueba(Model model){
        Venta venta = this.ventaService.obtenerVenta(2L);
        model.addAttribute("listaDetalles",venta.getListaDetalle());
        model.addAttribute("cliente", venta.getCliente());
        model.addAttribute("venta", venta);
        return "factura";
    }

    @GetMapping("/fragmentoDetallesVentas")
    public String prueba2(Model model){
        model.addAttribute("listaVentas",this.ventaService.listaVentas());
        return "fragments/GestionDetallesVentas";
    }

    @GetMapping("/detalleVenta/{id}")
    public String detalleventaVista(Model model, @PathVariable Long id){
        model.addAttribute("detallesVenta", this.detalleVentaService.obtenerDetallesPorIdVenta(id));
        model.addAttribute("venta", this.ventaService.obtenerVenta(id));
        return "fragments/GestionVenta";
    }

}
