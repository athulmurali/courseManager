(function() {
    jQuery(main);
    $(init);



    function init() {

    }




})();

function main()
{
    $('#navLogout').click(redirectToLogin)
    $('#updateBtn').click(updateUser)

    localStorage.setItem("userId","422");
    const currentUserId = getUserId();
    console.log(currentUserId);

    findUserById(currentUserId);
    //get the user details from the user ID

}

function updateUser() {

    var user = getUserDetailsFromForm();
    var userService = new UserServiceClient();
    userService.updateUser(getUserId(), user).then(success);
    console.log("user-updated");
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
        username: userName,
        email: email,
        firstName: firstName,
        lastName: lastName,
        password: password,
        confirmPassword: confirmPassword,
        role: role,
        dob: dob,
        phone: phone
    }



}



function setFormDetails(user){

    console.log("setting user form");
    console.log("printing user details");
    console.log(user)

    $('#username').val(user.username);
    $('#firstName').val(user.firstName);
    $('#lastName').val(user.lastName);
    $('#email').val(user.email);
    $('#password').val(user.password);
    $('#confirmPassword').val(user.password);
    $('#role').val(user.role);
    $('#phoneNo').val(user.phone)
    $('#dob').val(user.dob);


    return;
}



function findUserById(userId) {
    var userService = new UserServiceClient();

    userService
        .findUserById(userId)
        .then(renderUser);
}

function renderUser(user) {
    setFormDetails(user);
}


function getUserId(){
    return localStorage.getItem("userId")
}

function success(response) {
    if(response === null) {
        alert('unable to update')
    } else {
        alert('success');
    }
}
