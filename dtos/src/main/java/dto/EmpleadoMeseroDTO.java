/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 * Data transfer object para representar a un empleado del tipo mesero
 * entre las capas de forma segura para enlazar las comandas creadas con el 
 * mesero que las hizo
 * @author Benjamin
 */
public class EmpleadoMeseroDTO {
    
    // --- Atributos heredados del Empleado general ---
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    
    // El puesto por defecto debería ser "Mesero", pero lo incluimos por buenas prácticas
    private String puesto; 
    
    // (Opcional: Si los meseros no necesitan ver su sueldo en pantalla, 
    // puedes omitir el atributo 'sueldo' aquí por seguridad).

    // --- Atributo específico del Mesero ---
    private Long codigoMesero;

    /**
     * Constructor vacío requerido para inicializaciones rápidas
     */
    public EmpleadoMeseroDTO() {
    }

    /**
     * Constructor completo para poblar el DTO desde el BO
     */
    public EmpleadoMeseroDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String puesto, Long codigoMesero) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.puesto = puesto;
        this.codigoMesero = codigoMesero;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Long getCodigoMesero() {
        return codigoMesero;
    }

    public void setCodigoMesero(Long codigoMesero) {
        this.codigoMesero = codigoMesero;
    }

    /**
     * Método de conveniencia muy útil para las interfaces gráficas.
     * En lugar de concatenar a mano en cada pantalla, llamas a este método.
     * Ejemplo para un ticket: "Le atendió: " + mesero.getNombreCompleto()
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidoPaterno + 
              (this.apellidoMaterno != null ? " " + this.apellidoMaterno : "");
    }
}
