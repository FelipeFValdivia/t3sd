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
public class Personal {
    
    private int id;
    private String nombre;
    private String apellido;
    private int estudios;
    private int experiencia;
    private String especialidad;
    
    public Personal(int id, String nombre, String apellido, int estudios, int experiencia, int tipo){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.experiencia = experiencia;
        this.estudios = estudios;
        switch(tipo){
            case 1:
                this.especialidad = "Doctor";
                break;
            case 2:
                this.especialidad = "Enfermero";
                break;
            case 3:
                this.especialidad = "Paramedico";
                break;
            default:
                break;
        }
    }
}
