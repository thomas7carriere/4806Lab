package Application;

import Application.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/addressbook/{bookId}")
    public AddressBook greeting(@RequestBody BuddyInfo bud, @PathVariable Long bookId) {
        AddressBook book = repoBook.findById(bookId).orElse(new AddressBook());
        book.addBuddy(bud);
        repoBook.save(book);
        return book;
    }

}
