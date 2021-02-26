package Application.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    //curious about fetch type eager
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddies = new ArrayList<BuddyInfo>();

    public AddressBook(){
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void addBuddy(BuddyInfo bud){
        if(!buddies.contains(bud)) buddies.add(bud);
    }

    public void removeBuddy(int index)
    {
        if( index < this.buddies.size() && index >= 0) {
            this.buddies.remove(index);
        }
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public List<BuddyInfo> getBuddies(List<BuddyInfo> buddies) {
        return this.buddies;
    }

    //remove the += string concatenation in the loop, high overhead in large strings
    @Override
    public String toString(){
        String str = null;
        for (BuddyInfo bud : buddies) {
            str += bud.toString() + "\n";
        }
        return str;
    }

}