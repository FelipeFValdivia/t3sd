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
public class Paramedico {
    private Long id;
    private String nombre;
    private String apellido;
    private Long estudios;
    private Long experiencia;
    
    
    public Paramedico(Long id, String nombre, String apellido, Long experiencia, Long estudios){
        id = id;
        nombre = nombre;
        apellido = apellido;
        experiencia = experiencia;
        estudios = estudios;
        
    }

            
}
