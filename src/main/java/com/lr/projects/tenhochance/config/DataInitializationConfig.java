package com.lr.projects.tenhochance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.lr.projects.tenhochance.entity.Candidato;
import com.lr.projects.tenhochance.entity.Curso;
import com.lr.projects.tenhochance.repository.CandidatoRepository;
import com.lr.projects.tenhochance.repository.CursoRepository;
import com.lr.projects.tenhochance.utils.ProcessadorDeArquivos.GeradorDeArquivos.GeradorDeArquivos;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializationConfig {

    private final ResourceLoader resourceLoader;
    private final CursoRepository cursoRepository;
    private final CandidatoRepository candidatoRepository;

    private static String caminhoCSV_CANDITADOS;
    private static String caminhoCSV_CURSOS;
    private static String caminhoPDF;
    private static Integer paginaInicial;
    private static Integer paginaFinal;
    private static String sequenciaApagada;
    private Boolean cargaIncialCursoConcluida = false;
    private final Integer tempoDeEspera = 100;

    @Value("${caminho.recurso.curso.csv}")
    private String caminhoRecursoCursoCSV;

    @Value("${caminho.recurso.candidato.csv}")
    private String caminhoRecursoCandidatoCSV;

    @Value("${caminho.pdf}")
    private void setCaminhoPDFNonStatic(String caminhoPDF) {
        DataInitializationConfig.caminhoPDF = caminhoPDF;
    }

    @Value("${caminho.candidatos.csv}")
    private void setCaminhoCSV_CANDITADOSNonStatic(String caminhoCSV_CANDITADOS) {
        DataInitializationConfig.caminhoCSV_CANDITADOS = caminhoCSV_CANDITADOS;
    }

    @Value("${caminho.cursos.csv}")
    private void setCaminhoCSV_CURSOSNonStatic(String caminhoCSV_CURSOS) {
        DataInitializationConfig.caminhoCSV_CURSOS = caminhoCSV_CURSOS;
    }

    @Value("${pagina.inicial}")
    private void setPaginaIncial(Integer paginaInicial) {
        DataInitializationConfig.paginaInicial = paginaInicial;
    }

    @Value("${pagina.final}")
    private void setPaginaFinal(Integer paginaFinal) {
        DataInitializationConfig.paginaFinal = paginaFinal;
    }

    @Value("${sequencia.apagada}")
    private void setSequenciaApagada(String sequenciaApagada) {
        DataInitializationConfig.sequenciaApagada = sequenciaApagada;
    }

    @Autowired
    public DataInitializationConfig(ResourceLoader resourceLoader, CursoRepository cursoRepository,CandidatoRepository candidatoRepository) {
        this.resourceLoader = resourceLoader;
        this.cursoRepository = cursoRepository;
        this.candidatoRepository = candidatoRepository;
    }

    private void carregarCursos() throws IOException, CsvException {
        Resource resource = resourceLoader.getResource(caminhoRecursoCursoCSV);
        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());

        try (CSVReader csvReader = new CSVReader(inputStreamReader)) {
            List<String[]> registros = csvReader.readAll();
            List<Curso> cursos = new ArrayList<>();
            for (String[] registro : registros) {
                if (registro.length >= 2) {
                    Long id = Long.parseLong(registro[0].trim());
                    String nome = registro[1].trim();

                    Curso curso = new Curso().builder().id(id).nome(nome).build();
                    cursos.add(curso);
                }
            }

            cursoRepository.saveAll(cursos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarCandidatos() throws IOException, CsvException {
        Resource resource = resourceLoader.getResource(caminhoRecursoCandidatoCSV);
        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());

        try (CSVReader csvReader = new CSVReader(inputStreamReader)) {
            List<String[]> registros = csvReader.readAll();
            List<Candidato> candidatos = new ArrayList<>();
            for (String[] registro : registros) {
                if (registro.length >= 14) {
                    Candidato candidato = new Candidato().builder().
                            numeroInscricao(Long.parseLong(registro[0].replace(";", "").trim()))
                            .nome(registro[1].trim())
                            .notaFinal(Double.parseDouble(registro[2].trim()))
                            .classificacaoFinalUniversal(this.converteInteiro(registro[3].trim()))
                            .classificacaoFinalNegros(this.converteInteiro(registro[4].trim()))
                            .classificacaoFinalEscolaPublicaBaixaRendaPpi(this.converteInteiro(registro[5].trim()))
                            .classificacaoFinalEscolaPublicaBaixaRendaPpiPcd(this.converteInteiro(registro[6].trim()))
                            .classificacaoFinalEscolaPublicaBaixaRenda(this.converteInteiro(registro[7].trim()))
                            .classificacaoFinalEscolaPublicaBaixaRendaPcd(this.converteInteiro(registro[8].trim()))
                            .classificacaoFinalEscolaPublicaNaoBaixaRendaPpi(this.converteInteiro(registro[9].trim()))
                            .classificacaoFinalEscolaPublicaNaoBaixaRendaPpiPcd(this.converteInteiro(registro[10].trim()))
                            .classificacaoFinalEscolaPublicaNaoBaixaRenda(this.converteInteiro(registro[11].trim()))
                            .classificacaoFinalEscolaPublicaNaoBaixaRendaPcd(this.converteInteiro(registro[12].trim()))
                            .curso(cursoRepository.findById(Long.parseLong(registro[13].replace(";", "").trim())).get()).build();
                            candidatos.add(candidato);
                }
            }

           candidatoRepository.saveAll(candidatos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer converteInteiro(String valor){
         return "-".equals(valor) ? null : Integer.parseInt(valor);
    }
    @PostConstruct
    public void init() throws IOException, CsvException {
        Thread threadGerarArquivos = new Thread(() -> {
            GeradorDeArquivos.executar(caminhoCSV_CANDITADOS, caminhoCSV_CURSOS, caminhoPDF, paginaInicial, paginaFinal,
                    sequenciaApagada);
        });
        threadGerarArquivos.start();

        while (!GeradorDeArquivos.processoConcluido()) {
            try {
                Thread.sleep(tempoDeEspera);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        /*
         * Thread threadCarregarCursos = new Thread(() -> {
         * try {
         * carregarCursos();
         * } catch (IOException e) {
         * 
         * e.printStackTrace();
         * } catch (CsvException e) {
         * e.printStackTrace();
         * }
         * });
         * threadCarregarCursos.start();
         * 
         * while (!cargaIncialCursoConcluida) {
         * try {
         * Thread.sleep(tempoDeEspera);
         * } catch (InterruptedException e) {
         * Thread.currentThread().interrupt();
         * }
         * }
         * 
         * carregarCandidatos();
         */
        carregarCursos();
        carregarCandidatos();
    }

}
