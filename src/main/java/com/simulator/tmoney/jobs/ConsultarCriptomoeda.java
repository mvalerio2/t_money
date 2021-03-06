package com.simulator.tmoney.jobs;


import com.simulator.tmoney.model.Criptomoeda;
import com.simulator.tmoney.model.HistoricoCotacao;
import com.simulator.tmoney.service.CriptomoedaService;
import com.simulator.tmoney.service.HistoricoCotacaoService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;

@Component
public class ConsultarCriptomoeda implements Job {

    @Autowired
    private CriptomoedaService criptomoedaService;

    @Autowired
    private HistoricoCotacaoService historicoCotacaoService;

    public void execute(JobExecutionContext context) {

        URL url = null;
        try {
            url = new URL("https://www.mercadobitcoin.net/api/BTC/ticker/");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(15000);
        connection.connect();

            String responseJson = inputStreamToString(connection.getInputStream());
            System.out.println(responseJson);
            connection.disconnect();

            String [] ticker = responseJson.split(",");

            String last = ticker[3].replaceAll("\"last\": \"","");
            last = last.replaceAll("\"","");

            Criptomoeda criptomoeda = criptomoedaService.findByNome("BitCoin");

            System.out.println(criptomoeda.getNome());

            System.out.println(last);

            last = last.replaceAll( "last:" , "" );
            Double valor = Double.parseDouble(last);

            HistoricoCotacao historicoCotacao = new HistoricoCotacao();
            historicoCotacao.setCriptomoeda(criptomoeda);
            historicoCotacao.setDataHora(new Date());
            historicoCotacao.setValor(valor);

            historicoCotacaoService.save(historicoCotacao);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String inputStreamToString(InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
