/**
 * Copyright Frederik M.J.V. 2010
 * Copyright Martin Sandsmark 2011 
 * LGPL 2.1 / GPLv3
 */

package com.lekebilen.quasseldroid.qtcomm;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class QDataOutputStream extends DataOutputStream{
	public QDataOutputStream(OutputStream base){
		super(base);
	}
	public void writeQVariant(QVariant<?> var){

	}
	public QVariant<?> readQVariant(){
		return null;
	}
	public void writeUInt(long num, int size) throws IOException{
		switch (size) {
		case 64:
			writeByte((int)(num>>56 & 0xFF));
			writeByte((int)(num>>48 & 0xFF));
			writeByte((int)(num>>40 & 0xFF));
			writeByte((int)(num>>32 & 0xFF));
		case 32:
			writeByte((int)(num>>24 & 0xFF));
			writeByte((int)(num>>16 & 0xFF));
		case 16:
			writeByte((int)(num>>8  & 0xFF));
		case 8:
			writeByte((int)(num     & 0xFF));
		}
	}
}
