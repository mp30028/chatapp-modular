package com.zonesoft.utils.data_generators;

public  interface   IRecordGenerator <G, T>  {
	public G id();
	public G id(String suppliedValue);
	public G withDefaults();
	public T generate();

}
