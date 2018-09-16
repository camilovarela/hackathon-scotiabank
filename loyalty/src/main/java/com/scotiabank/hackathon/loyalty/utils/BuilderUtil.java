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
    initialData.put(3L, CustomerResponse.builder().id(3L).fullName("").email("")
        .redCoins(3000L).build());
    initialData.put(4L, CustomerResponse.builder().id(4L).fullName("").email("")
        .redCoins(4000L).build());
    initialData.put(5L, CustomerResponse.builder().id(5L).fullName("").email("")
        .redCoins(5000L).build());
    return initialData;
  }
}
