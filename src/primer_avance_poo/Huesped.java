
package primer_avance_poo;

import java.util.ArrayList;

public class Huesped extends Persona
{
    private String nacionalidad;
    private String preferencias;
    private Boolean esFrecuente;
    private ArrayList<Reserva>HisorialReservas;

    public Huesped(Boolean esFrecuente, ArrayList<Reserva> HisorialReservas) 
    {
        this.esFrecuente = false; //no inicia como visitante frecuente del hotel
        this.HisorialReservas = new ArrayList<>(); //se crea una lista de reservas al momento de crear un huesped
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad)
    {
        if(nacionalidad == null || nacionalidad.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.nacionalidad = nacionalidad;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) 
    {
        this.preferencias = preferencias;
    }

    public Boolean getEsFrecuente() {
        return esFrecuente;
    }

    public void setEsFrecuente(Boolean esFrecuente) 
    {
        this.esFrecuente = esFrecuente;
    }

    public ArrayList<Reserva> getHisorialReservas()
    {
        return HisorialReservas;
    }

    public void setHisorialReservas(ArrayList<Reserva> HisorialReservas) {
        this.HisorialReservas = HisorialReservas;
    }   
}