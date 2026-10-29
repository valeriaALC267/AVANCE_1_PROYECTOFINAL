
package primer_avance_poo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Reserva 
{
    private String cod_reserva;
    //el codigo ya no tiene setter pq se lo asignamos aleatoriamente
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Huesped cliente;
    private Recepcionista recepcionista_a_cargo;
    private ArrayList<Habitacion>ListaHabitaciones;
    public Reserva()
    {
        //UUID genera lgo como "550e8400-e29b-41d4-a716-446655440000"
        String uuidCompleto = UUID.randomUUID().toString();
        
        //cortamos los 6 primeros digitos y lo pasamos a mayusculas:
        String codigo = uuidCompleto.substring(0,6).toUpperCase();
        this.cod_reserva = "HTL-"+codigo;
        
        ListaHabitaciones = new ArrayList<>();
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
        if(fecha_inicio.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException("Error: Debe llenar esta campo con informacion valida. Intente nuevamente:");
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

    public Recepcionista getRecepcionista_a_cargo() {
        return recepcionista_a_cargo;
    }

    public void setRecepcionista_a_cargo(Recepcionista recepcionista_a_cargo) 
    {
        if(recepcionista_a_cargo == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.recepcionista_a_cargo = recepcionista_a_cargo;
    }

    public ArrayList<Habitacion> getListaHabitaciones() {
        return ListaHabitaciones;
    }

    public void AñadirHabitacion(Habitacion h)
    {
        if(h == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        //validaciones para ver si sí se úede ocupar la habitacion
        if ("mantenimiento".equalsIgnoreCase(h.getEstado()) || "ocupada".equalsIgnoreCase(h.getEstado())) 
        {
            throw new IllegalArgumentException("Error: La habitación seleccionada no está disponible en este momento.");
        }
        ListaHabitaciones.add(h);
    }
    
    public String MostrarDatosReserva()
    {
        return "CODIGO RESERVA: "+this.cod_reserva+" | FECHA INICIO: "+this.fecha_inicio.toString()+" | FECHA FIN: "+this.fecha_fin.toString()
                +" | DOC HUESPED: "+this.cliente.getNro_doc()
                +" | DOC RECEPCIONISTA: "+this.recepcionista_a_cargo.getNro_doc();
    }
    public String MostrarListaHabitaciones()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------LISTA DE HABITACIONES: RESERVA "+this.cod_reserva+"--------------------------------\n");
        if(ListaHabitaciones.isEmpty())
        {
            sb.append("LA RESERVA NO TIENE HABITACIONES AUN");
        }
        else
        {
            for(Habitacion h : ListaHabitaciones)
            {
                sb.append(h.MostrarDatosHabitacion());
            }
        }
        return sb.toString();
    }
}