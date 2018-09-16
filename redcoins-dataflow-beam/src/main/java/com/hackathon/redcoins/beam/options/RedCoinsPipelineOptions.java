package com.hackathon.redcoins.beam.options;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import com.hackathon.redcoins.beam.constant.RedCoinsConstant;

public interface RedCoinsPipelineOptions extends PipelineOptions {

  @Description(RedCoinsConstant.DESCRIPTION_MEMCACHE_URL)
  @Default.String(RedCoinsConstant.DEFAULT_MEMCACHE_URL)
  String getMemcacheUrl();

  void setMemcacheUrl(String memcacheUrl);
}
