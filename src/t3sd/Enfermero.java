/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t3sd;

/**
 *
 * @author felipefvaldivia
 */
public class Enfermero {
    
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final Long experiencia;
    private final Long estudios;
    
    public Enfermero(Long id, String nombre, String apellido, Long experiencia, Long estudios){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.experiencia = experiencia;
        this.estudios = estudios;
        
    }

}
