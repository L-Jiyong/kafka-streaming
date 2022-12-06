package com.example.dataType;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class TxTable {
	private String TX_id;
	private String user;
	private String amount;
	private String target;
}