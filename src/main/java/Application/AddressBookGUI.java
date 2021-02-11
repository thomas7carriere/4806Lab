package Application;

import Application.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AddressBookGUI {
    private AddressBookRepository repoBook;
    private BuddyInfoRepository repoBud;

    @Autowired
    public AddressBookGUI(AddressBookRepository repoBook, BuddyInfoRepository repoBud) {
        this.repoBud = repoBud;
        this.repoBook = repoBook;
    }

    @GetMapping("/addressbook/{bookId}")
    public String greetingForm(@PathVariable String bookId, Model model) {
        AddressBook book = repoBook.findById(Long.parseLong(bookId));
        if(book == null){
            book = new AddressBook();
        }
        repoBook.save(book);
        model.addAttribute("addressBook", book);
        return "contents";
    }

}
