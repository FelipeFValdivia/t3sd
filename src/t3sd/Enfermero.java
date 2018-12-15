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
    
    private Long id;
    private String nombre;
    private String apellido;
    private Long experiencia;
    private Long estudios;
    
    public Enfermero(Long id, String nombre, String apellido, Long experiencia, Long estudios){
        id = id;
        nombre = nombre;
        apellido = apellido;
        experiencia = experiencia;
        estudios = estudios;
        
    }

}
