package Application;

import Application.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class AddressBookOldController {
    private AddressBookRepository repoBook;
    private BuddyInfoRepository repoBud;

    @Autowired
    public AddressBookOldController(AddressBookRepository repoBook, BuddyInfoRepository repoBud) {
        this.repoBud = repoBud;
        this.repoBook = repoBook;
    }

    //this will show a nice view of the Book with "contents.html"
    @GetMapping("/addressbookold/{bookId}")
    public String addressBookContents(@PathVariable String bookId, Model model) {

        AddressBook book = repoBook.findById(Long.parseLong(bookId));
        //might want this to return an "error template"? if something goes wrong,
        //rather than just making a book
        if(book == null){
            book = new AddressBook();
        }
        repoBook.save(book);

        model.addAttribute("addressBook", book);
        BuddyInfo bud = new BuddyInfo();
        model.addAttribute("buddyInfo", bud);
        return "contents";
    }

    //This could be RESTful
    @PostMapping("/addressbookold/{bookId}")
    public String addBuddy(@PathVariable String bookId, @ModelAttribute BuddyInfo bud, Model model) {
        AddressBook book = repoBook.findById(Long.parseLong(bookId));
        //might want this to return an "error template"? if something goes wrong,
        //rather than just making a book
        if(book == null){
            book = new AddressBook();
        }
        book.addBuddy(bud);
        repoBook.save(book);
        model.addAttribute(book);
        return "contents";
    }


}
