
package models;
import enums.Status;
import java.util.List;
import java.time.LocalDate;

public class Pedido {
    private int id;
    private String cliente;
    private Status status;
    private List<ItemPedido> itens;
    private LocalDate data;
    
    public Pedido(int id, String cliente, Status status, List<ItemPedido> lista, LocalDate data){
        this.id = id;
        this.cliente = cliente;
        this.status = status;
        this.itens = lista;
        this.data = data;
    }
    
    public double getTotal(){
        return itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ItemPedido> getLista() {
        return itens;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    
            
}

