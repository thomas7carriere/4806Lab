package Application;

import Application.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressBookController {

    private AddressBookRepository repoBook;
    private BuddyInfoRepository repoBud;

    @Autowired
    public AddressBookController(AddressBookRepository repoBook, BuddyInfoRepository repoBud) {
        this.repoBud = repoBud;
        this.repoBook = repoBook;
    }
    @PostMapping("/addressbook")
    public AddressBook greeting(@RequestParam(value = "buddyName") String name,
                           @RequestParam(value = "buddyPhone") String phone,
                           @RequestParam(value = "buddyAddress") String address,
                           @RequestParam(value = "bookId") String id) {
        BuddyInfo bud = new BuddyInfo(name, phone, address);
        AddressBook book = repoBook.findById(Long.parseLong(id));
        book.addBuddy(bud);;
        repoBook.save(book);
        return book;
    }

}
