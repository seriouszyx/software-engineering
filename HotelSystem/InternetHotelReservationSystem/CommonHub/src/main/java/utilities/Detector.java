package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.inputException.InvalidInputException;
import exception.inputException.InvalidLengthInputException;
import exception.inputException.PasswordInputException;
import exception.inputException.SpecialCharacterException;

/**
 * 
 * @author Byron Dong lastChangedBy Byron Dong updateTime 2016/12/6
 *
 */
public class Detector {

	private String expression; //存放正则表达式
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/6
	 * @param express
	 *            传入需要检查不合法符号的ID
	 * @param length
	 *            id的长度
	 * @return boolean 是否符合要求规范
	 */
	public  boolean idDetector(String express, int length) throws SpecialCharacterException{

		if(express.length()!=length){
			return false; //得到与指定长度匹配的数字
		}
		
		expression = "[0-9]{"+String.valueOf(length)+"}";
		
		if(!this.getResultOfDetector(express)){
			throw new SpecialCharacterException();
		} 
		return true;
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/6
	 * @param express
	 *            传入需要检查不合法符号的密码
	 * @return boolean 是否符合要求规范
	 */
	public  boolean passwordDetector(String express) throws PasswordInputException{
		
		expression = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
		
		if(!this.getResultOfDetector(express)){
			throw new PasswordInputException(); //密码必须是数字与字母的组合
		}
		return true;
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/6
	 * @param express
	 *            传入需要检查不合法符号的电话
	 * @return boolean 是否符合要求规范
	 */
	public boolean phoneDetector(String express) throws InvalidLengthInputException{
		expression = "[0-9]{11}";
		
		if(!this.getResultOfDetector(express)){
			throw new InvalidLengthInputException();
		}
		return true;
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/6
	 * @param express
	 *            传入需要检查不合法符号的信息内容
	 * @return boolean 是否符合要求规范
	 */
	public boolean infoDetector(String express) throws InvalidInputException{ 
		expression = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
		
		if(!this.getResultOfDetector(express)){
			throw new InvalidInputException(); 	//统一检测填写信息内容中的不合法标识符
		}
		return true;
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/6
	 * @param express
	 *            传入需要检查不合法符号的折扣
	 * @return boolean 是否符合要求规范
	 */
	public boolean discoutDetector(String express)throws InvalidInputException{ //折扣只允许单数字
		expression = "[0-9]{1}";
		
		if(!this.getResultOfDetector(express)){
			throw new InvalidInputException();
		}
		return true;
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/14
	 * @param express
	 *            传入需要检查不合法符号的信用值
	 * @return boolean 是否符合要求规范
	 */
	public boolean chargeDetector(String express) throws InvalidInputException{
		expression = "[0-9]{1,10}";
		
		if(!this.getResultOfDetector(express)){
			throw new InvalidInputException();
		}
		return true;
	}
	
	/**
	 * @author Byron Dong
	 * @lastChangedBy Byron Dong
	 * @updateTime 2016/12/6
	 * @param express
	 *            传入需要检查不合法符号的表达式
	 * @return boolean 是否符合要求规范
	 */
	private boolean getResultOfDetector(String express){
		
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(express);
		boolean result = matcher.matches(); 
		
		return result; //得到匹配正则表达式的结果
	}
	
}
