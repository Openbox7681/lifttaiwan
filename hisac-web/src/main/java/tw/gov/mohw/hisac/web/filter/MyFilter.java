package tw.gov.mohw.hisac.web.filter;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;


public class MyFilter {
	
	static final Pattern HTML_PATTERN = Pattern.compile("(?s)<[^>]*>(\\s*<[^>]*>)*");
	static final Pattern SCRIPT_PATTERN_0 = Pattern.compile("\0");
	static final Pattern SCRIPT_PATTERN_1 = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
	static final Pattern SCRIPT_PATTERN_2 = Pattern.compile("src\\s*=\\s*(\\\".*?\\\"|\\'.*?\\')",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	static final Pattern SCRIPT_PATTERN_3 = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
	static final Pattern SCRIPT_PATTERN_4 = Pattern.compile("<script(.*?)>",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	static final Pattern SCRIPT_PATTERN_5 = Pattern.compile("eval\\((.*?)\\)",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	static final Pattern SCRIPT_PATTERN_6 = Pattern.compile("expression\\((.*?)\\)",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	static final Pattern SCRIPT_PATTERN_7 = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
	static final Pattern SCRIPT_PATTERN_8 = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
	static final Pattern SCRIPT_PATTERN_9 = Pattern.compile("onload(.*?)=",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	
    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable formatted string
     */
    public String filterString(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-_]*+$");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable number formatted string
     */
    public String filterNumber(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("^[0-9]++$");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable date formatted string
     */
    public String filterDate(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("^[0-9-: ]*+$");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable character formatted string
     */
    public String filterChars(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("^[A-Za-z_-]++$");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable account formatted string
     */
    public String filterAccount(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-_\\.\\@]*+$");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable email formatted string
     */
    public String filterEmail(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable password formatted string
     */
    public String filterPassword(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9-_\\~\\!\\@\\$\\#\\.\\(\\)\\?\\%\\^\\*]*+$");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable telephone,mobilephone,fax formatted string
     */
    public String filterPhone(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-_\\+\\(\\)#\\. ]*+$");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable pure text string
     */
    public String filterHtml(String input) {
        if (input == null || input.isEmpty()) return "";
        return input.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable ono XSS issue string
     */
    public String stripXSS(String input) {
        if (input == null || input.isEmpty()) return "";

        String cleanValue = "";
        cleanValue = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Avoid null characters
        cleanValue = cleanValue.replaceAll("\0", "");

        // Avoid anything between script tags
        Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        // Avoid anything in a src='...' type of expression
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        // Remove any lonesome </script> tag
        scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        // Remove any lonesome <script ...> tag
        scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        // Avoid eval(...) expressions
        scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        // Avoid expression(...) expressions
        scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        // Avoid javascript:... expressions
        scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        // Avoid vbscript:... expressions
        scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        // Avoid onload= expressions
        scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

        return cleanValue;
    }

    /**
     * Using this to filter illegal characters from input
     * @param input the source
     * @return acceptable ip address
     */
    public String filterIP(String input) {
        if (input == null || input.isEmpty()) return "";
        Pattern pattern = Pattern.compile("^[0-9\\.:]*+$");
        if (!pattern.matcher(input).matches()) {
            return "";
        } else {
            return input;
        }
    }
    
    
	/**
	 * Using this to filter illegal characters from input
	 * 
	 * @param input
	 *            the source
	 * @return acceptable pure text string
	 */
	public static String filterHtmlForMalwareScan(String input) {
		return StringUtils.isBlank(input) ? "" : HTML_PATTERN.matcher(input).replaceAll(" ");
	}

	/**
	 * Using this to filter illegal characters from input
	 * 
	 * @param input
	 *            the source
	 * @return acceptable ono XSS issue string
	 */
	public static String stripXSSForMalwareScan(String input) {
		if (StringUtils.isBlank(input))
			return "";

		String cleanValue = Normalizer.normalize(input, Normalizer.Form.NFD);

		// Avoid null characters
		cleanValue = SCRIPT_PATTERN_0.matcher(cleanValue).replaceAll("");

		// Avoid anything between script tags
		cleanValue = SCRIPT_PATTERN_1.matcher(cleanValue).replaceAll("");

		// Avoid anything in a src='...' or src="..." type of expression
		cleanValue = SCRIPT_PATTERN_2.matcher(cleanValue).replaceAll("");

		// Remove any lonesome </script> tag
		cleanValue = SCRIPT_PATTERN_3.matcher(cleanValue).replaceAll("");

		// Remove any lonesome <script ...> tag
		cleanValue = SCRIPT_PATTERN_4.matcher(cleanValue).replaceAll("");

		// Avoid eval(...) expressions
		cleanValue = SCRIPT_PATTERN_5.matcher(cleanValue).replaceAll("");

		// Avoid expression(...) expressions
		cleanValue = SCRIPT_PATTERN_6.matcher(cleanValue).replaceAll("");

		// Avoid javascript:... expressions
		cleanValue = SCRIPT_PATTERN_7.matcher(cleanValue).replaceAll("");

		// Avoid vbscript:... expressions
		cleanValue = SCRIPT_PATTERN_8.matcher(cleanValue).replaceAll("");

		// Avoid onload= expressions
		cleanValue = SCRIPT_PATTERN_9.matcher(cleanValue).replaceAll("");

		return cleanValue;
	}
}
