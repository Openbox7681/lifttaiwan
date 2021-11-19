package tw.gov.mohw.hisac.web.dao;

/**
 * Survey Variable
 */
public class SurveyVariable {
	/**
	 * Survey Level
	 */
	public static enum Level {
		/**
		 * 0:未使用功能
		 */
		Level0(0),
		/**
		 * 1:很不滿意
		 */
		Level1(1),
		/**
		 * 2:不太滿意
		 */
		Level2(2),
		/**
		 * 3:尚可
		 */
		Level3(3),
		/**
		 * 4:滿意
		 */
		Level4(4),
		/**
		 * 5:非常滿意
		 */
		Level5(5);

		private Integer value;

		private Level(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}
}