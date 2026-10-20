
package primer_avance_poo;

import java.time.LocalDate;
import java.util.UUID;

public class Reserva 
{
    private String cod_reserva;
    //el codigo ya no tiene setter pq se lo asignamos aleatoriamente
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Huesped cliente;
    private Habitacion habitacion;
    public Reserva()
    {
        //UUID genera lgo como "550e8400-e29b-41d4-a716-446655440000"
        String uuidCompleto = UUID.randomUUID().toString();
        
        //cortamos los 6 primeros digitos y lo pasamos a mayusculas:
        String codigo = uuidCompleto.substring(0,6).toUpperCase();
        this.cod_reserva = "HTL-"+codigo;
    }

    public String getCod_reserva() {
        return cod_reserva;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio)
    {
        if(fecha_inicio == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) 
    {
        if(fecha_fin == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        if(this.fecha_inicio!=null && fecha_fin.isBefore(this.fecha_inicio))
        {
            throw new IllegalArgumentException("Error: La fecha de salida no puede ser antes que la de entrada. Intente nuevamente:");
        }
        this.fecha_fin = fecha_fin;
    }

    public Huesped getCliente() {
        return cliente;
    }

    public void setCliente(Huesped cliente) 
    {
        if(cliente == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion)
    {
        if(habitacion == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.habitacion = habitacion;
    }
    
    public String MostrarDatosReserva()
    {
        return "CODIGO RESERVA: "+this.cod_reserva+" | FECHA INICIO: "+this.fecha_inicio+" | FECHA FIN: "+this.fecha_fin+" | DOC HUESPED: "+this.cliente.getNro_doc()
                +"NRO HABITACION: "+this.habitacion.getNro_habitacion();
    }
}