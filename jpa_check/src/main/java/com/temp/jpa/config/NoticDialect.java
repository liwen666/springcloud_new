package com.temp.jpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.Set;

@Component
public class NoticDialect extends AbstractProcessorDialect {

	private static final String DIALECT_NAME = "Portal Dialect";
	private static final String PREFIX = "portal";
	public static final int PROCESSOR_PRECEDENCE = 1000;

	@Value("${portal.devMode:true}")
	private boolean devMode;


	protected NoticDialect() {
		super(DIALECT_NAME, PREFIX, PROCESSOR_PRECEDENCE);
	}


	@Override
	public Set<IProcessor> getProcessors(String s) {
		return null;
	}
}