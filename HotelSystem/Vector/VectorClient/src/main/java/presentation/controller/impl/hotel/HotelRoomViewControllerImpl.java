package presentation.controller.impl.hotel;

import businessLogic.impl.HotelBlServiceImpl;
import businessLogic.service.HotelBlService;
import common.ResultMessage;
import common.RoomType;
import presentation.controller.service.hotel.HotelRoomViewControllerService;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description 更新酒店房间信息界面
 */
public class HotelRoomViewControllerImpl implements HotelRoomViewControllerService {
    private static HotelRoomViewControllerService INSTANCE = new HotelRoomViewControllerImpl();

    private HotelBlService hotelBlService;
    private String hotelId;

    public static HotelRoomViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    private HotelRoomViewControllerImpl() {
        hotelBlService = HotelBlServiceImpl.getInstance();
    }

    @Override
    public ResultMessage initializeRoom(RoomType type, int number, int price) {
        return hotelBlService.initializeRoom(hotelId, type, number, price);
    }

    @Override
    public ResultMessage checkoutRoom(RoomType type, int number) {
        return hotelBlService.checkoutRoom(type, number);
    }

    @Override
    public ResultMessage checkinRoom(RoomType type, int number) {
        return hotelBlService.bookRoom(type, number);
    }
}
