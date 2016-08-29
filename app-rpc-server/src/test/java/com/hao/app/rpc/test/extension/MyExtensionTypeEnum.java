package com.hao.app.rpc.test.extension;

public enum MyExtensionTypeEnum {

	DEFAULT("default"), DEMO("demo");
	
	private String value;

	private MyExtensionTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
