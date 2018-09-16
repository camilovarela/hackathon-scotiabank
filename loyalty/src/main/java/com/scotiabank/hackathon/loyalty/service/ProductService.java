package com.scotiabank.hackathon.loyalty.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.scotiabank.hackathon.loyalty.domain.CustomerResponse;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
  
  @Value("${product.email.from}")
  private String fromEmail;

  @Value("${product.email.subject}")
  private String subject;

  @Value("${product.email.sendGridKey}")
  private String sendGridKey;
  
  @Value("classpath:templates/product-notification.html")
  private Resource emailTemplate;

  public void sendEmailConfirmation(CustomerResponse customer) {

    try {
      
      Email from = new Email(this.fromEmail, "[RedCoins] - Nuevo producto.");
      String subject = this.subject;
      Email to = new Email(customer.getEmail());
      
      String message = new String(Files.readAllBytes(Paths.get(this.emailTemplate.getURI())));
      message = message.replace("{username}", customer.getFullName());
      message = message.replace("{redcoins}", String.valueOf(customer.getRedCoins()));

      Content content = new Content("text/html", message);
      Mail mail = new Mail(from, subject, to, content);

      SendGrid sendGrid = new SendGrid(this.sendGridKey);
      Request request = new Request();
      
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sendGrid.api(request);
      log.info("Status code: {}", response.getStatusCode());
      log.info("Body: {}", response.getBody());
    } catch (IOException ex) {
      log.error("There was an error sending the email.", ex);
    }
  }
}
