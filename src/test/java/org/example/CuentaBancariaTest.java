package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaTest {

    private CuentaBancaria cuentaBancaria;

    @BeforeEach
    void setUp() {
        cuentaBancaria = new CuentaBancaria();
    }

    @Test
    void depositarSaldoPositivo() {
        cuentaBancaria.depositar(35);
        assertEquals(35, cuentaBancaria.obtenerSaldo(), "El saldo debe ser 35 después de depositar");
    }

    @Test
    void depositarSaldoCero() {
        /* Ojo! recuerda que creamos un objeto cuentaBancaria nuevo antes de cada test (ver @BeforeEach) */
        cuentaBancaria.depositar(0);
        assertEquals(0, cuentaBancaria.obtenerSaldo(), "El saldo debe ser 0 después de depositar");
    }

    @Test
    void depositarSaldoNegativo() {
        /* En caso de que el depósito sea negativo, se lanza una excepción tipo IllegalArgumentException */
        // cuentaBancaria.depositar(-3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> cuentaBancaria.depositar(-3));
        assertEquals("La cantidad no puede ser negativa", exception.getMessage());
    }

    @Test
    void testRetirarConSaldoSuficiente() {
        cuentaBancaria.depositar(1000);
        assertTrue(cuentaBancaria.retirar(500), "Debe permitir el retiro si hay saldo suficiente");
        assertEquals(500, cuentaBancaria.obtenerSaldo(), "El saldo debe actualizarse correctamente");
    }

    @Test
    void testRetirarSinSaldoSuficiente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> cuentaBancaria.retirar(47));
        assertEquals("El saldo es insuficiente", exception.getMessage());
    }

    @Test
    void testRetirarCantidadNegativa() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> cuentaBancaria.retirar(-100));
        assertEquals("La cantidad no puede ser negativa", exception.getMessage());
    }
}