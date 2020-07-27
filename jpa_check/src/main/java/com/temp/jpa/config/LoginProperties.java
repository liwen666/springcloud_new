package com.temp.jpa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "portal.login")
public class LoginProperties {

	private Platform platform = new Platform();

	private Rest rest = new Rest();

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Rest getRest() {
		return rest;
	}

	public void setRest(Rest rest) {
		this.rest = rest;
	}

	/**
	 * 平台特有的登录配置
	 * 
	 * @author JACK
	 *
	 */
	public static class Platform {

		private String yearsUrl;

		private String loginUrl;

		private String userInfoUrl;

		private String logoutUrl;

		private String  rolesUrl;

		public String getRolesUrl() {
			return rolesUrl;
		}

		public void setRolesUrl(String rolesUrl) {
			this.rolesUrl = rolesUrl;
		}

		public String getYearsUrl() {
			return yearsUrl;
		}

		public void setYearsUrl(String yearsUrl) {
			this.yearsUrl = yearsUrl;
		}

		public String getLoginUrl() {
			return loginUrl;
		}

		public void setLoginUrl(String loginUrl) {
			this.loginUrl = loginUrl;
		}

		public String getUserInfoUrl() {
			return userInfoUrl;
		}

		public void setUserInfoUrl(String userInfoUrl) {
			this.userInfoUrl = userInfoUrl;
		}

		public String getLogoutUrl() {
			return logoutUrl;
		}

		public void setLogoutUrl(String logoutUrl) {
			this.logoutUrl = logoutUrl;
		}
	}

	public static class Rest {

		private String yearsUrl;

		private String loginUrl;

		private String logoutUrl;

		/**
		 * 获取财年列表的URL，服务端不应该对该请求做权限校验<br/>
		 * 如果该值不设置，则认为登录不需要财年信息，前台登录界面隐藏财年下拉选择
		 * 
		 * @return
		 */
		public String getYearsUrl() {
			return yearsUrl;
		}

		public void setYearsUrl(String yearsUrl) {
			this.yearsUrl = yearsUrl;
		}

		/**
		 * 获取用户登录信息的URL，支持username,password,year占位符<br/>
		 * eg:http://out.service.com/login/{username}/{password}/{year}<br/>
		 * 用户登录时会使用实际输入的登录信息替换占位符。<br/>
		 *
		 * @return
		 */
		public String getLoginUrl() {
			return loginUrl;
		}

		public void setLoginUrl(String loginUrl) {
			this.loginUrl = loginUrl;
		}

		/**
		 * 获取用户登录信息的URL，支持username,tokenid占位符<br/>
		 * eg:http://out.service.com/logout/{username}/{tokenid}<br/>
		 * 用户退出登录时会使用实际信息替换占位符<br/>
		 * 服务调用成功不必返回,调用失败，抛出异常
		 * 
		 * @return
		 */
		public String getLogoutUrl() {
			return logoutUrl;
		}

		public void setLogoutUrl(String logoutUrl) {
			this.logoutUrl = logoutUrl;
		}
	}

}
