package com.temp.springcloud.environment.config;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.*;

class ObjectSerializer implements StreamSerializer<Object> {
	public int getTypeId() {
		return 1;
	}

	public void write(ObjectDataOutput objectDataOutput, Object object) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream((OutputStream) objectDataOutput);
		out.writeObject(object);
		out.flush();
	}

	public Object read(ObjectDataInput objectDataInput) throws IOException {
		ObjectInputStream in = new ObjectInputStream((InputStream) objectDataInput);
		try {
			return in.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException(e);
		}
	}

	public void destroy() {
	}
}