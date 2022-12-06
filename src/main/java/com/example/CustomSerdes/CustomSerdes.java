package com.example.CustomSerdes;
import com.example.dataType.Tx_message_value;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import com.example.dataType.account_message_value;
public final class CustomSerdes {
	private CustomSerdes() {}
	public static Serde<account_message_value> account_info() {
		JsonSerializer<account_message_value> serializer = new JsonSerializer<>();
		JsonDeserializer<account_message_value> deserializer = new JsonDeserializer<>(account_message_value.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

	public static Serde<AccountTXCount> account_TXCount() {
		JsonSerializer<AccountTXCount> serializer = new JsonSerializer<>();
		JsonDeserializer<AccountTXCount> deserializer = new JsonDeserializer<>(AccountTXCount.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

	public static Serde<Tx_message_value> MessageToJsonTXCount() {
		JsonSerializer<Tx_message_value> serializer = new JsonSerializer<>();
		JsonDeserializer<Tx_message_value> deserializer = new JsonDeserializer<>(Tx_message_value.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}

	public static Serde<TXCount> TXCount() {
		JsonSerializer<TXCount> serializer = new JsonSerializer<>();
		JsonDeserializer<TXCount> deserializer = new JsonDeserializer<>(TXCount.class);
		return Serdes.serdeFrom(serializer, deserializer);
	}
}