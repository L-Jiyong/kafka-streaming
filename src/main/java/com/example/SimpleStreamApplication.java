package com.example;

import com.example.CustomSerdes.AccountTXCount;
import com.example.CustomSerdes.CustomSerdes;
import com.example.CustomSerdes.TXCount;
import com.example.dataType.Tx_message_value;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;

import java.util.Properties;

import com.example.dataType.account_message_value;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

public class SimpleStreamApplication {

    private static String APPLICATION_NAME = "streams-application";
    private static String BOOTSTRAP_SERVERS = "210.109.60.230:10152";
    private static String STREAM_LOG = "stream_log";
    private static String STREAM_LOG_COPY = "stream_log_copy";

    private static KafkaStreams kafkaStreams;
    static final String inputTopic_2 = "dbserver1.testdb.accounts";
    static final String inputTopic = "dbserver1.testdb.TX_table";
    static final String outputTopic = "tx_count_topic";
    private static final Serde<String> STRING_SERDE = Serdes.String();

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_NAME);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        //데이터의 흐름으로 구성된 토폴로지를 정의할 빌더
//        final StreamsBuilder builder = new StreamsBuilder();
//
//        final KStream<String, account_message_value> account_source = builder
//                .stream(inputTopic_2, Consumed.with(Serdes.String(), CustomSerdes.account_info()));
//
//        final KTable<String, AccountTXCount> counts = account_source
//                .groupBy((key, value) -> value.getPayload().getUser_name())
//                .count()
//                .mapValues(AccountTXCount::new);
//        counts
//                .toStream()
//                .to(outputTopic, Produced.with(Serdes.String(), CustomSerdes.account_TXCount()));




        final StreamsBuilder tx_builder = new StreamsBuilder();

        final KStream<String, Tx_message_value> tx_source = tx_builder
                .stream(inputTopic, Consumed.with(Serdes.String(), CustomSerdes.MessageToJsonTXCount()));

        final KTable<String, TXCount> tx_counts = tx_source
                .groupBy((key, value) -> value.getPayload().getUser())
                .count()
                .mapValues(TXCount::new);
        tx_counts
                .toStream()
                .to(outputTopic, Produced.with(Serdes.String(), CustomSerdes.TXCount()));



        final Topology topology = tx_builder.build();

        final KafkaStreams streams = new KafkaStreams(topology, props);
        //final CountDownLatch latch = new CountDownLatch(1);

        streams.start();

    }

}