
function setNewPassword(){
    const reset_token =getParameterByName("reset_token",window.location);
    const password    = $('#password').val();

    console.log("reset token :",reset_token);
    console.log("password    :",password);


    var userService = new UserServiceClient();
    userService.resetPassword(reset_token,password).then(function (response)
    {
        console.log(response)
        if (response.status == 200) {
            $('#resetForm').hide();
            $('#successCard').show();

        }
        else
            console.log("Failure");
    });
}




function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}


function main() {
    $('#navLogin').click(redirectToLogin);
    $('#navRegister').click(redirectToRegister);


    $('#loginBtn').click(redirectToLogin);

    $('#reset').click(setNewPassword)

}
//IIFE
(function () {

    jQuery(main);

    var template;

})();
