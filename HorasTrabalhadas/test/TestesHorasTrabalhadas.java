/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import horastrabalhadas.HorasTrabalhadas;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Alunos
 */
public class TestesHorasTrabalhadas {

    @Test
    public void Teste01_e_Teste02_VerificaHoraNormal() {

        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(8.0f, 12.0f);

        assertEquals(4.0f, resposta, 0);

        //Teste02 (inicio = 13 && fim = 18 /// Resultado esperado = 5
        resposta = jornada.calculaHora(13.0f, 18.0f);

        assertEquals(5.0f, resposta, 0);

    }

    @Test
    public void Teste03_VerificaDireitoHorarioAlmoco() {

        //Teste03(inicio = 8 && fim = 18 // resultado esperado = 11.5 (regra do horario de almoço)
        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(8.0f, 18.0f);

        assertEquals(11.5f, resposta, 0);

    }

    @Test
    public void Teste04_VerificaDireitoHoraExtra() {

        //Teste04(inicio = 14 && fim = 19 // resultado esperado = 5.5 // é para achar o período trabalhado normal e somar com o restante * 1.5
        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(14.0f, 19.0f);

        assertEquals(5.5f, resposta, 0);
    }

    @Test
    public void Teste05_VerificaDireitoHoraExtraDiferente() {

        //Teste05(inicio = 6&& fim = 12 // resultado esperado = 7 // é para achar o período trabalhado normal e somar com o restante * 2
        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(6.0f, 12.0f);

        assertEquals(7.0f, resposta, 0);
    }

    @Test
    public void Teste06_VerificaDireitoHoraExtraHoraAlmoco() {

        //Teste06(inicio = 6&& fim = 13 // resultado esperado = 9.7 // é para achar o período trabalhado normal e somar com o restante + 1.5 + horario de almoco (1.5)
        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(6.0f, 13.0f);

        assertEquals(9.5f, resposta, 0);
    }

    @Test
    public void Teste07_VerificaDireitoHoraExtraHoraTerceiroTurno() {

        //Teste07(Inicio = 17 && fim = 23 // resultado esperado = 9 // vai somar 1 + (1,5 *4 ) + (2 * 1 terceiroturno) = 9
        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(17.0f, 23.0f);

        assertEquals(9.0f, resposta, 0);

    }

    @Test
    public void Teste08_VerificaDireitoHoraExtraHoraTerceiroTurnoHoraAlmoco() {

        //Teste08(Inicio = 17 && fim = 23 // resultado esperado = 9 //
        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(0.0f, 9.0f);

        assertEquals(17.5f, resposta, 0);
    }

    @Test
    public void Teste09_VerificaDireitoTerceiroTurno() {
        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(22.0f, 2.0f);

        assertEquals(8.0f, resposta, 0);
    }

    @Test
    public void Teste10_VerificaDireitoHoraNormalHoraExtraTerceiroTurnoHoraAlmoco() {
        HorasTrabalhadas jornada = new HorasTrabalhadas();

        float resposta = jornada.calculaHora(14.0f, 2.0f);

        assertEquals(19.5f, resposta, 0);

    }

}
