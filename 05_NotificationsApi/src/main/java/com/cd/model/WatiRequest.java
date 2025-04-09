package com.cd.model;

import java.util.List;

import lombok.Data;

@Data
public class WatiRequest {

	private String templete_name;
	private String broadcast_name;
	private List<WatiParameters> parameters;
}
