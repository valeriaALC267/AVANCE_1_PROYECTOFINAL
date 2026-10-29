
package primer_avance_poo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmpleadoController 
{
    public ArrayList<Empleado> ListaEmpleados;
    public EmpleadoController()
    {
        ListaEmpleados = new ArrayList<>();
    }
    public Empleado BuscarEmpleado(String doc)
    {
        for(Empleado e : ListaEmpleados)
        {
            if(doc.equalsIgnoreCase(e.getNro_doc()))
            {
                return e;
            }
        }
        return null;
    }
    public void AñadirEmpleado(Empleado e)
    {
        if(BuscarEmpleado(e.getNro_doc())==null)
        {
            ListaEmpleados.add(e);
        }
    }
    public void ExportarListaEmpleadosTXT(String nombre_archivo) throws IOException
    {
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre_archivo)))
        {
            escritor.println("==========================================================================================");
            escritor.println("                              📋 REPORTE GENERAL DE EMPLEADOS 📋                          ");
            escritor.println("==========================================================================================");
            
            if(ListaEmpleados.isEmpty())
            {
                escritor.println("NO HAY NINGUN EMPLEADO REGISTRADO");
            }
            else
            {
                //por polimorfismo ponemos esto, las clases hijas heredan de una clase abstracta empleado
                for(Empleado e : ListaEmpleados)
                {
                    
                        escritor.println(e.MostrarDatosPersonales());
                        escritor.println(e.MostrarDatosLaborales());
                        if(e instanceof Gerente)
                        {
                            escritor.println(((Gerente) e).MostrarListaSubordinados());
                        }
                        if(e instanceof Administrativo)
                        {
                            escritor.println(((Administrativo) e).MostrarListaSubordinados());
                        }
                    
                   escritor.println("--------------------------------------------------------------------------------------");
                }
            }
            escritor.println("==========================================================================================");
            escritor.println("Generado automáticamente por el Sistema de Hotel 🏨✨");
        }
    }
}