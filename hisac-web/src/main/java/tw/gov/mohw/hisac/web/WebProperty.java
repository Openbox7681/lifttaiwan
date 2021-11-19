package tw.gov.mohw.hisac.web;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Property安全性擴充
 */
public class WebProperty extends PropertyPlaceholderConfigurer {

	final static Logger logger = LoggerFactory.getLogger(WebProperty.class);
	final static String CodeParameter = "ZK5P789RHNPY6BYX";
	final static String IvParameter = "3MYRZDDT2T89NNGD";

	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		try {
			String MAIL_CODE = props.getProperty("mail_code");
			if (MAIL_CODE != null) {
				props.setProperty("mail_code", WebCrypto.aesDecrypt(CodeParameter, IvParameter, MAIL_CODE));
			}

			String code = props.getProperty("sqlserver_code");
			if (code != null) {
				props.setProperty("sqlserver_code", WebCrypto.aesDecrypt(CodeParameter, IvParameter, code));
			}

			String PFX_CODE = props.getProperty("pfxCode");
			if (PFX_CODE != null) {
				props.setProperty("pfxCode", WebCrypto.aesDecrypt(CodeParameter, IvParameter, PFX_CODE));
			}

			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			////e.printStackTrace();
			//logger.error(e.getMessage());
			throw new BeanInitializationException(e.getMessage());
		}
	}
}
