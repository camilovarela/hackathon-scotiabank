package com.hackathon.redcoins.beam.pipeline;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import com.hackathon.redcoins.beam.options.RedCoinsPipelineOptions;

public class MainPipelineBeam {

  private MainPipelineBeam() {}

  /**
   * 
   * @param args
   */
  public static void run(String... args) {

    PipelineOptionsFactory.register(RedCoinsPipelineOptions.class);
    RedCoinsPipelineOptions options =
        PipelineOptionsFactory.fromArgs(args).withValidation().as(RedCoinsPipelineOptions.class);
    Pipeline pipeline = Pipeline.create(options);

    RedCoinsPipelineBeam.run(pipeline, options);

    // Run the pipeline.
    pipeline.run().waitUntilFinish();
  }
}
