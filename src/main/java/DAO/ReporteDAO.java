package DAO;

import DAO.ItemDAO;
import DAO.VentaDAO;
import DTO.ItemDTO;
import DTO.ReporteDTO;
import DTO.VentaDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;

import static java.lang.Integer.parseInt;

/**
 * Clase encargada de procesar los datos para el reporte financiero (Ventas de los ultimos 12 meses)
 */
public class ReporteDAO {
    private final ArrayList<VentaDTO> ventas;
    private final HashMap<ItemDTO, Integer> items;
    private final ArrayList<Integer> ventas12Meses;
    private final int ventasUltimoMes;
    private final List<Entry<ItemDTO, Integer>> mostSaledItems;
    private int totalUltimoMes;
    private int totalUltimos12Meses;
    private final ArrayList<VentaDTO> ventasHoy;
    private int totalHoy;

    /**
     * Constructor de la clase que se encarga de procesar todo lo necesario para el reporte llamando a sus metodos privados
     */
    public ReporteDAO(){
        this.totalUltimoMes = 0;
        this.totalUltimos12Meses = 0;
        this.ventas = new VentaDAO().getVentasDB();
        if(this.ventas != null && this.ventas.size() > 0){
            this.items = new HashMap<ItemDTO, Integer>();
            this.ventas12Meses = processSalesLast12Months();
            this.ventasUltimoMes = this.ventas12Meses.get(this.ventas12Meses.size()-1);
            this.mostSaledItems = findMostSaledItems(items, 5);
            this.ventasHoy = getSalesToday();
        }else{
            this.items = null;
            this.ventas12Meses = null;
            this.ventasUltimoMes = 0;
            this.mostSaledItems = null;
            this.ventasHoy = null;
            this.totalHoy = 0;
            this.totalUltimos12Meses = 0;
            this.totalUltimoMes = 0;
        }
    }

    /**
     * Metodo que se encarga de conseguir los items de cada venta procesada
     * @param ventas ArrayList de VentaDTO conteniendo la informacion de todas las ventas
     * @return ArrayList de ItemDTO conteniendo la informacion de los items vendidos
     */
    private ArrayList<ItemDTO> getItemsFromVentas(ArrayList<VentaDTO> ventas){
        ArrayList<ItemDTO> items = new ArrayList<>();
        for (VentaDTO venta : ventas){
            ItemDTO item = new ItemDAO().getItemById(venta.getIdProducto());
            if(!items.contains(item)){
                items.add(item);
            }
            }
        return items;
    }

    /**
     * Obtiene un ArrayList de VentaDTO que contiene todas las ventas
     * realizadas el mismo dia de la revision
     * @return ArrayList de VentasDTO con las ventas del dia
     */
    private ArrayList<VentaDTO> getSalesToday(){
        ArrayList<VentaDTO> ventasHoy = new ArrayList<>();

        int[] fechaActual = getTodaysDate();
        int diaActual = fechaActual[0];
        int mesActual= fechaActual[1];
        int yearActual = fechaActual[2];

        for(VentaDTO venta: ventas){
            int[] fechaVenta = getDate(venta);

            int day = fechaVenta[0];
            int month = fechaVenta[1];
            int year = fechaVenta[2];

            if(year == yearActual && month == mesActual && day == diaActual){
                ventasHoy.add(venta);
                this.totalHoy += venta.getTotal();
            }
        }
        return ventasHoy;
    }

    /**
     * Consigue la fecha del dia de hoy como array de enteros
     * @return array entero con dia, mes y año
     */
    private int[] getTodaysDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String fechaHoy = dtf.format(now);
        String[] fecha = fechaHoy.split("/");
        int year = Integer.parseInt(fecha[0]);
        int month = Integer.parseInt(fecha[1]);
        int day = Integer.parseInt(fecha[2]);
        return new int[]{day, month, year};
    }

    /**
     * Obtiene el item de una venta en especifico
     * @param venta Objeto VentaDTO a procesar
     * @return Objeto ItemDTO con la informacion del item vendido
     */
    private ItemDTO getItemVenta(VentaDTO venta){
       return new ItemDAO().getItemById(venta.getIdProducto());
    }

    /**
     * Procesa las ventas de los ultimos 12 meses, comparando las fechas desde la ultima venta y
     * agrega las que corresponden al periodo
     * @return ArrayList de Integers que representa la cantidad de ventas en los ultimos 12 meses
     */
    private ArrayList<Integer> processSalesLast12Months(){
        ArrayList<Integer> salesLastYear = new ArrayList<>(12);
        for(int i = 0; i < 12; i++){
            salesLastYear.add(0);
        }

        int[] lastDate = getDate(ventas.get(ventas.size()-1));
        int initialYear = lastDate[2]-1;
        int initialMonth = lastDate[1];
        int i = 0;
        for(VentaDTO venta : ventas) {

            int[] fechaVenta = getDate(venta);
            int monthVenta = fechaVenta[1];
            int yearVenta = fechaVenta[2];
            int monthRelative = 0;
            ItemDTO currItem = getItemVenta(venta);

            if (yearVenta > initialYear) {
                monthRelative = 11 + monthVenta - initialMonth;
                salesLastYear.set(monthRelative, salesLastYear.get(monthRelative) + 1);
                this.totalUltimos12Meses += venta.getTotal();
                if(monthRelative == 11){
                    this.totalUltimoMes += venta.getTotal();
                }
                if (items.containsKey(currItem)) {
                    items.put(currItem, items.get(currItem) + 1);
                } else {
                    items.put(currItem, 1);
                }
            } else if (yearVenta == initialYear && monthVenta >= initialMonth) {
                monthRelative = monthVenta - initialMonth;
                salesLastYear.set(monthRelative, salesLastYear.get(monthRelative) + 1);
                this.totalUltimos12Meses += venta.getTotal();

                if (items.containsKey(currItem)) {
                    items.put(currItem, items.get(currItem) + 1);
                } else {
                    items.put(currItem, 1);
                }
            }
        }
        return salesLastYear;
    }

    /**
     * Se encarga de conseguir la fecha de una venta y devolverla como un array de enteros
     * @param venta Objeto VentaDTO con la informacion de una venta
     * @return int array con el dia mes y año de la venta
     */
    private int[] getDate(VentaDTO venta){
        String lastDate = venta.getFecha().toString();
        String[] date = lastDate.split("-");
        int actualYear = parseInt(date[0]);
        int actualMonth = parseInt(date[1]);
        int actualDay = parseInt(date[2]);
        return new int[]{actualDay, actualMonth, actualYear};
    }

    /**
     * Encuentra los n items mas vendidos de un diccionario de Items y cantidad de ventas
     * @param map HashMap conteniendo cada item y sus cantidad de ventas
     * @param n La cantidad de items que queremos conseguir (ordenados de mayor a menor en ventas)
     * @param <K>
     * @param <V>
     * @return Retorna una lista de Entries ItemDTO e Integer del HashMap que procesamos
     */
    private <K, V extends Comparable<? super V>> List<Entry<ItemDTO, Integer>>
    findMostSaledItems(Map<ItemDTO, Integer> map, int n)
    {
        Comparator<? super Entry<ItemDTO, Integer>> comparator =
                new Comparator<Entry<ItemDTO, Integer>>()
                {
                    @Override
                    public int compare(Entry<ItemDTO, Integer> e0, Entry<ItemDTO, Integer> e1)
                    {
                        Integer v0 = e0.getValue();
                        Integer v1 = e1.getValue();
                        return v0.compareTo(v1);
                    }
                };
        PriorityQueue<Entry<ItemDTO, Integer>> highest =
                new PriorityQueue<Entry<ItemDTO,Integer>>(n, comparator);
        for (Entry<ItemDTO, Integer> entry : map.entrySet())
        {
            highest.offer(entry);
            while (highest.size() > n)
            {
                highest.poll();
            }
        }

        List<Entry<ItemDTO, Integer>> result = new ArrayList<Map.Entry<ItemDTO, Integer>>();
        while (highest.size() > 0)
        {
            result.add(highest.poll());
        }
        return result;
    }


    /**
     * Metodo que retorna todos los datos procesados para el reporte
     * @return Objeto ReporteDTO que contiene toda la informacion del reporte
     */
    public ReporteDTO getReporte(){
        return new ReporteDTO(this.ventasUltimoMes, this.ventas12Meses,
                this.totalUltimoMes, this.totalUltimos12Meses,
                this.mostSaledItems, this.ventasHoy, this.totalHoy);
    }




}
