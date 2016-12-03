package com.andrios.prt;

public class Instruction {

	private static final long serialVersionUID = -1538116430747812374L;
	String url;
	String icon;
	String name;
    String ssic;

	public Instruction(String name, String ssic, String url){
		this.name = name;
        this.ssic = ssic;
		this.url = url;
	}
}
