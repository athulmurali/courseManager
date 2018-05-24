function toggleSentMessage(){
    console.log($('#formContainer'));
    const emailId= $('#email').val();
    console.log("Sending email to ", emailId);

    const userService = new UserServiceClient();
    userService.emailResetPasswordLink(emailId).then(
        function (response)
        {  if (response.status ==200)
            {
                $('#forgotTextBox').hide();
                $('#errorMessage').hide();

                // document.getElementById('userForm').style(display:none)
                $('#resetLinkSentMessage').show();

            }

            else {
            $('#errorMessage').show();
        }
        });


    console.log("replaced text box with message");
}


function main() {
    $('#sendLinkButton').click(toggleSentMessage);
    $('#login').click(redirectToLogin);

    $('#navLogin').click(redirectToLogin);
    $('#navRegister').click(redirectToRegister);


}

(function () {

    jQuery(main);

    var template;



})();

