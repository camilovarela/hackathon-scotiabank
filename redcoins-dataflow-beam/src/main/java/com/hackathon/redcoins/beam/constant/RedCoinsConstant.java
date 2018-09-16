package com.hackathon.redcoins.beam.constant;

import java.time.ZoneId;

public final class RedCoinsConstant {

  /**
   * Private constructor in order to avoid its implementation
   */
  private RedCoinsConstant() {}

  public static final String PREFIX_BEAM_DOFN = "[ DoFn ] ->";

  public static final String PREFIX_BEAM_DOFN_EX = "[ DoFnException ] ->";

  public static final String DEFAULT_MEMCACHE_URL = "";

  public static final String DESCRIPTION_MEMCACHE_URL = "The endpoint URL of memcache API.";

  public static final ZoneId LOCAL_ZONE_ID = ZoneId.of("America/Bogota");

  public static final String PROJECT_ID = "backbase-as-a-service";

  public static final String REDCOINS_TOPIC_NAME = "redcoins-topic";
}
