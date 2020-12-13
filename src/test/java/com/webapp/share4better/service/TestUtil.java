package com.webapp.share4better.service;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.model.Contact;
import com.webapp.share4better.model.Food;
import com.webapp.share4better.model.Profile;
import com.webapp.share4better.repository.IFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestUtil {

    @Autowired
    private IAddressService addressService;
    @Autowired
    private  IContactService contactService;
    @Autowired
    private IProfileService profileService;

    @Autowired
    private IFoodRepository repository;
    //TODO: AMANDA
    @Autowired
    private IFoodService foodService;

    private Contact contact_1 = createContact(999999991,"123-456-7891","987-543-1234");
    private Contact contact_2 = createContact(999999992,"987654321","987654321");

    private Address address_1 = createAddress(999999991,"300001 Mill shop Drive","charlotte", "NC", 28222,"USA","123 Mill Drive","charlotte", "NC", 28111,"USA");
    private Address address_2 = createAddress(999999992,"300001 Mill shop Drive","charlotte", "NC", 28222,"USA","123 Mill Drive","charlotte", "NC", 28111,"USA");

    private Profile profile_1 = createProfile(99999999,"FirstName LastName","test@test.com","123456");

    //TODO: AMANDA
    private Food food_1 = createFood(111111111, 9999, 8888, "bagels", "bread", "10", "fresh");
    private Food food_2 = createFood(111111112, 9991, 8881, "squash", "vegetable", "5", "good");

    public void addAddressTestData(){
        addressService.updateAddressInfo(address_1);
        addressService.updateAddressInfo(address_2);
    }

    public void removeAddressTestData(){
        addressService.removeAddress(address_1);
        addressService.removeAddress(address_2);

    }

    public void addContactTestData(){
        contactService.updateContactInfo(contact_1);
        contactService.updateContactInfo(contact_2);
    }

    public void removeContactTestData(){
        contactService.removeContactInfo(contact_1);
        contactService.removeContactInfo(contact_2);
    }

    public void removeFoodListTestData(){
        repository.delete(food_1);
        repository.delete(food_2);
    }


    public void addProfileTestData(){
        profileService.addUser(profile_1);
    }

    public void removeProfileTestData(){
        profileService.removeUser(99999999);
    }

    //TODO: AMANDA
    public void addFoodListTestData() {
        foodService.addFood(food_1);
        foodService.addFood(food_2);
    }

    //TODO:AMANDA, ask if we need to remove food like profile and address
    //public void removeFoodTestListData() { foodService.removeFood(); }

    public Address createAddress(int id, String home_address, String home_city, String home_state, int home_zip, String home_country, String work_address, String work_city, String work_state, int work_zip, String work_country){
        Address address = new Address();
        address.setId(id);
        address.setHome_address(home_address);
        address.setHome_city(home_city);
        address.setHome_state(home_state);
        address.setHome_zip(home_zip);
        address.setHome_country(home_country);

        address.setWork_address(work_address);
        address.setWork_city(work_city);
        address.setWork_state(work_state);
        address.setWork_zip(work_zip);
        address.setWork_country(work_country);
        return address;
    }

    public Contact createContact(int id, String phone, String additional){
        Contact contact = new Contact();
        contact.setId(id);
        contact.setPhone_number(phone);
        contact.setAdditional_number(additional);
        return contact;
    }

    public Profile createProfile(int id, String userName, String email, String password){
        Profile profile = new Profile();
        profile.setUser_id(id);
        profile.setUser_name(userName);
        profile.setUser_email(email);
        profile.setUser_password(password);
        return profile;
    }

    //TODO: AMANDA
    public Food createFood(int id, int contributorId, int receiverId,
                           String name, String type, String quantity, String quality){
        Food food = new Food();
        food.setId(id);
        food.setContributorID(contributorId);
        food.setReceiverID(receiverId);
        food.setName(name);
        food.setType(type);
        food.setQuantity(quantity);
        food.setQuality(quality);
        return food;
    }

}
