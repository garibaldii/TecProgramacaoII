package Principal;

import Principal.Periodo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlanoDeTrabalho {

    private Map<String, List<Periodo>> periodosPorDia = new HashMap<>();
    private List<Float> listaComAsJornadasDeTrabalho = new ArrayList<>();
    
    
    

    public void adicionarPeriodo(String diaDaSemana, Periodo periodo) {

        if (periodosPorDia.size() < 7) {

            if (!periodosPorDia.containsKey(diaDaSemana)) { //verifica: se o dia não foi cadastrado, é colocado como chave o diaDaSemana e é criada uma lista para armazenar seus períodos
               
                periodosPorDia.put(diaDaSemana, new ArrayList<>());
                periodosPorDia.get(diaDaSemana).add(periodo);

                listaComAsJornadasDeTrabalho = periodo.percorrePeriodo(periodo.getInicio(), periodo.getFim()); //periodo é adicionado a lista geral de jornada desse respectivo dia.
                System.out.println("1 - Dia nao encontrado no nosso sistema, entao foi cadastrado um novo: " + listaComAsJornadasDeTrabalho);
            } 
            
            
            else {
                if (PlanoDeTrabalho.TemPeriodoJaCadastrado(periodo, listaComAsJornadasDeTrabalho)) {
                    System.out.println("2 - Periodo ja cadastrado: " + listaComAsJornadasDeTrabalho);
                } else {
                    periodosPorDia.get(diaDaSemana).add(periodo);
                    System.out.println("3 - Dia  encontrado no nosso sistema, cadastrado um novo periodo: " + listaComAsJornadasDeTrabalho);
                }
            }

        } else {
            System.out.println("Limite máximo de dias criado!");
        }

    }

    
    
    public static boolean TemPeriodoJaCadastrado(Periodo periodo, List<Float> listaComAsJornadasDeTrabalho) {

        List<Float> listaTemporaria = new ArrayList<>();

        listaTemporaria = periodo.percorrePeriodo(periodo.getInicio(), periodo.getFim());

        for (float hora : listaTemporaria) {
            if (listaComAsJornadasDeTrabalho.contains(hora)) {
                return true;
            }
        }

        listaComAsJornadasDeTrabalho.addAll(listaTemporaria);
        return false;

    }


    
    
    public List<Periodo> getPeriodosParaDia(String diaDaSemana) {//está retornando 1 período daquele dia, precisa retornar TODOS
        List<Periodo> listaPeriodos = periodosPorDia.get(diaDaSemana);
        for (Periodo periodo : listaPeriodos) {
            System.out.println("Início: " + periodo.getInicio() + "Fim: " + periodo.getFim() + "tempoPeriodo: " + periodo.percorrePeriodo(periodo.getInicio(), periodo.getFim()));
        }

        return periodosPorDia.get(diaDaSemana);
    }

    
    
    
    public float getSalario() {
        float salario = 0;
        
        for (String chave: periodosPorDia.keySet()){ //keyset retorna todas as chaves do dicionário periodosPorDia
            
                List<Periodo> valorReferenteChave = periodosPorDia.get(chave);
                
                for (Periodo periodo: valorReferenteChave){
                    salario += periodo.getSalarioPadrao();
                }
        }
        
        System.out.println(salario);
        return salario;
    }

    

    
}
