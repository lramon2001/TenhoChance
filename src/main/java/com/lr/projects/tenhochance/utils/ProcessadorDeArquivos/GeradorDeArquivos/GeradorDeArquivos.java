package com.lr.projects.tenhochance.utils.ProcessadorDeArquivos.GeradorDeArquivos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lr.projects.tenhochance.utils.ProcessadorDeArquivos.ProcessadorDePDF.ProcessadorDePDF;

import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivos {

    private static final Logger logger = LogManager.getLogger(GeradorDeArquivos.class);

    public GeradorDeArquivos(String caminhoCSV_CANDITADOS, String caminhoCSV_CURSOS,String caminhoPDF,Integer paginaInicial,Integer paginaFinal,String sequenciaApagada) {
        this.geraArquivos(caminhoCSV_CANDITADOS, caminhoCSV_CURSOS,caminhoPDF,paginaInicial,paginaFinal,sequenciaApagada);
    }

    private static void gravarEmCSV(String caminho, String texto) {
        try {
            FileWriter fileWriter = new FileWriter(caminho);
            fileWriter.write(texto);
            logger.info("Arquivo gerado com sucesso.");
            fileWriter.close();
        } catch (IOException e) {
            logger.error("Erro ao gerar arquivo.");
            logger.error(e.getMessage());
        }

    }

    private void geraArquivos(String caminhoCSV_CANDITADOS, String caminhoCSV_CURSOS,String caminhoPDF,Integer paginaInicial,Integer paginaFinal,String sequenciaApagada) {
        logger.info("Gerando arquivos...");
        gravarEmCSV(caminhoCSV_CURSOS, ProcessadorDePDF.processaPDF(caminhoPDF,paginaInicial,paginaFinal,sequenciaApagada).get(0));
        gravarEmCSV(caminhoCSV_CANDITADOS, ProcessadorDePDF.processaPDF(caminhoPDF,paginaInicial,paginaFinal,sequenciaApagada).get(1));

    }

    public static GeradorDeArquivos executar(String caminhoCSV_CANDITADOS, String caminhoCSV_CURSOS,String caminhoPDF,Integer paginaInicial,Integer paginaFinal,String sequenciaApagada) {
        logger.info("Iniciando geração de arquivos.");
        GeradorDeArquivos gerador = new GeradorDeArquivos(caminhoCSV_CANDITADOS, caminhoCSV_CURSOS,caminhoPDF,paginaInicial,paginaFinal,sequenciaApagada);
        return gerador;
    }

}
