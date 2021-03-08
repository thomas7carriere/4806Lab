package Application.InheritanceCheck;

import Application.persistence.AddressBook;
import Application.persistence.BuddyInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.Text;

@RestController
public class ControllerQuestion {
    @PostMapping("/question")
    public Question greeting(@RequestBody Question q) {
        TextQuestion qText = (TextQuestion) q;
        System.out.print("Is it a number question?: ");
        System.out.println(q instanceof NumberQuestion);
        System.out.print("Is it a text question?: ");
        System.out.println(q instanceof TextQuestion);
        System.out.print("Is it a question question?: ");
        System.out.println(q instanceof Question);
        System.out.print("Question: ");
        System.out.println(q.getQuestion());
        return new Question();
    }
}

