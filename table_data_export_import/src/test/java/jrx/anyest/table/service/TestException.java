package jrx.anyest.table.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestException {

    public static final Logger logger = LoggerFactory.getLogger(TestException.class);

	public static void main(String[] args) {
		try {
			int a = 1 / 0;
		} catch (Exception e) {
			logger.error("ERROR", e);

			//e.printStackTrace();
		}
	}
}