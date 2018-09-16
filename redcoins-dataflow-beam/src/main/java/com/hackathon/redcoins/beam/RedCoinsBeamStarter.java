package com.hackathon.redcoins.beam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hackathon.redcoins.beam.pipeline.MainPipelineBeam;

/**
 * This class contains the main method which is used to execute the pipeline. In the arguments we
 * can send the runner that will execute all process.
 */
@SpringBootApplication
public class RedCoinsBeamStarter {

  public static void main(String[] args) {
    SpringApplication.run(RedCoinsBeamStarter.class);
    initPipelines(args);
  }

  /**
   * Starts the pipelines.
   * 
   * @param args The command line arguments.
   */
  private static void initPipelines(String... args) {
    Thread mainBeamThread = new Thread(new Runnable() {
      public void run() {
        MainPipelineBeam.run(args);
      }
    }, "mainBeamThread");
    mainBeamThread.start();
  }
}
