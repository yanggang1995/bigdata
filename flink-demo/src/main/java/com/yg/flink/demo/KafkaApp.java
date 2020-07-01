package com.yg.flink.demo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yg.flink.demo.entity.DbMovie;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.core.fs.Path;
import org.apache.flink.formats.parquet.avro.ParquetAvroWriters;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/5/9 11:18
 **/
public class KafkaApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(10*60*1000L);
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers", "192.168.90.96:9092");
        prop.setProperty("group.id", "yg_test3");
        DataStreamSource<String> kafkaStream = env.addSource(new FlinkKafkaConsumer<>("db_movie", new SimpleStringSchema(), prop));
        kafkaStream.print();

        // parquet Sink

        StreamingFileSink<DbMovie> sFileSink = StreamingFileSink
                .forBulkFormat(new Path("hdfs://myha/tmp/yg/flink"), ParquetAvroWriters.forReflectRecord(DbMovie.class))
                .withRollingPolicy(OnCheckpointRollingPolicy.build())
                .build();

        kafkaStream.map(v -> {
            JsonObject obj = JsonParser.parseString(v).getAsJsonObject();
            DbMovie dbMovie = new DbMovie();
            String cover = obj.get("cover").getAsString();
            String title = obj.get("title").getAsString();
            dbMovie.setCover(cover == null ? "" : cover);
            dbMovie.setTitle(title == null ? "" : title);
            return dbMovie;
        }).addSink(sFileSink);

        env.execute("db_movie");
    }
}
