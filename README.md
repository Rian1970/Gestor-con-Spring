# Nota: Este proyecto sirve para entender los conceptos básicos de una aplicación desarrollada con Spring Boot.

# Instalación del Proyecto

## Paso 1: Descarga y descompresión

1. Descarga el proyecto en ZIP.
2. Descomprime el ZIP en cualquier carpeta.
3. Abrir la carpeta descargada con ayuda de un IDE.
4. Ubicar el archivo `src/main/java/com/example/product_management/StudentManagementSystemApplication.java` y ejecutarlo.

    ![image](https://github.com/user-attachments/assets/d4830eb7-793c-45c5-955b-dabf253e127e)

   **Imagen 1**: Archivo `src/main/java/com/example/product_management/StudentManagementSystemApplication.java`.

## Paso 2:

Con ayuda de Postman se puede verificar que se haya desplegado correctamente y consumir la aplicación con los diferentes verbos HTTP.

## URI GET :

  ```
   http://localhost:8080/sistema/api/v1/productos
   ```

## URI GET (Producto por nombre):

  ```
   http://localhost:8080/sistema/api/v1/productos/Papitas
   ```

## URI POST:

  ```
   http://localhost:8080/sistema/api/v1/productos
   ```

  Body (raw):

  ```
   {
    "id": 5,
    "name": "Chicharrones",
    "price": 5.0,
    "stock": 200
  }
   ```

## URI PUT:

  ```
   http://localhost:8080/sistema/api/v1/productos
   ```

  Body (raw):

  ```
   {
    "id": 5,
    "name": "Palomitas",
    "price": 15.0,
    "stock": 300
  }
   ```

## URI PATCH:

  ```
   http://localhost:8080/sistema/api/v1/productos
   ```

  Body (raw):

  ```
    {
    "id": 5,
    "name": "Gomiboing"
   }
   ```

## URI DELETE por id:

  ```
   http://localhost:8080/sistema/api/v1/productos/5
   ```


  

