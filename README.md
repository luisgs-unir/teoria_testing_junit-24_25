# ✔️**Pruebas Unitarias con JUnit en IntelliJ IDEA**

## **1. Introducción a JUnit**

### **¿Qué es JUnit?**
JUnit es un **framework de pruebas unitarias para Java** que permite validar el comportamiento del código de manera automática. Es ampliamente utilizado en metodologías de desarrollo como **TDD (Test-Driven Development)**.

### **¿Por qué usar JUnit?**
✅ Permite detectar errores temprano en el desarrollo.  
✅ Facilita el mantenimiento del código y evita regresiones.  
✅ Automatiza las pruebas para mejorar la calidad del software.  
✅ Es una **herramienta esencial** en el desarrollo ágil y la integración continua.

### **JUnit y TDD**
El **Desarrollo Guiado por Pruebas (TDD)** sigue este ciclo:
1. **Escribir una prueba** antes de implementar la funcionalidad.
2. **Ejecutar la prueba**, que debe fallar porque el código aún no está escrito.
3. **Implementar el código necesario** para que la prueba pase.
4. **Refactorizar el código**, asegurando que todas las pruebas siguen pasando.

---

## 📌 **2. ¿Qué es una anotación en JUnit?**

Las **anotaciones** en JUnit son **metadatos** que proporcionan información adicional al código y afectan la ejecución de las pruebas. Se utilizan para definir el comportamiento y el ciclo de vida de los **métodos de prueba**.

Por ejemplo:
```java
@BeforeEach
void setUp() {
    cuenta = new CuentaBancaria(); // Se ejecuta antes de cada prueba
}
```
En este caso, `@BeforeEach` indica que el método `setUp()` se ejecutará antes de cada prueba individual.

### 🔹 **Principales anotaciones en JUnit 5**
- `@Test`: Marca un método como una prueba unitaria.
- `@BeforeEach`: Se ejecuta antes de cada prueba (configuración).
- `@AfterEach`: Se ejecuta después de cada prueba (limpieza).
- `@BeforeAll`: Se ejecuta una vez antes de todas las pruebas.
- `@AfterAll`: Se ejecuta una vez después de todas las pruebas.
- `@DisplayName`: Define un nombre descriptivo para la prueba.

---

## 📌 **3. ¿Qué es una aserción en JUnit?**

Las **aserciones** en JUnit son métodos utilizados para **verificar si el código bajo prueba produce el resultado esperado**. Si una aserción falla, la prueba también fallará.

Ejemplo:
```java
@Test
void testDepositar() {
    cuenta.depositar(1000);
    assertEquals(1000, cuenta.obtenerSaldo(), "El saldo debe ser 1000 después de depositar");
}
```
Aquí, `assertEquals(expected, actual, mensajeOpcional)` verifica que el saldo de la cuenta **sea exactamente 1000** después de depositar.
### 🔹 **Métodos de aserción en JUnit 5**
- `assertEquals(expected, actual)`: Comprueba si dos valores son iguales.
- `assertTrue(condition)`: Verifica si una condición es verdadera.
- `assertFalse(condition)`: Verifica si una condición es falsa.
- `assertThrows(Exception.class, () -> method())`: Verifica excepciones.

---

## **4. Configuración de JUnit en IntelliJ IDEA**
### **Instalación y configuración**
1. **Abrir IntelliJ IDEA** y crear un nuevo proyecto Java.
2. **Añadir JUnit 5** como dependencia:
    - Si usas **Maven**, agrega en `pom.xml`:
      ```xml
      <dependencies>
          <dependency>
              <groupId>org.junit.jupiter</groupId>
              <artifactId>junit-jupiter-api</artifactId>
              <version>5.9.0</version>
              <scope>test</scope>
          </dependency>
      </dependencies>
      ```
    - Si usas **Gradle**, agrega en `build.gradle`:
      ```gradle
      dependencies {
          testImplementation 'org.junit.jupiter:junit-jupiter-api:5.9.0'
          testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.0'
      }
      ```
3. **Crear una clase de prueba** en `src/test/java/` para probar `CuentaBancaria`.

---

## **5. Primeras pruebas unitarias con JUnit**
Vamos a escribir pruebas para la clase `CuentaBancaria`.

### **Código de `CuentaBancaria`**
```java
/**
 * Representa una cuenta bancaria con operaciones básicas.
 */
public class CuentaBancaria {

    private double saldo;

    /**
     * Deposita una cantidad en la cuenta.
     * @param cantidad Monto a depositar, debe ser positivo.
     */
    public void depositar(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        saldo += cantidad;
    }

    /**
     * Retira una cantidad de la cuenta si hay saldo suficiente.
     * @param cantidad Monto a retirar.
     * @return {@code true} si la operación fue exitosa, {@code false} si no hay saldo suficiente.
     */
    public boolean retirar(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("No se puede retirar una cantidad negativa");
        }
        if (saldo >= cantidad) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    /**
     * Obtiene el saldo actual de la cuenta.
     * @return El saldo disponible en la cuenta.
     */
    public double obtenerSaldo() {
        return saldo;
    }
}
```

### **Clase de Pruebas con JUnit**
```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase CuentaBancaria.
 */
class CuentaBancariaTest {

    private CuentaBancaria cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaBancaria();
    }

    @Test
    void testDepositar() {
        cuenta.depositar(1000);
        assertEquals(1000, cuenta.obtenerSaldo(), "El saldo debe ser 1000 después de depositar");
    }

    @Test
    void testDepositarCantidadNegativa() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> cuenta.depositar(-500));
        assertEquals("El monto debe ser positivo", exception.getMessage());
    }

    @Test
    void testRetirarConSaldoSuficiente() {
        cuenta.depositar(1000);
        assertTrue(cuenta.retirar(500), "Debe permitir el retiro si hay saldo suficiente");
        assertEquals(500, cuenta.obtenerSaldo(), "El saldo debe actualizarse correctamente");
    }

    @Test
    void testRetirarSinSaldoSuficiente() {
        assertFalse(cuenta.retirar(1000), "No debe permitir retiro sin saldo suficiente");
    }

    @Test
    void testRetirarCantidadNegativa() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(-100));
        assertEquals("No se puede retirar una cantidad negativa", exception.getMessage());
    }
}
```

---

## **6. Ejercicios prácticos para los estudiantes**

### 🔹 **Ejercicio 1: Escribir pruebas para `Empleado`**
Implementa la clase `Empleado` y prueba los siguientes métodos:
- `Empleado(String nombre, double salario)`
- `aumentarSalario(double porcentaje)`
- `obtenerSalario()`

Asegúrate de probar:
✅ Aumentos de salario correctos.  
✅ Que el salario inicial sea el esperado.  
✅ Que no se puedan aumentar salarios con valores negativos.

---

### 🔹 **Ejercicio 2: Pruebas parametrizadas**
Utiliza `@ParameterizedTest` para probar múltiples valores en la función `retirar()` de `CuentaBancaria`.

Ejemplo:
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaParamTest {

    private final CuentaBancaria cuenta = new CuentaBancaria();

    @ParameterizedTest
    @ValueSource(doubles = {50.0, 100.0, 150.0})
    void testRetirarConDiferentesMontos(double monto) {
        cuenta.depositar(200);
        assertTrue(cuenta.retirar(monto), "Debe permitir el retiro de " + monto);
    }
}
```

---

### 🔹 **Ejercicio 3: Uso de Mockito para simular dependencias**
Si la clase `CuentaBancaria` tuviera una dependencia externa, usa **Mockito** para **simularla** en las pruebas.

```java
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaMockTest {

    @Test
    void testTransferencia() {
        CuentaBancaria cuentaOrigen = new CuentaBancaria();
        CuentaBancaria cuentaDestino = Mockito.mock(CuentaBancaria.class);

        cuentaOrigen.depositar(500);
        assertTrue(cuentaOrigen.transferir(cuentaDestino, 200), "La transferencia debe ser exitosa");

        Mockito.verify(cuentaDestino).depositar(200);
    }
}
```

---

### **Recursos adicionales**
📖 [Documentación oficial de JUnit](https://junit.org/junit5/docs/current/user-guide/)  
🎥 [Video tutorial sobre pruebas con JUnit en IntelliJ](https://www.jetbrains.com/help/idea/testing.html)

---

📌 ¡Ahora tienen las herramientas para escribir código **robusto y confiable** con pruebas unitarias!  

