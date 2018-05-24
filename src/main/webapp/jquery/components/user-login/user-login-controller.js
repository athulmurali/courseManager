
function login(){
        const  userName = $('#userName').val();
        const  password = $('#password').val();

        var userService = new UserServiceClient();
        userService.login(userName,password).then(function (response)
        {
            console.log(response)
            if (response.status == 200) redirectToProfile();
            else{
                $('#invalidCredentialsUser').show();
            }

        });
}

function main() {
    $('#navRegister').click(redirectToRegister);
    $('#navLogin').click(redirectToLogin);

    $('#login').click(login);
    $('#forgotPassword').click(redirectToForgotPassword);


}
//IIFE
(function () {

    jQuery(main);

    var template;

})();
