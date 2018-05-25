const formValidated = true;
var emailValidated = false;

/**Enables and disables buttons based on all validation events in form**/

function registerOnFormChange()
{
    const formValidated = ifFormValid();
    console.log(formValidated);

    if (formValidated){
        $("#register").prop("disabled",false);
    }
    else{
        $("#register").prop("disabled",true);
    }
    return;

}

function ifFormValid() {
    return  validateUserName() &&
        emailValidated             &&
        isPasswordLengthValid()         &&
        isPasswordConfirmed()&&
        validatePhoneNumber();
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

function isPasswordConfirmed(){

    return ($('#password').val() == $('#confirmPassword').val() &&
        $('#password').val()!= ''); }


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

function validateDOB(){
    console.log( new Date($('#dob').val()))
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
        registerOnFormChange()

        return false;
    }
}



function register(){
    if (formValidated){
        console.log($('#dob').val());
        console.log("registering");
        var user = getUserDetailsFromForm();
        console.log(user);

        var userService = new UserServiceClient();
        userService.createUser(user).then(function (response)
        {
            console.log(response.status == 200 );
            replaceFromWithSuccess();
        });

    }

}

function replaceFromWithSuccess(){
    console.log($('#formContainer'));

    $('#userForm').hide();
    // document.getElementById('userForm').style(display:none)
    $('#successCard').show();


    console.log("form replaced");
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
        username : userName,
        email : email,
        firstName : firstName,
        lastName : lastName,
        password : password,
        confirmPassword : confirmPassword,
        role: role,
        dob : dob,
        phone: phone
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





function main() {
    $('#navRegister').click(redirectToRegister);
    $('#navLogin').click(redirectToLogin);
    $('#register').click(register);
    $("#register").prop("disabled",true);
    $('input').change(registerOnFormChange).keyup(registerOnFormChange);
    $("#email").keyup(emailValidate).change(emailValidate);


    $('#confirmPassword,#password').keyup(
        function () {
            if (isPasswordConfirmed())
            {
                // $('#message').html('<img id="passwordStatus" src="../templates/img/circleCheck.png" />')
                console.log("password confirmed");

            } else
                // $('#message').html('<img id="passwordStatus" src="../templates/img/wrong.png" />')
                console.log("Error: confirm  password not matching");

        }
    );


    $('#password').keyup(isPasswordLengthValid);

    $("#phoneNo").keyup(validatePhoneNumber).change(validatePhoneNumber);
    $('#dob').keyup(validateDOB).change(validateDOB);


}
//IIFE
(function () {

    jQuery(main);






})();

