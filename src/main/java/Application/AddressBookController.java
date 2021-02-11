package Application;

import Application.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressBookController {

    private AddressBookRepository repoBook;
    private BuddyInfoRepository repoBud;

    @Autowired
    public AddressBookController(AddressBookRepository repoBook, BuddyInfoRepository repoBud) {
        this.repoBud = repoBud;
        this.repoBook = repoBook;
    }

    /*@GetMapping("/addressbook/{id}")
    public AddressBook createAddressBook(@PathVariable String id) {
        AddressBook book = repoBook.findById(Long.parseLong(id));
        if(book == null) {
            book = new AddressBook();
            repoBook.save(book);
        }
        repoBook.save(book);
        return book;
    }*/
    //I guess I don't need these?
    @GetMapping("addressbook/addbuddy/{id}")
    public BuddyInfo addBuddy(@PathVariable String id, @RequestParam(value = "name", defaultValue = "noName") String name, @RequestParam(value = "phone", defaultValue = "noPhone") String phone) {
        AddressBook book = repoBook.findById(Long.parseLong(id));
        if(book == null) {
            book = new AddressBook();
            System.out.println("I guess the id was null!");
        }
        //so the buddyInfo might actually need a reference to the addressbook it's in
        //try making the buddyInfo's first then adding th
        BuddyInfo bud = new BuddyInfo(name,phone);
        book.addBuddy(bud);
        repoBook.save(book);
        return bud;
    }
}