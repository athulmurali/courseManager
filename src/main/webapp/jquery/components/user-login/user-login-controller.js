
function login(){
        const  userName = $('#userName').val();
        const  password = $('#password').val();

        var userService = new UserServiceClient();
        userService.login(userName,password).then(function (response)
        {
            console.log(response)
            if (response.status == 200) redirectToProfile();
            else console.log("Failure");

        });
}

function main() {
    $('#login').click(login);
    $('#register').click(redirectToRegister);

}
//IIFE
(function () {

    jQuery(main);

    var template;

})();
