/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 * Data transfer object para mostrar ingredientes
 * @author Benjamin
 */
public class IngredienteDTO {
    
    private Long id;
    private String nombre;
    private String unidadMedida;
    private Double cantidadActual;
    private String rutaImagen;

    public IngredienteDTO() {}

    public IngredienteDTO(Long id, String nombre, String unidadMedida, Double cantidadActual, String rutaImagen) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.cantidadActual = cantidadActual;
        this.rutaImagen = rutaImagen;
    }

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public Double getCantidadActual() { return cantidadActual; }
    public void setCantidadActual(Double cantidadActual) { this.cantidadActual = cantidadActual; }

    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
