
package primer_avance_poo;

import java.util.ArrayList;

public abstract class Empleado extends Persona
{
    protected double sueldo;
    protected Empleado jefe;
    protected ArrayList<Empleado> ListaSubordinados;

    public Empleado() 
    {
        this.ListaSubordinados = new ArrayList<>();
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) 
    {
        if(sueldo<=0)
        {
            throw new IllegalArgumentException("Error: Debe ingresar una cantidad de sueldo valida en este campo. Intente nuevamente:");
        }
        this.sueldo = sueldo;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) 
    { //para que un empleado no se tenga de jefe a si mismo
        if(jefe == this)
        {
            throw new IllegalArgumentException("Error: Un empleado no puede ser su propio jefe. Intente nuevamente:");
        }
        this.jefe = jefe;
    }

    public ArrayList<Empleado> getListaSubordinados() {
        return ListaSubordinados;
    }

    public void setListaSubordinados(ArrayList<Empleado> ListaSubordinados) {
        this.ListaSubordinados = ListaSubordinados;
    }
    public void AgregarSubordinado(Empleado e)
    {
        //para que un empleado no sea su propio subordinado
        if(e == null)
        {
           throw new IllegalArgumentException("Error: Debe llenar este campo con informacion valida. Intente nuevamente:");
        }
        if(e == this)
        {
           throw new IllegalArgumentException("Error: Un empleado no puede ser su propio subordinado. Intente nuevamente:");
        }
        ListaSubordinados.add(e);
    }
}