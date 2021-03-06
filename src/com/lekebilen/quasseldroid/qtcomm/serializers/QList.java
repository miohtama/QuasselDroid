/**
 * Copyright Frederik M.J.V. 2010 
 * Copyright Martin Sandsmark 2011
 * LGPL 2.1 / GPLv3
 */

package com.lekebilen.quasseldroid.qtcomm.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lekebilen.quasseldroid.qtcomm.DataStreamVersion;
import com.lekebilen.quasseldroid.qtcomm.QDataInputStream;
import com.lekebilen.quasseldroid.qtcomm.QDataOutputStream;
import com.lekebilen.quasseldroid.qtcomm.QMetaType;
import com.lekebilen.quasseldroid.qtcomm.QMetaTypeRegistry;
import com.lekebilen.quasseldroid.qtcomm.QMetaTypeSerializer;
import com.lekebilen.quasseldroid.qtcomm.QVariant;

public class QList<T> implements QMetaTypeSerializer<List<T>> {
	String elementType;
	public QList(String elementType){
		this.elementType = elementType;
	}
	protected List<T> makeList(){
		return new ArrayList<T>();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> unserialize(QDataInputStream stream, DataStreamVersion version)
			throws IOException {
		List<T> list = makeList();
		int len = (int)stream.readUInt(32);
		for(int i=0;i<len;i++){
			list.add((T)QMetaTypeRegistry.instance().getTypeForName(elementType).getSerializer().unserialize(stream, version));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serialize(QDataOutputStream stream, List<T> data,
			DataStreamVersion version) throws IOException {
		stream.writeUInt(data.size(), 32);
		for(T element: data){
			((QMetaTypeSerializer<Object>)QMetaTypeRegistry.instance().getTypeForName(elementType).getSerializer()).serialize(stream, (Object)element, version);
		}
	}
}
