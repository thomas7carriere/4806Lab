package Application;

import Application.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class AddressBookGUI {
    private AddressBookRepository repoBook;
    private BuddyInfoRepository repoBud;

    @Autowired
    public AddressBookGUI(AddressBookRepository repoBook, BuddyInfoRepository repoBud) {
        this.repoBud = repoBud;
        this.repoBook = repoBook;
    }

    //this will show a nice view of the Book with "contents.html"
    @GetMapping("/addressbook/{bookId}")
    public String greetingForm(@PathVariable String bookId, Model model) {
        AddressBook book = repoBook.findById(Long.parseLong(bookId));
        //might want this to return an "error template"? if something goes wrong,
        //rather than just making a book
        if(book == null){
            book = new AddressBook();
        }
        repoBook.save(book);
        model.addAttribute("addressBook", book);
        return "contents";
    }

    //Post mapping for adding a buddy to an addressbook
    @PostMapping("/addressbook/{bookId}")
    public String addBuddy(@PathVariable String bookId, @RequestParam String name, @RequestParam String phone, Model model) {
        AddressBook book = repoBook.findById(Long.parseLong(bookId));
        //might want this to return an "error template"? if something goes wrong,
        //rather than just making a book
        if(book == null){
            book = new AddressBook();
        }
        BuddyInfo bud = new BuddyInfo(name,phone);
        book.addBuddy(bud);
        repoBook.save(book);
        return "contents";
    }


}
