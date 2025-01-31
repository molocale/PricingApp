# Pricing App

### **Requisitos Mínimos**

* Java: 17 o superior
* Maven: 3.6.0 o superior

### **Iniciar la aplicación**
1. Clonar el repositorio

   Para obtener el proyecto, puedes clonarlo usando Git:

        git clone https://github.com/molocale/PricingApp.git
2. Construir el proyecto

   Una vez dentro del directorio del proyecto, usa Maven para construirlo:

        mvn clean install

3. Ejecutar la aplicación

   Puedes ejecutar la aplicación utilizando el siguiente comando:

        mvn spring-boot:run

Esto arrancará el servidor localmente en el puerto 8080.

**H2 Console**

La aplicación usa una base de datos en memoria H2 para almacenar los datos. Puedes acceder a la consola de H2 para ver y manipular los datos directamente desde tu navegador en:

    Consola H2: http://localhost:8080/h2-console

### **Consultar la API**

   Una vez que la aplicación esté en funcionamiento, podrás acceder a la API en:

* API Base URL: http://localhost:8080
* Swagger Editor: https://editor.swagger.io/