package userHackathon.gui;

import userHackathon.service.AlunoService;
import userHackathon.service.EmpresaService;
import userHackathon.service.RelatorioService;
import userHackathon.util.ActionManager;

import javax.swing.*;
import java.awt.*;

public class RelatoriosGUI extends JFrame implements PainelDefault {

    private final RelatorioService relatorioService = new RelatorioService();
    private final AlunoService alunoService = new AlunoService();
    private final EmpresaService empresaService = new EmpresaService();

    private JPanel painel = new JPanel(null);

    JButton btnRelatorioAlunos = new JButton("Gerar Relatório de Alunos");
    JButton btnRelatorioEmpresas = new JButton("Gerar Relatórios de Empresas");

    public RelatoriosGUI() throws HeadlessException{

        setTitle("Relatórios");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        btnRelatorioAlunos.setBounds(300,240,200,40);
        btnRelatorioEmpresas.setBounds(300,290,200,40);

        painel.add(btnRelatorioAlunos);
        painel.add(btnRelatorioEmpresas);

        ActionManager.configurarBotaoExportarRelatorioAlunos(
                btnRelatorioAlunos,
                this,
                this.alunoService,
                this.relatorioService
        );

        ActionManager.configurarBotaoExportarRelatorioEmpresas(
                btnRelatorioEmpresas,
                this,
                this.empresaService,
                this.relatorioService
        );

        getContentPane().add(painel,BorderLayout.CENTER);
    }
}
