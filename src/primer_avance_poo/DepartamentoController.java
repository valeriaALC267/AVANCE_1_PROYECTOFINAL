
package primer_avance_poo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
public class DepartamentoController
{
    public ArrayList<Departamento>ListaDepartamentos;
    public DepartamentoController()
    {
        ListaDepartamentos = new ArrayList<>();
    }
    public Departamento BuscarDepartamento(String cod)
    {
        for(Departamento d : ListaDepartamentos)
        {
            if(cod.equalsIgnoreCase(d.getId_departamento()))
            {
                return d;
            }
        }
        return null;
    }
    public void AñadirDepartamento(Departamento d)
    {
        if(BuscarDepartamento(d.getId_departamento())==null)
        {
            ListaDepartamentos.add(d);
        }
    }
    public void ExportarListaDepartamentosTXT(String nombre_archivo) throws IOException
    {
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre_archivo)))
        {
            escritor.println("==========================================================================================");
            escritor.println("                            📋 REPORTE GENERAL DE DEPARTAMENTOS 📋                          ");
            escritor.println("==========================================================================================");
            
            if(ListaDepartamentos.isEmpty())
            {
                escritor.println("NO HAY NINGUN DEPARTAMENTO REGISTRADO");
            }
            else
            {
                for(Departamento d : ListaDepartamentos)
                {
                    escritor.println(d.MostrarDatosDepartamento());
                    escritor.println("--------------------------------------------------------------------------------------");
                }       
            }
            escritor.println("==========================================================================================");
            escritor.println("Generado automáticamente por el Sistema de Hotel 🏨✨");
        }
    }
}