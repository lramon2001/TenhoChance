package com.lr.projects.tenhochance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lr.projects.tenhochance.utils.GeradorDeArquivos.GeradorDeArquivos;

@SpringBootApplication
public class TenhochanceApplication {

	
    private static String caminhoCSV_CANDITADOS;
    private static String caminhoCSV_CURSOS;
    private static String caminhoPDF;
    private static Integer paginaInicial;
    private static Integer paginaFinal;
    private static String  sequenciaApagada;

    @Value("${caminho.pdf}")
    private void setCaminhoPDFNonStatic(String caminhoPDF) {
        TenhochanceApplication.caminhoPDF = caminhoPDF;
    }

    @Value("${caminho.candidatos.csv}")
    private void setCaminhoCSV_CANDITADOSNonStatic(String caminhoCSV_CANDITADOS) {
        TenhochanceApplication.caminhoCSV_CANDITADOS = caminhoCSV_CANDITADOS;
    }

    @Value("${caminho.cursos.csv}")
    private void setCaminhoCSV_CURSOSNonStatic(String caminhoCSV_CURSOS) {
        TenhochanceApplication.caminhoCSV_CURSOS = caminhoCSV_CURSOS;
    }

    @Value("${pagina.inicial}")
    private void setPaginaIncial(Integer paginaInicial) {
        TenhochanceApplication.paginaInicial = paginaInicial;
    }

    @Value("${pagina.final}")
    private void setPaginaFinal(Integer paginaFinal) {
        TenhochanceApplication.paginaFinal = paginaFinal;
    }

    @Value("${sequencia.apagada}")
    private void setSequenciaApagada(String sequenciaApagada) {
        TenhochanceApplication.sequenciaApagada = sequenciaApagada;
    }

    public static void main(String[] args) {
        SpringApplication.run(TenhochanceApplication.class, args);
    }

    @Bean
    public ApplicationRunner run() {
        return args -> {
            GeradorDeArquivos.executar(caminhoCSV_CANDITADOS,caminhoCSV_CURSOS,caminhoPDF,paginaInicial,paginaFinal,sequenciaApagada);
        };
    }

}
