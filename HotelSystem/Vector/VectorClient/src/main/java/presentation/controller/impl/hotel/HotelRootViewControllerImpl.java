package presentation.controller.impl.hotel;

import presentation.controller.service.hotel.HotelRootViewControllerService;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class HotelRootViewControllerImpl implements HotelRootViewControllerService {
    private static HotelRootViewControllerService INSTANCE = new HotelRootViewControllerImpl();
    private String hotelId;

    private HotelRootViewControllerImpl() {}

    public static HotelRootViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setHotelId(String Id) {
        this.hotelId = Id;
    }

    @Override
    public String getHotelName() {
        return null;
    }
}
