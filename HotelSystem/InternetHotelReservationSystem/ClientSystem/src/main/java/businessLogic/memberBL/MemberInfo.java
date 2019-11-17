package businessLogic.memberBL;

import utilities.enums.MemberType;
import vo.MemberVO;

/**
 * 
 * @author 董金玉
 * lastChangedBy 董金玉
 * updateTime 2016/11/27
 *
 */
public class MemberInfo {

	private MemberType common = MemberType.COMMON;

	private MemberType enterprise = MemberType.ENTERPRISE;

	private MemberType bothMember = MemberType.BOTH;

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param memberVO，memberType从member传过来的memberInfo载体和指定会员类型
	 * @return 是否为普通会员
	 */
	public boolean isCommonMember(MemberVO memberVO, MemberType memberType) {
		if (memberType != this.common) {
			return false;
		}
		if (memberVO.birthday == null) {
			return false;
		}
		return true;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param memberVO，memberType从member传过来的memberInfo载体和指定会员类型
	 * @return 是否为企业会员
	 */
	public boolean isEnterpriseMember(MemberVO memberVO, MemberType memberType) {
		if (memberType != this.enterprise) {
			return false;
		}
		if (memberVO.enterprise == null) {
			return false;
		}
		return true;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param memberVO，memberType从member传过来的memberInfo载体和指定会员类型
	 * @return 是否为双会员（普通和企业）
	 */
	public boolean isBothMember(MemberVO memberVO, MemberType memberType) {
		if (memberType != this.bothMember) {
			return false;
		}
		if (memberVO.birthday != null && memberVO.enterprise != null) {
			return true;
		} else
			return false;
	}
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/27
	 * @param memberVO 从member传过来的memberInfo载体
	 * @return 对应会员信息的会员类型
	 */
	public MemberType getMemberType(MemberVO memberVO){
		
		if (memberVO.birthday != null && memberVO.enterprise != null) {
			return this.bothMember;
		}

		if (memberVO.birthday != null) {
			return this.common;
		}

		if (memberVO.enterprise != null) {
			return this.enterprise;
		}
		
		return null;
	}

}
