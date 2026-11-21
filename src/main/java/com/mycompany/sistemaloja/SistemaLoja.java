package com.mycompany.sistemaloja;

import enums.Categoria;
import enums.Status;
import exceptions.EstoqueInsuficienteException;
import models.Pedido;
import models.Produto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.ItemPedido;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Comparator;

public class SistemaLoja {

    public static void main(String[] args) throws EstoqueInsuficienteException{
        // Produtos
Produto notebook   = new Produto(1, "Notebook", Categoria.ELETRONICOS, 3500.0, 50);
Produto smartphone = new Produto(2, "Smartphone", Categoria.ELETRONICOS, 2000.0, 50);
Produto fone       = new Produto(3, "Fone de Ouvido", Categoria.ELETRONICOS, 150.0, 100);
Produto livroJava  = new Produto(4, "Java: Como Programar", Categoria.LIVROS, 120.0, 50);
Produto cleanCode  = new Produto(5, "Clean Code", Categoria.LIVROS, 90.0, 50);
Produto camiseta   = new Produto(6, "Camiseta", Categoria.ROUPAS, 50.0, 100);
Produto calca      = new Produto(7, "Calça Jeans", Categoria.ROUPAS, 130.0, 100);
Produto arroz      = new Produto(8, "Arroz 5kg", Categoria.ALIMENTOS, 28.0, 200);
Produto cafe       = new Produto(9, "Café 500g", Categoria.ALIMENTOS, 22.0, 200);

// Pedidos
Pedido ped1 = new Pedido(1, "Ana", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 10, 5));
ped1.adicionarItem(notebook, 1);
ped1.adicionarItem(fone, 2);
ped1.setStatus(Status.PAGO);

Pedido ped2 = new Pedido(2, "Bruno", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 10, 12));
ped2.adicionarItem(livroJava, 2);
ped2.adicionarItem(cleanCode, 1);
ped2.setStatus(Status.ENVIADO);

Pedido ped3 = new Pedido(3, "Carla", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 10, 18));
ped3.adicionarItem(camiseta, 3);
ped3.adicionarItem(calca, 2);
ped3.setStatus(Status.PAGO);

Pedido ped4 = new Pedido(4, "Diego", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 10, 25));
ped4.adicionarItem(smartphone, 1);
ped4.cancelar();

Pedido ped5 = new Pedido(5, "Ana", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 11, 2));
ped5.adicionarItem(arroz, 5);
ped5.adicionarItem(cafe, 4);
ped5.setStatus(Status.ENVIADO);

Pedido ped6 = new Pedido(6, "Bruno", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 11, 5));
ped6.adicionarItem(notebook, 1);
ped6.adicionarItem(smartphone, 1);
ped6.adicionarItem(fone, 1);
ped6.setStatus(Status.PAGO);

Pedido ped7 = new Pedido(7, "Carla", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 11, 8));
ped7.adicionarItem(cleanCode, 2);
ped7.adicionarItem(livroJava, 1);

Pedido ped8 = new Pedido(8, "Diego", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 11, 10));
ped8.adicionarItem(camiseta, 4);
ped8.adicionarItem(arroz, 3);
ped8.adicionarItem(cafe, 2);
ped8.setStatus(Status.PAGO);

Pedido ped9 = new Pedido(9, "Ana", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 11, 12));
ped9.adicionarItem(smartphone, 2);
ped9.cancelar();

Pedido ped10 = new Pedido(10, "Bruno", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 11, 15));
ped10.adicionarItem(camiseta, 1);
ped10.adicionarItem(calca, 1);
ped10.adicionarItem(fone, 1);
ped10.adicionarItem(arroz, 2);
ped10.adicionarItem(cafe, 1);
ped10.adicionarItem(cleanCode, 1);
ped10.setStatus(Status.ENVIADO);

Pedido ped11 = new Pedido(11, "Carla", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 11, 17));
ped11.adicionarItem(notebook, 2);

Pedido ped12 = new Pedido(12, "Diego", Status.CRIADO, new ArrayList<>(), LocalDate.of(2026, 11, 18));
ped12.adicionarItem(livroJava, 3);
ped12.adicionarItem(camiseta, 2);
ped12.setStatus(Status.ENVIADO);

List<Pedido> pedidos = new ArrayList<>(List.of(
        ped1, ped2, ped3, ped4, ped5, ped6, ped7, ped8, ped9, ped10, ped11, ped12));
      //1. Liste os nomes dos clientes que têm pelo menos um pedido PAGO, sem repetição e em ordem alfabética.
List<String> pedidoPago = pedidos.stream()
                           .filter(p -> p.getStatus() == Status.PAGO)
                           .map(n -> n.getCliente())
                           .distinct()
                           .sorted()
                           .toList();
System.out.println(pedidoPago);

    //2.Calcule o faturamento total (soma dos totais) dos pedidos que não foram CANCELADO.
    double faturamentoTotal = pedidos.stream()
                                    .filter(p -> p.getStatus() != Status.CANCELADO)
                                    .mapToDouble(p -> p.getTotal())
                                    .sum();
    System.out.printf("R$%.2f%n", faturamentoTotal);
    
    //3.Agrupe os pedidos por cliente (Map<String, List<Pedido>>).
    Map<String, List<Pedido>> porCliente = pedidos.stream()
                                           .collect(Collectors.groupingBy(Pedido::getCliente));
    porCliente.forEach((cliente, lista) -> System.out.println(cliente+": "+lista.size()+" pedidos"));
    
    //4. Gere um Map<String, Double> com o total gasto por cliente.
    Map<String, Double> gastoPorCliente = pedidos.stream()
                                          .collect(Collectors.groupingBy(
                                                  Pedido::getCliente,
                                                  Collectors.summingDouble(Pedido::getTotal)));
    gastoPorCliente.forEach((cliente, total) -> System.out.printf("%s: R$ %.2f%n", cliente, total));
    
    //5. Descubra qual é o produto mais vendido, em quantidade total, considerando todos os pedidos.
   Map<String, Integer> qtdPorProduto = pedidos.stream()
        .flatMap(p -> p.getLista().stream())
        .collect(Collectors.groupingBy(
                i -> i.getProduto().getNome(),
                Collectors.summingInt(ItemPedido::getQuantidade)));
   Optional<Map.Entry<String, Integer>> maisVendido = qtdPorProduto.entrySet().stream()
        .max(Map.Entry.comparingByValue());
   maisVendido.ifPresent(e ->
        System.out.println(e.getKey() + ": " + e.getValue() + " unidades"));
   
   //6. Calcule o ticket médio (média do total) dos pedidos de cada categoria de produto.
   Map<Categoria, Double> ticketPorCategoria = pedidos.stream()
        .flatMap(p -> p.getLista().stream()
                .map(i -> i.getProduto().getCategoria())
                .distinct()
                .map(c -> Map.entry(c, p)))
        .collect(Collectors.groupingBy(
                Map.Entry::getKey,
                Collectors.averagingDouble(e -> e.getValue().getTotal())));

ticketPorCategoria.forEach((categoria, media) ->
        System.out.printf("%s: R$ %.2f%n", categoria, media));

   //7. Mostre os 3 pedidos de maior valor, em ordem decrescente.
   List<Pedido> top3 = pedidos.stream()
                       .sorted(Comparator.comparingDouble(Pedido::getTotal))
                       .limit(3)
                       .toList();
   
   top3.forEach(p -> System.out.printf("Pedido %d (%s): R$ %.2f%n", p.getId(), p.getCliente(), p.getTotal()));
           
    //8.Verifique se existe algum pedido com mais de 5 itens (anyMatch) e se todos os pedidos têm total maior que zero (allMatch).
    boolean temPedidoGrande = pedidos.stream()
                              .anyMatch(p -> p.getLista().size() > 5);
                              
    
    boolean todosPositivos = pedidos.stream()
                             .allMatch(p -> p.getTotal() > 0);
    
    System.out.println("Existe pedido com mais de 5 itens? " + temPedidoGrande);
    System.out.println("Todos os pedidos têm total > 0? " + todosPositivos);
    }
    
    
    
    
  
}
