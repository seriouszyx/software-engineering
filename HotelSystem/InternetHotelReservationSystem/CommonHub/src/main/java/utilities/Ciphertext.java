package utilities;

/**
 * 
 * @author Byron Dong lastChangedBy Byron Dong updateTime 2016/12/9
 *
 */
public class Ciphertext {

	private String key = "1"; // 默认key值为1

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/6
	 * @param expression
	 *            传入需要加密的字符串ß
	 * @return String 返回加密后的字符串
	 */
	public String encrypt(String expression) {

		if (expression == null) {
			return null;
		}

		String result = "";
		int temp = 0;

		if (key.length() == 0) { // 代表不加密
			return expression;
		}

		for (int i = 0, j = 0; i < expression.length(); i++, j++) {

			if (j > key.length() - 1) {
				j = j % key.length();
			}

			temp = expression.codePointAt(i) + key.codePointAt(j);

			if (temp > 65535) {
				temp = temp % 65535;// temp - 33 = (temp - 33) % 95 ;
			}

			result = result + (char) temp;
		}
		return result;
	}

	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/6
	 * @param expression
	 *            传入需要解密的字符串
	 * @return String 返回解密后的字符串
	 */
	public String decode(String expression) {

		if (expression.equals(key)) {
			return null;
		} // 与解密的key值相同，无法解密

		String result = "";
		int temp;
		if (key.length() == 0) {
			return expression;
		}

		for (int i = 0, j = 0; i < expression.length(); i++, j++) {

			if (j > key.length() - 1) {
				j = j % key.length();
			}

			temp = (expression.codePointAt(i) + 65535 - key.codePointAt(j));

			if (temp > 65535) {
				temp = temp % 65535;// temp - 33 = (temp - 33) % 95 ;
			}
			result = result + (char) temp;
		}
		return result;
	}

	

}
