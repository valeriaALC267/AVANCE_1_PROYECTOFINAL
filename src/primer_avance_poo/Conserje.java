
package primer_avance_poo;

public class Conserje extends Empleado
{
    private String especialidad;

    public Conserje() {
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) 
    {
        if(especialidad == null || especialidad.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.especialidad = especialidad;
    }

    @Override
    public double CalcularSueldo() 
    {
        return this.sueldo;
    }

    @Override
    public String MostrarDatosLaborales()
    {
        
      
        return "DOC CONSERJE: "+this.nro_doc+" | DEPARTAMENTO: "+this.departamento.getNombre_departamento()+" | SUELDO: S/"+CalcularSueldo()+" | ESPECIALIDAD: "+this.especialidad
                +" | DOC JEFE: "+this.jefe.getNro_doc();
    }    
}