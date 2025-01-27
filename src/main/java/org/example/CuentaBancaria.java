package org.example;

/**
 * Representa una cuenta bancaria con operaciones básicas
 */
public class CuentaBancaria {

    private double saldo;

    /**
     * Deposita una cantidad en la cuenta
     * @param cantidad cantidad de dinero a ingresar
     * @throws IllegalArgumentException en caso de que la cantidad indicada sea un valor negativo
     */
    public void depositar(double cantidad) {
        if( cantidad < 0 ) {
            // System.out.println("El saldo no puede ser negativo");
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.saldo += cantidad;
    }



    /**
     * Retira una cantidad de la cuenta si hay saldo suficiente.
     * @param cantidad Monto a retirar.
     * @return {@code true} si la operación fue exitosa, {@code false} si no hay saldo suficiente.
     */
    public boolean retirar(double cantidad) {
        // Casos:
        // cantidad negativa
        if( cantidad < 0 ) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }

        // Saldo insuficiente
        if( this.saldo < cantidad ) {
            throw new IllegalArgumentException("El saldo es insuficiente");
        }

        // Cantidad positiva y saldo suficiente
        this.saldo -= cantidad;
        return true;
    }

    /**
     * Obtiene el saldo actual de la cuenta.
     * @return El saldo disponible en la cuenta.
     */
    public double obtenerSaldo() {
        return saldo;
    }
}
