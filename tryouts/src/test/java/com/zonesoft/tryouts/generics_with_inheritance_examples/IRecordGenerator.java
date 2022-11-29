package com.zonesoft.tryouts.generics_with_inheritance_examples;

public  interface   IRecordGenerator <G, T>  {
	public G id();
	public G id(String suppliedValue);
	public G withDefaults();
	public T generate();

}
