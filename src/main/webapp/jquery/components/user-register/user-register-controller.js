const button = document.querySelector('.btn')
const form   = document.querySelector('.form')
const formValidated = true;

var emailValidated = false;

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
    $("#register").prop("disabled",true);

}
//IIFE
(function () {

    jQuery(main);






})();


// ======================validation

// function registerOnFormChange()
// {
//     const formValidated = ifFormValid();
//     console.log(formValidated);
//
//     if (formValidated){
//         $("#register").prop("disabled",false);
//     }
//     else{
//         $("#register").prop("disabled",true);
//     }
//     return;
//
// }
//
// $(document).ready(function () {
//
//
//     $('input').change(registerOnFormChange).keyup(registerOnFormChange);
//
//     $("#home").click(function(){redirectToWelcome();});
//
//     // checks bootstrap import
//     var bootstrap_enabled = (typeof $().emulateTransitionEnd == 'function');
//     console.log("bootstrap loaded : " + bootstrap_enabled);
//
//     $('#password_confirm,#password').keyup(
//         function () {
//             if (isPasswordConfirmed())
//             {
//                 $('#message').html('<img id="passwordStatus" src="../templates/img/circleCheck.png" />')
//
//             } else
//                 $('#message').html('<img id="passwordStatus" src="../templates/img/wrong.png" />')
//         }
//     );
//
//     $("#email").keyup(emailValidate).change(emailValidate);
//
//     $('#password').keyup(isPasswordLengthValid);
//
//     // if password is valid and email is valid allow
//     // else disable the register button
//
//     $("#register").click(function () {
//
//         var userDetails = {
//             "userId"     :    $("#email").val(),
//             "password"  :    $("#password").val(),
//             "role"      :    $('input[name=role]:checked').val()
//         }
//
//         $.ajax({
//             type: "POST",
//             url: "/userRegister",
//             data: JSON.stringify(userDetails),
//             contentType: 'application/json',
//
//             success: function (response) {
//                 console.log("Success");
//                 redirect1();
//             },
//             error: function (e) {
//                 console.log('page not found' + e);
//                 redirect2();
//
//             }
//         });
//     });
//
//     ifFormValid();
//
// });
//
// function ifFormValid() {
//     return  emailValidated             &&
//         isPasswordLengthValid()         &&
//         isPasswordConfirmed();
// }
//
// function emailValidate() {
//     var $emailAvailable = $("#emailAvailable");
//     var email = $("#email").val();
//
//     if (validateEmail(email))
//     {
//         emailValidated = true;
//         console.log("existing email check");
//         checkIfEmailNotTaken(email);
//         registerOnFormChange()
//         return emailValidated;
//     }
//     else {
//
//         $emailAvailable.text("invalid");
//         $emailAvailable.css("color", "red");
//         registerOnFormChange()
//
//         return false;
//     }
// }
//
// function validateEmail(email) {
//     var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//     return re.test(String(email).toLowerCase());
// }
//
// function isPasswordConfirmed(){
//
//     return ($('#password').val() == $('#password_confirm').val() &&
//         $('#password').val()!= ''); }
//
// function isPasswordLengthValid()
// {
//     if ($('#password').val().length < 4)
//     {
//         $('#passwordHelp').css("display", "block");
//         return false;
//     }
//
//     else
//     {
//         $('#passwordHelp').css("display", "none");
//         return true;
//     }
// }
//
// function redirect1() {
//
//     console.log("in redirect");
//     window.location = "../templates/userLogin.html";
// }
//
// function redirect2() {
//     window.location = "../templates/userRegister.html";
// }
//
// function checkIfEmailNotTaken(email){
//     // return success if not taken (send request to server)
//     $.ajax({
//         url : "/isEmailTaken",
//         data: {email : email},
//         type : "GET",
//
//         success : function(response) {
//             console.log("email -available");
//             emailValidated = true;
//             $("#emailAvailable").text("");
//             $("#emailAvailable").prepend('<img id="theImg" src="../templates/img/circleCheck.png" />');
//         },
//         error : function(xhr, status, error) {
//             console.log("in error of email");
//             $("#emailAvailable").text("email Taken");
//             $("#emailAvailable").css("color", "red");
//             emailValidated  = false;
//         }
//
//     });
//
// };
