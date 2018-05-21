const button = document.querySelector('.btn')
const form   = document.querySelector('.form')
const formValidated = true;


function register(){
    if (formValidated){
        console.log($('#dob').val());
        console.log("registering");
    }

}

//IIFE
(function () {

    jQuery(main);


    var template;


    function main() {
        $('#register').click(register);
    }


})();
