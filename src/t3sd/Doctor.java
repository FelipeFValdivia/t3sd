/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t3sd;

/**
 *
 * @author usuario
 */
public class Doctor {
    private Long id;
    private String nombre;
    private String apellido;
    private Long estudios;
    private Long experiencia;
    
    public Doctor(Long id, String nombre, String apellido, Long estudios, Long experiencia){
        id = id;
        nombre = nombre;
        apellido = apellido;
        estudios = estudios;
        experiencia = experiencia;
    }

    Doctor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void lecturaPaciente(){
    }
    
    public void coordinarAccesos(){
    }
    
}
