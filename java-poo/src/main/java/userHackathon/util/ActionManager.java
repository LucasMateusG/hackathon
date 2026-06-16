package userHackathon.util;

import userHackathon.model.Aluno;
import userHackathon.model.Empresa;
import userHackathon.model.EnderecoAluno;
import userHackathon.service.AlunoService;
import userHackathon.service.EmpresaService;
import userHackathon.service.RelatorioService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class ActionManager {
    public static void configurarBotaoStatusEmpresa(
            JButton botao,
            JTextField idField,
            Boolean novoStatus,
            EmpresaService service,
            JTable tabela,
            //vai guardar a informação e após a execução da ação ele vai executar oque está guardado dentro dele
            Runnable atualizarTela
    ) {
        botao.addActionListener(e -> {
            //.trim é apenas para tirar espaços e pegar apenas a String
            String idTexto = idField.getText().trim();
            if (!idTexto.isEmpty()) {
                try {
                    int id = Integer.parseInt(idTexto);

                    service.atualizar(id, novoStatus);

                    //vai executar a limpeza feita na GUI
                    if (atualizarTela != null) {
                        atualizarTela.run();
                    }
                } catch (Exception ex) {
                    System.out.println("Error" + ex.getMessage());
                }
            } else {
                //JOptionPane funciona para poder criar mensagens que apareçam informando algo para o usuario
                JOptionPane.showMessageDialog(null, "Selecione uma empresa primeiro",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void configurarBotaoIncluirAluno(
            JButton botao,
            JTextField nomeField, JTextField cpfField, JTextField emailField,
            JTextField telefoneField, JTextField cursoField, JTextField periodoField,
            JTextField dataNascField, JTextField logradouroField, JTextField numField,
            JTextField bairroField, JTextField cepField, JTextField cidadeField, JTextField ufField,
            AlunoService service,
            Runnable atualizarTela
    ) {
        botao.addActionListener(e -> {
            if (nomeField.getText().trim().isEmpty() || cpfField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome e CPF são obrigatórios para o cadastro.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Aluno aluno = new Aluno();
                aluno.setNome(nomeField.getText().trim());
                aluno.setCpf(cpfField.getText().trim());
                aluno.setEmail(emailField.getText().trim());
                aluno.setTelefone(telefoneField.getText().trim());
                aluno.setCurso(cursoField.getText().trim());

                if (!periodoField.getText().trim().isEmpty()) {
                    aluno.setPeriodo(Integer.parseInt(periodoField.getText().trim()));
                }
                aluno.setDataNascimento(dataNascField.getText().trim());

                EnderecoAluno endereco = new EnderecoAluno();
                endereco.setLogradouro(logradouroField.getText().trim());
                endereco.setNumLogradouro(numField.getText().trim());
                endereco.setBairro(bairroField.getText().trim());
                endereco.setCep(cepField.getText().trim());
                endereco.setCidade(cidadeField.getText().trim());
                endereco.setUf(ufField.getText().trim());

                service.incluir(aluno, endereco);


                if (atualizarTela != null) {
                    atualizarTela.run();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir aluno: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: " + ex.getMessage());
            }
        });
    }

    public static void configurarBotaoImportarAluno(
            JButton botao,
            Component componentePai,
            AlunoService service,
            Runnable atualizarTela
    ) {
        botao.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int retorno = fileChooser.showOpenDialog(componentePai);

            if (retorno == JFileChooser.APPROVE_OPTION) {
                File arquivoSelecionado = fileChooser.getSelectedFile();
                try {
                    service.importarTxt(arquivoSelecionado);

                    JOptionPane.showMessageDialog(componentePai, "Alunos importados com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    // Se a importação der certo, avisa a GUI para recarregar a tabela
                    if (atualizarTela != null) {
                        atualizarTela.run();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(componentePai, "Erro ao importar: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        });
    }

    public static void configurarBotaoExportarRelatorioAlunos(
            JButton botao,
            Component componentePai,
            AlunoService alunoService,
            RelatorioService relatorioService
    ) {
        botao.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Relatório de Alunos");
            fileChooser.setSelectedFile(new File("relatorio_alunos.txt"));

            int retorno = fileChooser.showSaveDialog(componentePai);
            if (retorno == JFileChooser.APPROVE_OPTION) {
                File arquivoDestino = fileChooser.getSelectedFile();
                try {
                    // Busca a lista atualizada de alunos vinda do banco
                    List<Aluno> listaAlunos = alunoService.listar();

                    // Dispara a escrita do arquivo txt
                    relatorioService.gerarRelatorioAlunos(listaAlunos, arquivoDestino);

                    JOptionPane.showMessageDialog(componentePai, "Relatório exportado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(componentePai, "Erro ao gerar relatório: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void configurarBotaoExportarRelatorioEmpresas(
            JButton botao,
            Component componentePai,
            EmpresaService empresaService,
            RelatorioService relatorioService
    ) {
        botao.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Relatório de Empresas");
            fileChooser.setSelectedFile(new File("relatorio_empresas.txt"));

            int retorno = fileChooser.showSaveDialog(componentePai);
            if (retorno == JFileChooser.APPROVE_OPTION) {
                File arquivoDestino = fileChooser.getSelectedFile();
                try {
                    // Busca a lista atualizada de alunos vinda do banco
                    List<Empresa> listaEmpresas = empresaService.listar();

                    // Dispara a escrita do arquivo txt
                    relatorioService.gerarRelatorioEmpresas(listaEmpresas, arquivoDestino);

                    JOptionPane.showMessageDialog(componentePai, "Relatório exportado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(componentePai, "Erro ao gerar relatório: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }
}

