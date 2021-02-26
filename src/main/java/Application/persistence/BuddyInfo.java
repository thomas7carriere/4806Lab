package Application.persistence;

import javax.persistence.*;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AddressBook addressBook;

    private String name;
    private String phone;
    private String address;

    public BuddyInfo(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BuddyInfo(String name, String phone, String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return this.phone;
    }

    public AddressBook getAddressBook() {return this.addressBook;}

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddressBook(AddressBook addressBook) {this.addressBook = addressBook;}



    @Override
    public String toString(){
        return "Name: " + this.name + "\nPhone: " + this.phone;
    }
}
