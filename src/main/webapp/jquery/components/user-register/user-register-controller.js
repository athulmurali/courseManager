const button = document.querySelector('.btn')
const form   = document.querySelector('.form')
const formValidated = true;


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
    const dob = $('#dob').val();

    console.log("printing user details");
    console.log(userName + firstName + lastName + email + password + confirmPassword + role + dob);
    return {
        username : userName,
        email : email,
        firstName : firstName,
        lastName : lastName,
        password : password,
        confirmPassword : confirmPassword,
        role: role,
        dob : dob
    }

}

function main() {
    $('#navRegister').click(redirectToRegister);
    $('#navLogin').click(redirectToLogin);
    $('#register').click(register);

}
//IIFE
(function () {

    jQuery(main);

    var template;



})();
