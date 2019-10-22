package com.simulator.tmoney.controller;

import com.simulator.tmoney.model.Usuario;
import com.simulator.tmoney.service.UsuarioService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

@Controller
public class RelatorioMb {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "/admin/relatorios", method = RequestMethod.GET)
    public ModelAndView criarConfiguracao() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.findByEmail(auth.getName());
        modelAndView.addObject("role", usuario.getRoles().iterator().next());
        modelAndView.setViewName("admin/relatorios");
        return modelAndView;
    }

    @PostMapping("/admin/relatorio/venda")
    public void imprimirVenda(HttpServletResponse response) throws JRException, SQLException, IOException {

        Map<String, Object> parametros = new HashMap<>();

        // Pega o arquivo .jasper localizado em resources
        InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/venda.jasper");

        // Cria o objeto JaperReport com o Stream do arquivo jasper
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        // Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

        // Configura a respota para o tipo PDF
        response.setContentType("application/pdf");
        // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
        // para fazer download do relatório troque 'inline' por 'attachment'
        response.setHeader("Content-Disposition", "inline; filename=Relatorio de Vendas.pdf");

        // Faz a exportação do relatório para o HttpServletResponse
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @PostMapping("/admin/relatorio/compra")
    public void imprimirCompra(HttpServletResponse response) throws JRException, SQLException, IOException {

        Map<String, Object> parametros = new HashMap<>();

        // Pega o arquivo .jasper localizado em resources
        InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/compra.jasper");

        // Cria o objeto JaperReport com o Stream do arquivo jasper
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        // Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

        // Configura a respota para o tipo PDF
        response.setContentType("application/pdf");
        // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
        // para fazer download do relatório troque 'inline' por 'attachment'
        response.setHeader("Content-Disposition", "inline; filename=Relatorio de Compras.pdf");

        // Faz a exportação do relatório para o HttpServletResponse
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @PostMapping("/admin/relatorio/rank-compra")
    public void imprimirRankCompra(HttpServletResponse response) throws JRException, SQLException, IOException {

        Map<String, Object> parametros = new HashMap<>();

        // Pega o arquivo .jasper localizado em resources
        InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/RelatoriodeCompras.jasper");

        // Cria o objeto JaperReport com o Stream do arquivo jasper
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        // Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

        // Configura a respota para o tipo PDF
        response.setContentType("application/pdf");
        // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
        // para fazer download do relatório troque 'inline' por 'attachment'
        response.setHeader("Content-Disposition", "inline; filename=Rank de Compras.pdf");

        // Faz a exportação do relatório para o HttpServletResponse
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }
    @PostMapping("/admin/relatorio/rank-venda")
    public void imprimirRankVenda(HttpServletResponse response) throws JRException, SQLException, IOException {

        Map<String, Object> parametros = new HashMap<>();

        // Pega o arquivo .jasper localizado em resources
        InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/RelatorioVenda.jasper");

        // Cria o objeto JaperReport com o Stream do arquivo jasper
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        // Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

        // Configura a respota para o tipo PDF
        response.setContentType("application/pdf");
        // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
        // para fazer download do relatório troque 'inline' por 'attachment'
        response.setHeader("Content-Disposition", "inline; filename=Rank de Vendas.pdf");

        // Faz a exportação do relatório para o HttpServletResponse
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }
}

