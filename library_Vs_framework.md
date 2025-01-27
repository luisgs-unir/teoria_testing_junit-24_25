# Library Vs Framework

Veamos la diferencia entre **biblioteca** y **framework** en programación usando **Java** y una analogía culinaria.

---

## 🍽️ Biblioteca: Un conjunto de herramientas a tu disposición
Una **biblioteca** en Java es como un **conjunto de utensilios de cocina** o **ingredientes prelistos** que puedes usar cuando los necesites. Te ofrece funcionalidades específicas sin imponer cómo debes organizar tu código.

### 📌 Ejemplo culinario:
Imagina que estás cocinando una **pizza** desde cero, amasando la masa y preparando los ingredientes. Sin embargo, para la salsa decides usar una salsa de tomate enlatada en lugar de hacerla tú mismo. La salsa enlatada es una **biblioteca**: un recurso que puedes usar si lo deseas, pero tú decides **cuándo y cómo** utilizarlo.

### 📌 Ejemplo en Java:
Supongamos que necesitas manejar fechas y horas en Java. En lugar de programar toda la lógica desde cero, puedes usar la biblioteca **java.time**:

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EjemploBiblioteca {
    public static void main(String[] args) {
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Fecha formateada: " + hoy.format(formato));
    }
}
```
🔹 Aquí **java.time** es una **biblioteca**: la usas cuando la necesitas y decides cómo integrarla en tu código.

---

## 🍽️ Framework: Un chef que dicta cómo debes cocinar
Un **framework** en Java es como un **chef que dirige una cocina** y te dice cómo preparar cada platillo. Define reglas, estructura y flujo de trabajo, y tú solo sigues sus instrucciones.

### 📌 Ejemplo culinario:
Ahora imagina que en lugar de cocinar por tu cuenta, trabajas en la cocina de un **restaurante** con un chef exigente. Él te dice que la pizza debe prepararse de cierta forma, con tiempos exactos de horneado y un orden específico para los ingredientes. Tú solo sigues el flujo establecido.

### 📌 Ejemplo en Java:
Supongamos que estás desarrollando una aplicación web y decides usar **Spring Boot**, un framework que define cómo debes estructurar tu código y cómo se deben manejar las peticiones HTTP. Un ejemplo mínimo usando Spring Boot sería:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EjemploFramework {
    public static void main(String[] args) {
        SpringApplication.run(EjemploFramework.class, args);
    }
}

@RestController
class ControladorPizza {
    @GetMapping("/pizza")
    public String obtenerPizza() {
        return "Aquí tienes tu pizza";
    }
}
```

🔹 Aquí **Spring Boot** es un **framework** porque **impone una estructura y reglas** sobre cómo debes organizar tu aplicación. Tú solo implementas lo necesario dentro de su flujo de trabajo.

---

## 🔥 **Resumen Final**
| Característica        | Biblioteca 🛠️  | Framework 🍽️ |
|----------------------|---------------|--------------|
| **Quién tiene el control** | El programador | El framework |
| **Flexibilidad** | Alta (tomas lo que necesitas) | Baja (debes seguir sus reglas) |
| **Ejemplo culinario** | Salsa enlatada que usas cuando quieres | Chef que dirige la cocina |
| **Ejemplo en Java** | `java.time`, `javax.mail`, `JDBC` | `Spring Boot`, `JSF`, `Hibernate` |

