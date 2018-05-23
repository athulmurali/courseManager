function toggleSentMessage(){
    console.log($('#formContainer'));

    $('#forgotTextBox').hide();
    // document.getElementById('userForm').style(display:none)
    $('#resetLinkSentMessage').show();


    console.log("replaced text box with message");
}

function sendResetLink(){
  const emailId= $('#email').val();
  console.log("Sending email to ", emailId);
}


function main() {
    $('#forgotPasswordContent').click(sendResetLink);
    $('#sendLinkButton').click(toggleSentMessage);


}

(function () {

    jQuery(main);

    var template;



})();

