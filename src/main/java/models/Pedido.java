
package models;
import enums.Status;
import exceptions.EstoqueInsuficienteException;
import exceptions.PedidoInvalidoException;
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
    
    public void adicionarItem(Produto produto, int quantidade) throws EstoqueInsuficienteException{
        if(status == Status.ENVIADO || status == Status.CANCELADO){
            throw new PedidoInvalidoException("Pedido nao pode ser alterado no status "+status);
        }
        if(quantidade <= 0){
            throw new PedidoInvalidoException("Quantidade deve ser maior que zero");
        }
        
        if(quantidade > produto.getEstoque()){
            throw new EstoqueInsuficienteException("Estoque insuficiente. "+produto.getNome()+" possui "+produto.getEstoque()+" em estoque");
        }
        
        itens.add(new ItemPedido(produto, quantidade));
        produto.setEstoque(produto.getEstoque()-quantidade);
}
    
    public void cancelar(){
        if (status == Status.ENVIADO || status == Status.CANCELADO){
            throw new PedidoInvalidoException("Pedido nao pode ser cancelado no status "+status);
        }
        
        for(ItemPedido item:itens){
            Produto produto = item.getProduto();
            produto.setEstoque(produto.getEstoque()+item.getQuantidade());
        }
        
        status = Status.CANCELADO;
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
        if(this.status == Status.CANCELADO || this.status == Status.ENVIADO){
            throw new PedidoInvalidoException("Pedido no status "+this.status+"nao pode mudar status");
        }
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

