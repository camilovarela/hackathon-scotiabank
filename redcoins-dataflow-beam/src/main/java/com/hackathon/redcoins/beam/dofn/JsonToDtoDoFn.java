package com.hackathon.redcoins.beam.dofn;

import org.apache.beam.sdk.transforms.DoFn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.hackathon.redcoins.beam.constant.RedCoinsConstant;
import com.hackathon.redcoins.beam.dto.CustomerRequest;
import com.hackathon.redcoins.beam.dto.Transaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonToDtoDoFn {

  private static Gson gson = new Gson();

  public static class Run extends DoFn<String, Transaction> {
    
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 1L;

    @ProcessElement
    public void processElement(ProcessContext context) {

      log.info("{} Executing {}", RedCoinsConstant.PREFIX_BEAM_DOFN,
          JsonToDtoDoFn.class.getSimpleName());

      try {
        final Transaction[] transactionData = gson.fromJson(context.element(), Transaction[].class);
        for (Transaction dto : transactionData) {
          System.out.println("Leyendo transacción -> " + dto);
          
          CustomerRequest customerRequest = new CustomerRequest();
          long newRedCoins = (long) (dto.getAmount() / 2000);
          customerRequest.setRedCoins(newRedCoins);
          
          HttpEntity<CustomerRequest> requestEntity = new HttpEntity<CustomerRequest>(customerRequest, new HttpHeaders());
          
          RestTemplate restTemplate = new RestTemplate();
          restTemplate.exchange("http://localhost:8080/redcoins-api/rest/v1.0/customers/" + dto.getCustomerId(), HttpMethod.PUT, requestEntity, Object.class);
          context.output(dto);
        }
      } catch (Exception e) {
        log.error("{} There was an error converting JSon to DTO.",
            RedCoinsConstant.PREFIX_BEAM_DOFN_EX, e);
      }
    }
  }
}
