
package descontos;


public class DescontoFixo implements Desconto{
    private double descontoFixo;
    
    public DescontoFixo(double descontoFixo){
    this.descontoFixo = descontoFixo;
    
}
    @Override
    public double aplicar(double valor){
        double resultado = valor-descontoFixo;
        return Math.max(resultado, 0);
    }
}
