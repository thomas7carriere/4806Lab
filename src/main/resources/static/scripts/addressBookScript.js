//Send a GET to addressBooks repo endpoint
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
}

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
        url: "/addressbook/" + $('#bookId').val(),
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(objectifyForm($('#buddyForm').serializeArray())),
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
function objectifyForm(formArray) {
    //serialize data function
    var returnArray = {};
    for (var i = 0; i < formArray.length; i++){
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray;
}

function testMethod(){
    console.log(JSON.stringify(objectifyForm($('#buddyForm').serializeArray())));
}

//testing out filtering table rows
function filterName(){
    if ($('#radioName').is(':checked')) {
        $('#buddyTable').hide();
        console.log("button is chcked");
    } else {
        $('#buddyTable').show();
        console.log("button is unchcked");
    }
}

function setup() {
    $('#radioName').change(filterName);
    $('#buddyTable > td').hide();
}

$(document).ready(setup);