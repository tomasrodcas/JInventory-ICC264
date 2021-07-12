package DAO;

import DAO.ItemDAO;
import DAO.VentaDAO;
import DTO.ItemDTO;
import DTO.ReporteDTO;
import DTO.VentaDTO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class ReporteDAO {

    private final ArrayList<VentaDTO> ventas;
    private final HashMap<ItemDTO, Integer> items;
    private final ArrayList<Integer> ventas12Meses;
    private final int ventasUltimoMes;
    private final List<Entry<ItemDTO, Integer>> mostSaledItems;
    private int totalUltimoMes;
    private int totalUltimos12Meses;

    public ReporteDAO(){
        this.totalUltimoMes = 0;
        this.totalUltimos12Meses = 0;
        this.ventas = new VentaDAO().getVentasDB();
        this.items = new HashMap<ItemDTO, Integer>();
        this.ventas12Meses = processSalesLast12Months();
        this.ventasUltimoMes = this.ventas12Meses.get(this.ventas12Meses.size()-1);
        this.mostSaledItems = findMostSaledItems(items, 5);


    }
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
    private ItemDTO getItemVenta(VentaDTO venta){
       return new ItemDAO().getItemById(venta.getIdProducto());
    }

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

    private int[] getDate(VentaDTO venta){
        String lastDate = venta.getFecha().toString();
        String[] date = lastDate.split("-");
        int actualYear = parseInt(date[0]);
        int actualMonth = parseInt(date[1]);
        int actualDay = parseInt(date[2]);
        return new int[]{actualDay, actualMonth, actualYear};
    }

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


    public ReporteDTO getReporte(){
        return new ReporteDTO(this.ventasUltimoMes, this.ventas12Meses, this.totalUltimoMes, this.totalUltimos12Meses, this.mostSaledItems);
    }




}
