function getBooksBuddies() {
    $.ajax({
        url: "http://localhost:8080/addressBooks/" + $("#bookId").val()})
        .then(function(data) {
            $.ajax({
                url: data._links.buddies.href})
                .then(bookData => {
                    let buds = bookData._embedded.buddies;
                    displayBuddies(buds);
                });
        });
};

//creates an addressBook by sending a POST request to the AddressBook crudrepo endpoint
function createAddressBook() {
    $.ajax({
        url: "http://localhost:8080/addressBooks",
        type: 'POST',
        contentType: 'application/json',
        data: "{}",
        dataType: 'json'
    })
        //gets the newly created addressBook's Id from the return links, and puts it in the buddyForm bookId field
        .then(data => {
            let lastChar = /[^/]*$/.exec(data._links.self.href)[0];
            $('#bookId').val(lastChar);
        });
}

//creates and adds a buddy to an AddressBook, which is specified by its ID
function addBuddyInfo(){
    $.ajax({
        url: "http://localhost:8080/addressbook",
        type: 'POST',
        contentType: 'application/json', //seems to cause an error. Don't think my JSON is getting wrapped in double quotes
        data: ($('#buddyForm').serializeArray()),
        dataType: 'json'
    }).then(data => displayBuddies(data.buddies));
}

function displayBuddies(buddies){
    $('#tbodyid').empty();
    buddies.forEach(buddy => $('#buddyTable > tbody:last')
        .append('<tr><td class="name">' + buddy.name +
            '</td><td class="phone">' + buddy.phone +
            '</td><td class="address">' + buddy.address +'</td></tr>'
        ));
}
// JSON.stringify(getFormData($('#buddyForm').serializeArray())),
function getFormData(data) {
    var unindexed_array = data;
    var indexed_array = {};

    $.map(unindexed_array, function(n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}