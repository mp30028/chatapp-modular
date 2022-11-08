package com.zonesoft.utils;

import java.util.Objects;

public class ToStringHelpers {
	
	public static final void c(StringBuffer b) {b.append(",");}
	
	public static final void lb(StringBuffer b){b.append("{");}
	
	public static final void rb(StringBuffer b) {b.append("}");}
	
	public static final void t(StringBuffer b) {b.append("\t");}
	
	public static final void n(StringBuffer b) {b.append("\n");}
	
	public static final void q(StringBuffer b) {b.append("\"");}
	
	public static final void v(StringBuffer b, Object in) {
		if(Objects.isNull(in)) {
			b.append("null");
		}else{ 
			b.append(in.toString());
		}
	};
	
	public static final void qv(StringBuffer b, Object in) {
		if(Objects.isNull(in)) {
			b.append("null");
		}else{ 
			b.append("\""); 
			b.append(in.toString());
			b.append("\"") ;
		}
	}
}
