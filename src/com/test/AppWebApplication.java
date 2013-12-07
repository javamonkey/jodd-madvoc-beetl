package com.test;

import jodd.madvoc.WebApplication;
import jodd.madvoc.component.ResultsManager;
import jodd.madvoc.config.AutomagicMadvocConfigurator;
import jodd.madvoc.config.MadvocConfigurator;

public class AppWebApplication extends WebApplication {
	protected void initResults(ResultsManager actionManager) {
		actionManager.register(new BeetlActionResult());
	}
	
	public void configure(MadvocConfigurator configurator) {
		AutomagicMadvocConfigurator auto = (AutomagicMadvocConfigurator)configurator;
		auto.setExcludedJars("**/beetl.1.25.03.jar");
		super.configure(configurator);
	}

}
