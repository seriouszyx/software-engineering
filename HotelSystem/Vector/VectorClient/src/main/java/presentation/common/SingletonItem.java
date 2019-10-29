package presentation.common;

import vo.ActivityPromotionVo;

/**
 * @author Molloh
 * @version 2016/11/25
 * @description 创建一个单例类，实现activeId的set和get
 */
public class SingletonItem {

    private String activateId;

    private String searchedId;

    private String hotelId;

    private String orderId;

    private ActivityPromotionVo vo;

    private static SingletonItem instance = new SingletonItem();

    private SingletonItem() {
        activateId = null;
    }

    public static SingletonItem getInstance() {
        return instance;
    }

    public String getActivateId() {
        return activateId;
    }

    public void setActivityPromotionVo(ActivityPromotionVo vo) {
        this.vo = vo;
    }

    public ActivityPromotionVo getActivityPromotionVo() {
        return this.vo;
    }

    public void setActivateId(String activateId) {
        this.activateId = activateId;
    }

    public void setSearchedId(String searchedId) {
        this.searchedId = searchedId;
    }

    public String getSearchedId() {
        return searchedId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelId() {
        return this.hotelId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return this.orderId;
    }

}
