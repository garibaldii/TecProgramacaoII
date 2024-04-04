package Principal;

import java.util.ArrayList;
import java.util.List;


public class Semana {
    
    List<PlanoDeTrabalho> dias = new ArrayList<>(7);
    
    public void adicionarDia(PlanoDeTrabalho dia){
        dias.add(dia);
    }
    
    
    
}
