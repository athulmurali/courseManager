var emailValidated = true;

(function() {
    jQuery(main);
    $(init);



    function init() {

    }

})();

function main()
{
    $('#navLogout').click(redirectToLogin)
    $('#updateBtn').click(updateUser)

    localStorage.setItem("userId","422");
    const currentUserId = getUserId();
    console.log(currentUserId);

    findUserById(currentUserId);
    //get the user details from the user ID

    $('input').change(updateOnFormChange).keyup(updateOnFormChange);
    $('#password').keyup(isPasswordLengthValid);
    $("#email").keyup(emailValidate).change(emailValidate);


}

function updateUser() {

    var user = getUserDetailsFromForm();
    var userService = new UserServiceClient();
    userService.updateUser(getUserId(), user).then(function (response) { success(response) } );
    console.log("user-updated");
}


function getUserDetailsFromForm() {
    const userName = $('#username').val();
    const firstName = $('#firstName').val();
    const lastName = $('#lastName').val();
    const email = $('#email').val();
    const password = $('#password').val();
    const confirmPassword = $('#confirmPassword').val();
    const role = $('#role').val();
    const phone = $('#phoneNo').val()
    const dob = $('#dob').val();

    console.log("printing user details");
    console.log(userName + firstName + lastName + email + password + confirmPassword + role + phone + dob);
    return {
        username: userName,
        email: email,
        firstName: firstName,
        lastName: lastName,
        password: password,
        confirmPassword: confirmPassword,
        role: role,
        dob: dob,
        phone: phone
    }



}



function setFormDetails(user){

    console.log("setting user form");
    console.log("printing user details");
    console.log(user)

    $('#username').val(user.username);
    $('#firstName').val(user.firstName);
    $('#lastName').val(user.lastName);
    $('#email').val(user.email);
    $('#password').val(user.password);
    $('#confirmPassword').val(user.password);
    $('#role').val(user.role);
    $('#phoneNo').val(user.phone)

    const convertedDate = new Date(user.dob);
    console.log("converted date : " + convertedDate);
    // const newFormattedDate = convertedDate.getFullYear() + "-" + convertedDate.getMonth() + "-" + convertedDate.getDate()
    const newFormattedDate =  (new Date(convertedDate)).toISOString().substring(0, 10)
    $('#dob').val(newFormattedDate);


    return;
}



function findUserById(userId) {
    var userService = new UserServiceClient();

    userService
        .findUserById(userId)
        .then(renderUser);
}

function renderUser(user) {
    setFormDetails(user);
}


function getUserId(){
    return localStorage.getItem("userId")
}

function success(response) {
    console.log("printing response ");
    console.log(response);
    if(response == null) {
        alert('unable to update')
    } else {
        alert('success');
    }
}


function isEmailValid(){

    if (validateEmail(email))
    {

        $('#emailHelp').text("Invalid");
        $('#emailHelp').show();
        return true;
    }

    console.log("email valid");

}




function emailValidate() {
    var $emailAvailable = $("#emailAvailable");
    var email = $("#email").val();

    if (validateEmail(email))
    {
        emailValidated = true;
        console.log("existing email check : ", emailValidated);
        checkIfEmailNotTaken(email);
        registerOnFormChange()
        return emailValidated;
    }
    else {

        $emailAvailable.text("invalid");
        $emailAvailable.css("color", "red");
        updateOnFormChange()
        return false;
    }
}

function updateOnFormChange(){
    const formValidated = ifFormValid();
    console.log(formValidated);

    if (formValidated){
        $("#updateBtn").prop("disabled",false);
    }
    else{
        $("#updateBtn").prop("disabled",true);
    }
    return;
}


function ifFormValid() {
    return  validateUserName() &&
            emailValidated             &&
        isPasswordLengthValid()        &&
        validatePhoneNumber();
}


function validateUserName() {

    const username = $('#username').val();
    const usernameRegex = /^[a-zA-Z0-9]+$/;
    if (usernameRegex.test(String(username).toLowerCase())){
        $('#userNameHelp').hide();
        return true;
    }
    else{
        $('#userNameHelp').show();
        return false;

    }
}


function validatePhoneNumber(){
    var phoneNumberPattern = /^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/;
    console.log($('#phoneNo').val());
    if (phoneNumberPattern.test($('#phoneNo').val())){
        $('#phoneNoHelp').hide()
        return true;

    }
    else{
        $('#phoneNoHelp').show()
        return false;

    }

}


function isPasswordLengthValid()
{
    if ($('#password').val().length < 8 ||  $('#password').val().length >20)
    {
        $('#passwordHelp').css("display", "block");
        return false;
    }

    else
    {
        $('#passwordHelp').css("display", "none");
        return true;
    }
}


function checkIfEmailNotTaken(email) {

    const userService = new UserServiceClient();
    userService.isEmailAvailable(email).then(function(response) {
        console.log("Inside validation utils ; isEmailAvailable");
        console.log(response);

        if(response.status == 200){


            console.log("Email not taknen.......");
            $("#emailAvailable").text("email Available");
            $("#emailAvailable").css("color", "green");
            emailValidated = true;
            // $("#emailAvailable").prepend('<img id="theImg" src="../templates/img/circleCheck.png" />');
            return true;

        }

        else{
            $("#emailAvailable").text("email Taken");
            $("#emailAvailable").css("color", "red");
            emailValidated = false;
            console.log("email already taken");
            return false;

        }
    });
}
