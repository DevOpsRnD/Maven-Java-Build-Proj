package com.cnk.siapi.config;

import java.util.Random;

public class RandomKeyConfig 
{
	public static String generateRandom() {
		String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    Random rand=new Random();
	    StringBuilder res=new StringBuilder();
	    for (int i = 0; i < 14; i++) {
	       int randIndex=rand.nextInt(aToZ.length()); 
	       res.append(aToZ.charAt(randIndex));            
	    }
	    return res.toString();
	}
}
