
package primer_avance_poo;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PRIMER_AVANCE_POO {

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String r;
        int op;
        DepartamentoController dep_con = new DepartamentoController();
        HabitacionController hab_con = new HabitacionController();
        HuespedController huesp_con = new HuespedController();
        EmpleadoController emp_con = new EmpleadoController();
        ReservaController resv_con = new ReservaController();
        do 
        {            
            System.out.println("==========================================================================================");
            System.out.println("                            📋 REGISTRO HOTEL 'INDIGO LOFT' 📋                          ");
            System.out.println("==========================================================================================");
            System.out.println("SELECCIONE UNA OPCION:");
            System.out.println("(1) REGISTRAR EMPLEADO");
            System.out.println("(2) REGISTRAR DEPARTAMENTO");
            System.out.println("(3) REGISTRAR HABITACION");
            System.out.println("(4) REGISTRAR HUESPED");
            System.out.println("(5) REALIZAR RESERVA");
            System.out.println("(6) LISTA DE CLIENTES");
            System.out.println("(7) LISTA DE EMPLEADOS");
            System.out.println("(8) LISTA DE DEPARTAMENTOS");
            System.out.println("(9) LISTA DE HABITACIONES");
            System.out.println("(10) LISTA DE RESERVAS");
            System.out.println("(11) ACTUALIZAR JEFE DE ALGUN EMPLEADO");
            System.out.println("(12) ACTUALIZAR GERENTE DE DEPARTAMENTO");
            System.out.println("(13) SALIR");
            while (true)
            {                
                try 
                {
                    op = sc.nextInt();
                    sc.nextLine();
                    break;
                }
                catch (InputMismatchException e) 
                {
                    System.out.println("Error: Ingrese una opcion valida. Intente nuevamente:");
                    sc.nextLine();
                }
            }
            
            switch (op) 
            {
                case 1:
                    Departamento dep = null;

                    // 1. buscar departamento
                   while (true)
                   {
                      System.out.println("INGRESE EL ID DEL DEPARTAMENTO AL QUE PERTENECERA:");
                      String idd = sc.nextLine().trim();

                       if (idd.isEmpty()) 
                       {
                          System.out.println("Error: El ID no puede estar vacio.");
                          continue;
                       }

                       dep = dep_con.BuscarDepartamento(idd);

                       if (dep != null) 
                       {
                           break;
                       }

                       System.out.println("Error: No existe un departamento con ese ID.");
                       System.out.println("DESEA INTENTAR NUEVAMENTE? (S/N):");
                       String respuesta = sc.nextLine().trim();

                      if (respuesta.equalsIgnoreCase("n")) 
                      {
                        System.out.println("Registro de empleado cancelado.");
                        break;
                      }
                   }

                  // Si no se encontro departamento, regresamos al menu.
                   if (dep == null)
                   {
                      break;
                   }

                  // 2. SELECCIONAR EL TIPO DE EMPLEADO
                     int op_empleado;
                     Empleado emp = null;

                 while (true)
                 {
                        System.out.println("SELECCIONE EL TIPO DE EMPLEADO A REGISTRAR:");
                        System.out.println("(1) GERENTE");
                        System.out.println("(2) ADMINISTRATIVO");
                        System.out.println("(3) RECEPCIONISTA");
                        System.out.println("(4) CONSERJE");

                      try 
                      {
                        op_empleado = sc.nextInt();
                         sc.nextLine();
                        if (op_empleado >= 1 && op_empleado <= 4) 
                         {
                           break;
                         }
                         System.out.println("Error: Seleccione una opcion entre 1 y 4.");
                       } catch (InputMismatchException e) 
                       {
                          System.out.println("Error: Debe ingresar un numero.");
                           sc.nextLine();
                       }
                 }

              switch (op_empleado) 
              {
                 case 1:
                    emp = new Gerente();
                    break;
                 case 2:
                    emp = new Administrativo();
                    break;
                case 3:
                    emp = new Recepcionista();
                    break;
                case 4:
                    emp = new Conserje();
                    break;
             }

               // 3. REGISTRAR TIPO Y NUMERO DE DOCUMENTO
            while (true)
            {
               try 
               {
                  System.out.println("TIPO DE DOCUMENTO (DNI, CE o PAS):");
                  emp.setTipo_doc(sc.nextLine().trim());
                  break;
               } catch (IllegalArgumentException e) 
               {
                  System.out.println(e.getMessage());
               }
            }

             while (true) 
             {
                try 
                {
                    System.out.println("NUMERO DE DOCUMENTO:");
                    String n_doc = sc.nextLine().trim();

                    emp.setNro_doc(n_doc);

                    Empleado existente = emp_con.BuscarEmpleado(n_doc);

                    if (existente != null)
                    {
                       System.out.println("Error: Ya existe un empleado con ese documento.");
                       continue;
                    }
                  break;

                } catch (IllegalArgumentException e)
                {
                  System.out.println(e.getMessage());
                }
             }

            // 4. REGISTRAR LOS DATOS ESPECIFICOS DEL EMPLEADO
            if (emp instanceof Gerente)
            {
                while (true) 
                {
                  try 
                  {
                     System.out.println("BONO DE GESTION:");
                      ((Gerente) emp).setBono_gestion(sc.nextDouble());
                      sc.nextLine();
                   break;
                  } catch (InputMismatchException e) 
                  {
                     System.out.println("Error: Ingrese un valor numerico.");
                      sc.nextLine();
                  } catch (IllegalArgumentException e) 
                  {
                     System.out.println(e.getMessage());
                  }
                }
            }

           else if (emp instanceof Administrativo)
           {
                while (true)
                {
                   try 
                   {
                     System.out.println("NRO DE ANEXO (6 DIGITOS):");
                    ((Administrativo) emp).setNro_anexo(sc.nextLine());
                      break;
                   } catch (IllegalArgumentException e) 
                   {
                      System.out.println(e.getMessage());
                   }
               }
           }

            else if (emp instanceof Recepcionista) 
            {
                while (true) 
                {
                   try 
                   {
                     System.out.println("TURNO:");
                     ((Recepcionista) emp).setTurno(sc.nextLine());
                     break;

                   } catch (IllegalArgumentException e) 
                   {
                     System.out.println(e.getMessage());
                   }
                }
            }

            else if (emp instanceof Conserje) 
            {
                while (true) 
                {
                   try 
                   {
                     System.out.println("ESPECIALIDAD:");
                     ((Conserje) emp).setEspecialidad(sc.nextLine());
                     break;
                   } catch (IllegalArgumentException e) 
                   {
                       System.out.println(e.getMessage());
                   }
                }
            }

             // 5. DATOS PERSONALES
            while (true) 
            {
              try 
              {
                 System.out.println("NOMBRE:");
                 emp.setNombre(sc.nextLine());
                 break;
              } catch (IllegalArgumentException e) 
              {
                System.out.println(e.getMessage());
              }
           }

           while (true)
           {
               try
               {
                  System.out.println("APELLIDO PAT:");
                  emp.setApellido_pat(sc.nextLine());
                  break;
               } catch (IllegalArgumentException e) 
               {
                  System.out.println(e.getMessage());
               }
            }

           while (true) 
           {
                try 
                {
                   System.out.println("APELLIDO MAT:");
                   emp.setApellido_mat(sc.nextLine());
                   break;
                } catch (IllegalArgumentException e) 
                {
                   System.out.println(e.getMessage());
                }
            }

           while (true) 
           {
                try 
                {
                   System.out.println("TELEFONO:");
                   emp.setTelefono(sc.nextLine());
                   break;
                } catch (IllegalArgumentException e)
                {
                  System.out.println(e.getMessage());
                }
            }

            while (true) 
            {
               try 
               {
                  System.out.println("CORREO:");
                  emp.setCorreo(sc.nextLine());
                  break;
               } catch (IllegalArgumentException e) 
               {
                  System.out.println(e.getMessage());
               }
            }

            while (true) 
            {
               try
               {
                  System.out.println("SUELDO:");
                  emp.setSueldo(sc.nextDouble());
                  sc.nextLine();
                  break;
               } catch (InputMismatchException e)
               {
                  System.out.println("Error: Ingrese un sueldo numerico.");
                  sc.nextLine();
               } catch (IllegalArgumentException e) 
               {
                  System.out.println(e.getMessage());
              }
            }
            
            emp.setDepartamento(dep);

            // 8. ASIGNAR JEFE
            if (!(emp instanceof Gerente)) 
            {

                if (emp_con.EstaVacia()) 
                {
                    System.out.println("Aviso: No existen empleados que puedan ser jefes.");
                    emp.setJefe(null);
                } 
                else 
                {
                   while (true) 
                   {
                       System.out.println("DESEA AGREGAR UN JEFE? (S/N)");
                       String rpt_jefe = sc.nextLine().trim();

                       if (rpt_jefe.equalsIgnoreCase("s")) 
                       {
                        System.out.println("NRO DE DOCUMENTO DEL JEFE:");
                        String doc = sc.nextLine().trim();

                        Empleado jefe = emp_con.BuscarEmpleado(doc);

                         if (jefe != null) 
                         {
                             try 
                             {
                                emp.setJefe(jefe);
                                jefe.AgregarSubordinado(emp);
                                System.out.println("JEFE ASIGNADO CON EXITO.");
                                break;
                              } catch (IllegalArgumentException e) 
                              {
                                 emp.setJefe(null);
                                 System.out.println(e.getMessage());
                              }

                         } else 
                         {
                             System.out.println("No se encontro un empleado con ese documento.");
                         }
                        } 
                       else if (rpt_jefe.equalsIgnoreCase("n")) 
                       {
                         emp.setJefe(null);
                          System.out.println("CAMPO JEFE NULO.");
                          break;
                       } 
                       else 
                       {
                         System.out.println("Error: Responda S o N.");
                       }
                    }
                }
            }
            else 
            {
               emp.setJefe(null);
            }
            // 9. AGREGAR EMPLEADO
                    try 
                    {
                        emp_con.AñadirEmpleado(emp);
                        System.out.println("EMPLEADO AGREGADO EXITOSAMENTE!");
                    } catch (IllegalArgumentException e) 
                    {
                        System.out.println(e.getMessage());
                    }
             break;
             
                case 2:
                    Departamento d = new Departamento();
                    
                    System.out.println("ID ASIGNADO PARA ESTE DEPARTAMENTO: "+d.getId_departamento());
                    System.out.println("NRO DOC DEL GERENTE QUE ESTARA A CARGO:");
                    String doc = sc.nextLine();
                    Empleado gerente = emp_con.BuscarEmpleado(doc);
                    if(gerente!=null)
                    {
                        if(gerente instanceof Gerente)
                        {
                            gerente.setDepartamento(d);
                            d.setGerente_a_cargo((Gerente)gerente);
                        }
                        else
                        {
                            System.out.println("Error: Este empleado no puede estar a cargo de este departamento porque no es gerente");
                            break;
                        }
                    }
                    else
                    {
                        System.out.println("Aviso: Gerente en nulo momentaneamente");
                        d.setGerente_a_cargo(null);
                    }
                    while (true) 
                    {                        
                        try 
                        {
                            System.out.println("NOMBRE DEL DEPARTAMENTO: ");
                            d.setNombre_departamento(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    { 
                        try 
                        {
                           System.out.println("PRESUPUESTO MENSUAL:");
                           d.setPresupuesto_mensual(sc.nextDouble());
                           sc.nextLine();
                           break;
                        } catch(InputMismatchException e)
                        {
                          System.out.println("Error: Debe ingresar un presupuesto numerico.");
                          sc.nextLine();
                        } catch(IllegalArgumentException e)
                        {
                         System.out.println(e.getMessage());
                        }
                    }
                     try 
                         {
                           dep_con.AñadirDepartamento(d);
                            System.out.println("DEPARTAMENTO AGREGADO EXITOSAMENTE!");   
                        } catch (IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    break;
                case 3:
                    int nh;
                    try 
                    {  
                         System.out.println("INGRESE EL NRO DE HABITACION:");
                         nh = sc.nextInt();
                         sc.nextLine();
                    }
                    catch(InputMismatchException it)
                    {
                        System.out.println("Error: Debe ingresar un valor numerico"); 
                        sc.nextLine();
                        break;
                    }
                    Habitacion h = hab_con.BuscarHabitacion(nh);
                    if(h!=null)
                    {
                        System.out.println("Error: Ya existe un ahabitacion con este numero");
                        break;
                    }
                    Habitacion hab = new Habitacion();
                    hab.setNro_habitacion(nh);
                    
                    while(true)
                    {
                        try 
                        {
                            System.out.println("ESTADO:");
                            hab.setEstado(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("PRECIO POR NOCHE:");
                            hab.setPrecio_noche(sc.nextDouble());
                            sc.nextLine();
                            break;
                        } catch (IllegalArgumentException | InputMismatchException e) 
                        {
                            System.out.println(e.getMessage());
                            sc.nextLine();
                        }
                    }
                     
                    hab_con.AñadirHabitacion(hab);
                    System.out.println("HABITACION AGREGADA EXITOSAMENTE!");
                    break;
                case 4:
                    Huesped hsp = new Huesped();

                   while(true)
                    {
                       try
                       {
                           System.out.println("TIPO DOC (DNI, CE o PAS):");
                           hsp.setTipo_doc(sc.nextLine().trim());
                            break;
                       }
                       catch (IllegalArgumentException e)
                       {
                         System.out.println(e.getMessage());
                       }
                    }

                   while(true)
                     {
                         try
                         {
                            System.out.println("INGRESE EL NRO DE DOC:");
                            String doc_h = sc.nextLine().trim();
                            hsp.setNro_doc(doc_h);
                            Huesped existente = huesp_con.Buscarhuesped(doc_h);
                            if(existente != null)
                              {
                                 System.out.println("Error: Ya existe un huesped con este documento.");
                                  continue;
                              }
                            break;
                          }
                         catch (IllegalArgumentException e)
                          {
                              System.out.println(e.getMessage());
                           }
                     }

                    while(true)
                    {
                        try 
                        {
                            System.out.println("NOMBRE:");
                            hsp.setNombre(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("APELLIDO PAT:");
                            hsp.setApellido_pat(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("APELLIDO MAT:");
                            hsp.setApellido_mat(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("TIPO DOC:");
                            hsp.setTipo_doc(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("TELEFONO:");
                            hsp.setTelefono(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("CORREO:");
                            hsp.setCorreo(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("NACIONALIDAD:");
                            hsp.setNacionalidad(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("PREFERENCIAS:");
                            hsp.setPreferencias(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("ES UN CLIENTE FRECUENTE? (MAS DE 3 RESERVAS) S/N:");
                            String frecuencia = sc.nextLine();
                            if(frecuencia.equalsIgnoreCase("s"))
                            {
                                hsp.setEsFrecuente(true);
                                break;
                            }
                            else if(frecuencia.equalsIgnoreCase("n"))
                            {
                                hsp.setEsFrecuente(false);
                                break;
                            }
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    huesp_con.AñadirHuesped(hsp);
                    System.out.println("CLIENTE AGREGADO EXITOSAMENTE!");
                   break;
                case 5:
                    if(huesp_con.EstaVacia() || hab_con.EstaVacia() || emp_con.EstaVacia())
                    {
                        System.out.println("Error: Debe haber al menos un cliente, habitacion y empleado registrados");
                        break;
                    }
                    Reserva res = new Reserva();
                    System.out.println("INGRESE EL NRO DOC DEL HUESPED");
                    String docum = sc.nextLine();
                    Huesped hpd = huesp_con.Buscarhuesped(docum);
                    if(hpd == null)
                    {
                        System.out.println("Error: No existe un huesped registrado con este nro de documento");
                        break;
                    }
                    res.setCliente(hpd);
                    System.out.println("INGRESE EL NRO DOC DEL RECEPCIONISTA:");
                    String doc_r = sc.nextLine();
                    Empleado ep = emp_con.BuscarEmpleado(doc_r);
                    if(ep==null)
                    {
                        System.out.println("Error: No existe ningun empleado registrado con este nro de documento");
                        break;
                    }
                    else
                    {
                        if(ep instanceof Recepcionista)
                        {
                            res.setRecepcionista_a_cargo((Recepcionista)ep);
                        }
                        else
                        {
                            System.out.println("Error: Este empleado no es un recepcionista");
                            break;
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("FECHA INICIO (AAAA-MM-DD):");
                            res.setFecha_inicio(LocalDate.parse(sc.nextLine()));
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                        catch(DateTimeException dt)
                        {
                            System.out.println("Error: Debe ingresar un formato de fecha valido. Intente nuevamente:");
                        }
                    }
                    while(true)
                    {
                        try 
                        {
                            System.out.println("FECHA FIN (AAAA-MM-DD):");
                            res.setFecha_fin(LocalDate.parse(sc.nextLine()));
                            break;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                        }
                        catch(DateTimeException dt)
                        {
                            System.out.println("Error: Debe ingresar un formato de fecha valido. Intente nuevamente:");
                        }
                    }
                    
                    //para añadir habitaciones a la reserva (la reserva tiene una lista de habitaciones)
                    
                    boolean agregarMas = true;
                    while(agregarMas)
                    {
                        try 
                        {
                            System.out.println("INGRESE EL NRO DE HABITACION:");
                            int nr_h= sc.nextInt();
                            sc.nextLine();
                            
                            Habitacion habt = hab_con.BuscarHabitacion(nr_h);
                            if(habt == null)
                            {
                                System.out.println("Error: No existe ninguna habotacion con ese numero");
                            }
                            else
                            {
                                res.AñadirHabitacion(habt);
                                habt.setEstado("ocupada");
                                System.out.println("HABITACION "+habt.getNro_habitacion()+" AÑADIDA A LA RESERVA CON EXITO!");
                            }
                            System.out.println("DESEA AÑADIR OTRA HABOTACION? S/N");
                            String r_hab = sc.nextLine();
                            if(!r_hab.equalsIgnoreCase("s"))
                            {
                                agregarMas = false;
                            }
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println(e.getMessage());
                            sc.nextLine();
                        }
                        catch(InputMismatchException it)
                        {
                            System.out.println("Error: Debe ingresar numeros en este campo");
                        }
                    }
                    resv_con.AñadirReserva(res);
                    hpd.AgregarReserva(res);
                    System.out.println("RESERVA AÑADIDA EXITOSAMENTE!");
                    
                    try
                     {
                       res.getFactura().ExportarFacturaTXT("Factura_" + res.getFactura().getCod_factura() + ".txt");
                       System.out.println("FACTURA GENERADA EXITOSAMENTE!");
                     }
                    catch(IOException e)
                     {
                         System.out.println("Error al exportar la factura: " + e.getMessage());
                     }
                    break;
                case 6:
                    try 
                    {
                        huesp_con.ExportarListaHuespedesTXT("reporte_huespedes.txt");
                        System.out.println("REGISTRO GUARDADO EXITOSAMENTE!");
                    } catch (IOException e) 
                    {
                        System.out.println("Error: Algo ocurrio al momento de intentar crear el registro. Detalles: "+e.getMessage());
                    }
                    break;
                case 7:
                    try 
                    {
                        emp_con.ExportarListaEmpleadosTXT("reporte_empleados.txt");
                        System.out.println("REGISTRO GUARDADO EXITOSAMENTE!");
                    } catch (IOException e) 
                    {
                        System.out.println("Error: Algo ocurrio al momento de intentar crear el registro. Detalles: "+e.getMessage());
                    }
                    break;
                case 8:
                    try 
                    {
                        dep_con.ExportarListaDepartamentosTXT("reporte_departamentos.txt");
                        System.out.println("REGISTRO GUARDADO EXITOSAMENTE!");
                    } catch (IOException e) 
                    {
                        System.out.println("Error: Algo ocurrio al momento de intentar crear el registro. Detalles: "+e.getMessage());
                    }
                    break;
                case 9:
                    try 
                    {
                        hab_con.ExportarListaHabitacionesTXT("reporte_habitaciones.txt");
                        System.out.println("REGISTRO GUARDADO EXITOSAMENTE!");
                    } catch (IOException e) 
                    {
                        System.out.println("Error: Algo ocurrio al momento de intentar crear el registro. Detalles: "+e.getMessage());
                    }
                    break;
                case 10:
                    try 
                    {
                        resv_con.ExportarListaReservasTXT("reporte_reservas.txt");
                        System.out.println("REGISTRO GUARDADO EXITOSAMENTE!");
                    } catch (IOException e) 
                    {
                        System.out.println("Error: Algo ocurrio al momento de intentar crear el registro. Detalles: "+e.getMessage());
                    }
                    break;
                case 11:
                    System.out.println("INGRESE EL NRO DE DOCUMENTO DEL EMPLEADO:");
                    String dc = sc.nextLine();
                    Empleado e = emp_con.BuscarEmpleado(dc);
                    if(e == null)
                    {
                        System.out.println("Error: No se encontró ningun empleado con este nro de documento");
                        break;
                    }
                    else
                    {
                        System.out.println("INGRESE EL NRO DOC DEL JEFE QUE DESEA ASIGNAR:");
                        String doc_jefe = sc.nextLine();
                        Empleado epd = emp_con.BuscarEmpleado(doc_jefe);
                        if(epd !=null)
                        {
                            //validamos que sea uno de los empleados que pueden ser jefes, y añadimos el jefe y a este le agregamos su subordinado
                            if(epd instanceof Gerente)
                            {
                                e.setJefe(epd);
                                epd.AgregarSubordinado(e);
                                System.out.println("JEFE ASIGNADO EXITOSAMENTE!");
                            }
                            if(epd instanceof Administrativo)
                            {
                                e.setJefe(epd);
                                epd.AgregarSubordinado(e);
                                System.out.println("JEFE ASIGNADO EXITOSAMENTE!");
                            }
                        }
                        else
                        {
                            System.out.println("Error: No se tiene ningun empleado con este nro de documento");
                            break;
                        }
                    }
                    break;
                case 12:
                         System.out.println("INGRESE EL ID DEL DEPARTAMENTO:");
                         String id_dep = sc.nextLine();

                         Departamento dpt = dep_con.BuscarDepartamento(id_dep);

                        if(dpt == null)
                        {
                           System.out.println("Error: No existe un departamento con ese ID.");
                            break;
                        }

                        if(dpt.getGerente_a_cargo() == null)
                        {
                          System.out.println("Aviso: Este departamento actualmente no tiene gerente asignado.");
                        }
                        else
                        {
                            System.out.println("GERENTE ACTUAL: " + dpt.getGerente_a_cargo().getNro_doc());

                            System.out.println("INGRESE EL NRO DOC DEL GERENTE ACTUAL:");
                             String doc_actual = sc.nextLine();

                             if(!dpt.getGerente_a_cargo().getNro_doc().equalsIgnoreCase(doc_actual))
                                 {
                                   System.out.println("Error: El documento no corresponde al gerente actual.");
                                    break;
                                  }
                        }

                       System.out.println("INGRESE EL NRO DOC DEL NUEVO GERENTE:");
                         String doc_nuevo = sc.nextLine();

                        Empleado g = emp_con.BuscarEmpleado(doc_nuevo);

                        if(g == null)
                        {
                          System.out.println("Error: No existe un empleado con ese documento.");
                           break;
                        }
                        if(!(g instanceof Gerente))
                         {
                              System.out.println("Error: El empleado seleccionado no es un gerente.");
                              break;
                        }
                     g.setDepartamento(dpt);
                     dpt.setGerente_a_cargo((Gerente) g);
                    System.out.println("GERENTE DEL DEPARTAMENTO ACTUALIZADO EXITOSAMENTE!");
               break;
                case 13:
                    System.out.println("==================================");
                    System.out.println("   GRACIAS POR USAR INDIGO LOFT");
                    System.out.println("       HASTA PRONTO!");
                    System.out.println("==================================");
                    break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
            }
            System.out.println("¿DESEA CONTINUAR EN EL MENU? (S/N)");
            r = sc.nextLine();
        } while (!r.equalsIgnoreCase("n"));
    }
}