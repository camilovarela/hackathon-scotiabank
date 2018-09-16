package com.scotiabank.hackathon.loyalty.utils;

import java.util.HashMap;
import java.util.Map;
import com.scotiabank.hackathon.loyalty.domain.CustomerResponse;

public class BuilderUtil {

  private BuilderUtil() {

  }

  public static Map<Long, CustomerResponse> buildInitialData() {

    Map<Long, CustomerResponse> initialData = new HashMap<>();
    initialData.put(1L, CustomerResponse.builder().id(1L).fullName("Laura Cano").email("laucc25@gmail.com")
        .redCoins(7000L).build());
    initialData.put(2L, CustomerResponse.builder().id(2L).fullName("Camilo Varela").email("ing.camilovarela@gmail.com")
        .redCoins(4500L).build());
    initialData.put(3L, CustomerResponse.builder().id(3L).fullName("Marcelino Herrera").email("herrermr@colpatria.com")
        .redCoins(3000L).build());
    initialData.put(4L, CustomerResponse.builder().id(4L).fullName("Oswaldo Álvarez").email("alvareos@colpatria.com")
        .redCoins(4000L).build());
    initialData.put(5L, CustomerResponse.builder().id(5L).fullName("Juliana Adib").email("adibh@colpatria.com")
        .redCoins(5000L).build());
    initialData.put(6L, CustomerResponse.builder().id(6L).fullName("Andrés Arévalo").email("arevalca@colpatria.com")
        .redCoins(6000L).build());
    initialData.put(7L, CustomerResponse.builder().id(7L).fullName("Paola Vásquez").email("vasquep@colpatria.com")
        .redCoins(5000L).build());
    initialData.put(8L, CustomerResponse.builder().id(8L).fullName("Camilo Jiménez").email("camilo@mercadoni.com")
        .redCoins(5000L).build());
    initialData.put(9L, CustomerResponse.builder().id(9L).fullName("Libardo Lara").email("llara@co.ibm.com")
        .redCoins(5000L).build());
    initialData.put(10L, CustomerResponse.builder().id(10L).fullName("Sergio V").email("sergio@co.ibm.com")
        .redCoins(5000L).build());
    return initialData;
  }
}
