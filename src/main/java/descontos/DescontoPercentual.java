
package descontos;


public class DescontoPercentual implements Desconto {
    private double percentual;
    
    public DescontoPercentual(double percentual){
        this.percentual = percentual;
    }
    
    @Override
    public double aplicar(double valor){
        return valor-(valor*(percentual/100));
    }
    
}
