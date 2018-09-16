package com.hackathon.redcoins.beam.pipeline;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.transforms.ParDo;
import com.google.pubsub.v1.TopicName;
import com.hackathon.redcoins.beam.constant.DataflowState;
import com.hackathon.redcoins.beam.constant.RedCoinsConstant;
import com.hackathon.redcoins.beam.dofn.JsonToDtoDoFn;
import com.hackathon.redcoins.beam.options.RedCoinsPipelineOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedCoinsPipelineBeam {

  public static void run(Pipeline pipeline, RedCoinsPipelineOptions merakiOptions) {

    log.info("Starting {}", RedCoinsPipelineBeam.class.getSimpleName());

    TopicName topic =
        TopicName.create(RedCoinsConstant.PROJECT_ID, RedCoinsConstant.REDCOINS_TOPIC_NAME);

    pipeline
        .apply(
            String.format(DataflowState.READ_FROM_PUBSUB.getDescription(),
                RedCoinsConstant.REDCOINS_TOPIC_NAME),
            PubsubIO.readStrings().fromTopic(topic.toString()))

        .apply(DataflowState.JSON_TO_DTO.getDescription(), ParDo.of(new JsonToDtoDoFn.Run()));
  }
}
