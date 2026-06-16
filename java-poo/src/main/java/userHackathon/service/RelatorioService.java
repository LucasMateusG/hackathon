
package userHackathon.service;

import userHackathon.model.Aluno;
import userHackathon.model.Empresa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

    public class RelatorioService {

        //  "/n = quebra de linha"

        public void gerarRelatorioAlunos(List<Aluno> alunos, File arquivoDestino) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDestino))) {
                writer.write("=========================================================================\n");
                writer.write("                       RELATÓRIO DE ALUNOS CADASTRADOS                   \n");
                writer.write("=========================================================================\n\n");

                if (alunos.isEmpty()) {
                    writer.write("Nenhum aluno cadastrado no sistema até o momento.\n");
                } else {
                    for (Aluno aluno : alunos) {
                        writer.write(String.format("RA: %-10s | Nome: %-30s | CPF: %-15s\n",
                                aluno.getRa() != null ? aluno.getRa() : "N/A",
                                aluno.getNome(),
                                aluno.getCpf()));
                        writer.write(String.format("Curso: %-27s | Período: %-2dº | Email: %-30s\n",
                                aluno.getCurso(),
                                aluno.getPeriodo() != null ? aluno.getPeriodo() : 0,
                                aluno.getEmail()));

                        if (aluno.getEndereco() != null) {
                            writer.write(String.format("Endereço: %s, Nº %s - %s, %s/%s\n",
                                    aluno.getEndereco().getLogradouro(),
                                    aluno.getEndereco().getNumLogradouro(),
                                    aluno.getEndereco().getBairro(),
                                    aluno.getEndereco().getCidade(),
                                    aluno.getEndereco().getUf()));
                        }
                        writer.write("-------------------------------------------------------------------------\n");
                    }
                }
                writer.write("\nFim do Relatório. Total de registros: " + alunos.size() + "\n");
            }
        }


    public void gerarRelatorioEmpresas(List<Empresa> empresas, File arquivoDestino) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDestino))) {
            writer.write("=========================================================================\n");
            writer.write("                      RELATÓRIO DE EMPRESAS CADASTRADAS                  \n");
            writer.write("=========================================================================\n\n");
            for (Empresa emp : empresas) {
                writer.write(String.format("ID: %-5d | Empresa: %-30s | Status: %s\n",
                        emp.getId(), emp.getRazaoSocial(), (emp.getStatus() ? "ATIVA" : "BLOQUEADA/INATIVA")));
                writer.write("-------------------------------------------------------------------------\n");
            }
        }
    }
/*
    // 3. Relatório de Vagas Disponíveis
    public void gerarRelatorioVagas(List<Vaga> vagas, File arquivoDestino) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDestino))) {
            writer.write("=========================================================================\n");
            writer.write("                        RELATÓRIO DE VAGAS DISPONÍVEIS                   \n");
            writer.write("=========================================================================\n\n");
            for (Vaga vaga : vagas) {
                writer.write(String.format("Vaga ID: %-5d | Título: %-25s | Empresa: %-25s\n",
                        vaga.getId(), vaga.getTitulo(), vaga.getEmpresaNome()));
                writer.write("-------------------------------------------------------------------------\n");
            }
        }
    }

    // 4. Relatório de Candidaturas e Status
    public void gerarRelatorioCandidaturas(List<Candidatura> candidaturas, File arquivoDestino) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDestino))) {
            writer.write("=========================================================================\n");
            writer.write("                     RELATÓRIO DE CANDIDATURAS REALIZADAS                \n");
            writer.write("=========================================================================\n\n");
            for (Candidatura cand : candidaturas) {
                writer.write(String.format("Aluno: %-25s | Vaga: %-25s | Status: %s\n",
                        cand.getAlunoNome(), cand.getVagaTitulo(), cand.getStatus()));
                writer.write("-------------------------------------------------------------------------\n");
            }
        }
    }
    */
    }

