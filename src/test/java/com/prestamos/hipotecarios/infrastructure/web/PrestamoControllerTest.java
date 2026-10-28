package com.prestamos.hipotecarios.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prestamos.hipotecarios.application.usecase.CrearPrestamoUseCase;
import com.prestamos.hipotecarios.domain.model.Prestamo;
import com.prestamos.hipotecarios.domain.ports.PrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
@DisplayName("Tests de integración para PrestamoController")
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CrearPrestamoUseCase crearPrestamoUseCase;

    @MockBean
    private PrestamoRepository prestamoRepository;

    private UUID prestamoId;
    private UUID clienteId;
    private BigDecimal monto;
    private BigDecimal tasaInteres;
    private Integer plazoMeses;
    private LocalDate fechaInicio;
    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        prestamoId = UUID.randomUUID();
        clienteId = UUID.randomUUID();
        monto = new BigDecimal("300000.00");
        tasaInteres = new BigDecimal("0.0725");
        plazoMeses = 360;
        fechaInicio = LocalDate.now();

        prestamo = new Prestamo(clienteId, monto, tasaInteres, plazoMeses, fechaInicio);
    }

    @Test
    @DisplayName("Debe retornar 201 Created cuando el préstamo se crea exitosamente")
    void debeRetornar201CuandoPrestamoSeCrea() throws Exception {
        when(crearPrestamoUseCase.ejecutar(any(), any(), any(), any(), any()))
            .thenReturn(Optional.of(prestamo));

        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"monto\":300000.00,\"tasaInteres\":0.0725,\"plazoMeses\":360}"))
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"))
            .andExpect(jsonPath("$.clienteId").value(clienteId.toString()))
            .andExpect(jsonPath("$.monto").value(300000.00))
            .andExpect(jsonPath("$.tasaInteres").value(0.0725))
            .andExpect(jsonPath("$.plazoMeses").value(360));
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el monto es negativo")
    void debeRetornar400CuandoMontoEsNegativo() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"monto\":-1000,\"tasaInteres\":0.05,\"plazoMeses\":12}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el clienteId está vacío")
    void debeRetornar400CuandoClienteIdEstaVacio() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"monto\":100000,\"tasaInteres\":0.05,\"plazoMeses\":24}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando el plazo es cero")
    void debeRetornar400CuandoPlazoEsCero() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"monto\":100000,\"tasaInteres\":0.05,\"plazoMeses\":0}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando la tasa de interés es negativa")
    void debeRetornar400CuandoTasaInteresEsNegativa() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"monto\":100000,\"tasaInteres\":-0.01,\"plazoMeses\":24}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando falta el campo monto")
    void debeRetornar400QuandoFaltaMonto() throws Exception {
        mockMvc.perform(post("/api/v1/prestamos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clienteId\":\"" + clienteId + "\",\"tasaInteres\":0.05,\"plazoMeses\":24}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("Debe retornar 200 OK y el préstamo cuando se busca por ID")
    void debeRetornarPrestamoPorId() throws Exception {
        when(prestamoRepository.findById(prestamoId)).thenReturn(Optional.of(prestamo));

        mockMvc.perform(get("/api/v1/prestamos/" + prestamoId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.clienteId").value(clienteId.toString()))
            .andExpect(jsonPath("$.monto").value(300000.00))
            .andExpect(jsonPath("$.tasaInteres").value(0.0725))
            .andExpect(jsonPath("$.plazoMeses").value(360));
    }

    @Test
    @DisplayName("Debe retornar 404 Not Found cuando el préstamo no existe")
    void debeRetornar404CuandoPrestamoNoExiste() throws Exception {
        when(prestamoRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/prestamos/" + UUID.randomUUID()))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Debe retornar 200 OK y lista de préstamos por cliente")
    void debeRetornarPrestamosPorCliente() throws Exception {
        when(prestamoRepository.findByClienteId(clienteId)).thenReturn(java.util.List.of(prestamo));

        mockMvc.perform(get("/api/v1/prestamos").param("clienteId", clienteId.toString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].clienteId").value(clienteId.toString()));
    }

    @Test
    @DisplayName("Debe retornar 200 OK y lista vacía cuando el cliente no tiene préstamos")
    void debeRetornarListaVaciaCuandoClienteSinPrestamos() throws Exception {
        when(prestamoRepository.findByClienteId(any())).thenReturn(java.util.List.of());

        mockMvc.perform(get("/api/v1/prestamos").param("clienteId", UUID.randomUUID().toString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Debe retornar 204 No Content cuando se elimina un préstamo exitosamente")
    void debeEliminarPrestamoExitosamente() throws Exception {
        mockMvc.perform(delete("/api/v1/prestamos/" + prestamoId))
            .andExpect(status().isNoContent());
    }
}