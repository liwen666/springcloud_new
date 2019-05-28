package com.temp.jpa.config;

import com.temp.jpa.jpa.biz.impl.NoticBiz;
import com.temp.jpa.jpa.entity.Notic;
import com.temp.jpa.jpa.entity.ResFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.*;

@Component
public class NoticDialect extends AbstractProcessorDialect {

	private static final String DIALECT_NAME = "Portal Dialect";
	private static final String PREFIX = "portal";
	public static final int PROCESSOR_PRECEDENCE = 1000;

	@Value("${portal.devMode:true}")
	private boolean devMode;

	@Autowired
	private NoticBiz noticBiz;

	protected NoticDialect() {
		super(DIALECT_NAME, PREFIX, PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		List<Notic> portlets = noticBiz.listEntities();
		List<String> jsFiles = new ArrayList<>();
		List<String> cssFiles = new ArrayList<>();
		String suffix = "";
		if (devMode) {
			suffix += "?v=" + new Date().getTime();
		}
		Map<String, Boolean> loadMap = new HashMap<>();
		for (Notic notic : portlets) {
			List<ResFile> files = notic.getResFiles();
			for (ResFile resFile : files) {
				if (resFile.isThird()) {
					// 第三方的只加载一次
					if (loadMap.containsKey(resFile.getPath())) {
						continue;
					} else {
						loadMap.put(resFile.getPath(), true);
					}
					if (ResFile.CSS.equals(resFile.getType())) {
						cssFiles.add(resFile.getPath());
					} else {
						jsFiles.add(resFile.getPath());
					}
				} else {
					if (ResFile.CSS.equals(resFile.getType())) {
						cssFiles.add("portlets/" + notic.getCode() + "/" + resFile.getPath() + suffix);
					} else {
						jsFiles.add("portlets/" + notic.getCode() + "/" + resFile.getPath() + suffix);
					}
				}
			}
			jsFiles.add("portlets/" + notic.getCode() + "/portlet.js" + suffix);
		}
		Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new ResFilesTagProcessor(dialectPrefix, cssFiles, jsFiles));
		return processors;
	}

	class ResFilesTagProcessor extends AbstractElementTagProcessor {

		private static final String TAG_NAME = "resfiles";
		private static final int PRECEDENCE = 10000;

		private List<String> jsFiles = new ArrayList<>();
		private List<String> cssFiles = new ArrayList<>();

		public ResFilesTagProcessor(String dialectPrefix, List<String> cssFiles, List<String> jsFiles) {
			super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
			this.cssFiles = cssFiles;
			this.jsFiles = jsFiles;
		}

		@Override
		protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
				IElementTagStructureHandler structureHandler) {
			String type = tag.getAttributeValue("type");
			final IModelFactory modelFactory = context.getModelFactory();
			final IModel model = modelFactory.createModel();
			if (ResFile.CSS.equals(type)) {
				for (String cssFile : cssFiles) {
					Map<String, String> attrs = new HashMap<>();
					attrs.put("rel", "stylesheet");
					attrs.put("href", cssFile);
					model.add(modelFactory.createOpenElementTag("link", attrs, AttributeValueQuotes.DOUBLE, false));
					model.add(modelFactory.createCloseElementTag("link"));
					structureHandler.replaceWith(model, false);
				}
			} else {
				for (String jsFile : jsFiles) {
					Map<String, String> attrs = new HashMap<>();
					attrs.put("type", "text/javascript");
					attrs.put("src", jsFile);
					model.add(modelFactory.createOpenElementTag("script", attrs, AttributeValueQuotes.DOUBLE, false));
					model.add(modelFactory.createCloseElementTag("script"));
					structureHandler.replaceWith(model, false);
				}
			}
		}

	}

}