
function login(){
        const  userName = $('#user').val();
        const  password = $('#password').val();

        var userService = new UserServiceClient();
        userService.login(userName,password).then(function (response)
        {
            console.log(response );

        });
}


function main() {
    $('#login').click(login);
}
//IIFE
(function () {

    jQuery(main);

    var template;

})();
