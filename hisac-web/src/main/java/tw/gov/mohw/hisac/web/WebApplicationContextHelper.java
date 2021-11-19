package tw.gov.mohw.hisac.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 擴充不支援Spring的Function可以調用資源檔
 */
public class WebApplicationContextHelper implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	@Override
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @param beanName
	 *            資源檔名稱
	 * @return 資源檔
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}
