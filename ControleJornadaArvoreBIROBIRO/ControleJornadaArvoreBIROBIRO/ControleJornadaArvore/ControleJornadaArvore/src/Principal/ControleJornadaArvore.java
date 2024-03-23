


package Principal;

import Funcionario.Administrativo;




public class ControleJornadaArvore {

    
    
    public static void main(String[] args) {
        
        PlanoDeTrabalho planoAdm = new PlanoDeTrabalho();
        Administrativo auxAdministrativo = new Administrativo();
        
        auxAdministrativo.setPlanoADM(planoAdm);
        
        
        planoAdm.adicionarPeriodo("Segunda", new Periodo("HoraExtra", 17,20,1));
        planoAdm.adicionarPeriodo("Segunda", new Periodo("HoraExtra", 17,20,1));
        planoAdm.adicionarPeriodo("Segunda", new Periodo("Comercial", 8,16,1));


        
        planoAdm.getSalario();
        
        auxAdministrativo.calculaSalario(planoAdm, 0);

      


        

        
     
        
       
        
        

    }
    
}


