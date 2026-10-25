
package primer_avance_poo;

public class Administrativo extends Empleado
{
    //numero de telefono de su escritorio para que pueda recibir llamadas a directamente
    private String nro_anexo;

    public Administrativo() {
    }

    public String getNro_anexo() {
        return nro_anexo;
    }

    public void setNro_anexo(String nro_anexo) 
    {
        if(nro_anexo == null || nro_anexo.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        if(nro_anexo.length()!=6)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo con informacion valida. Intente nuevamente:");
        }
        this.nro_anexo = nro_anexo;
    }
    
    @Override
    public double CalcularSueldo() 
    {
        return this.sueldo;
    }
    
    @Override
    public String MostrarDatosLaborales() 
    {
        return "DOC ADMINISTRATIVO: "+this.nro_doc+" | DEPARTAMENTO: "+this.departamento.getNombre_departamento()+" | SUELDO: S/"+CalcularSueldo()+
                " | DOC JEFE: "+this.jefe.getNro_doc()
                + "| NRO ANEXO: "+this.nro_anexo;
    }
}