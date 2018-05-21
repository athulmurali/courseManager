//IIFE
(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient()

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('#createUser').click(createUser);

        findAllUsers();
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function createUser() {
        console.log('createUser');

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role:$('#role').find('option:selected').text()
        };

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function renderUsers(users) {
        tbody.empty();
        console.log("rendering users")
        console.log(users);
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);

            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);

            clone.find('.username')
                .html(user.username);
            clone.find('.password')
                .html("***********");
            clone.find('.firstName')
                .html(user.firstName);
            clone.find('.lastName')
                .html(user.lastName);
            clone.find('.role')
                .html(user.role);
            tbody.append(clone);

        }
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event) {
        console.log('editUser');

        const   userName = $(this).parent().parent().find('td.username').html();
        const   firstName = $(this).parent().parent().find('td.firstName').html();
        const   lastName = $(this).parent().parent().find('td.lastName').html();
        const   password = $(this).parent().parent().find('td.password').html();
        const   role = $(this).parent().parent().find('td.role').html();


        // loading existing values in edit user modal window
        $('#modalUsername').val(userName);
        $('#modalFirstName').val(firstName);
        $('#modalLastName').val(lastName);
        $('#modalPassword').val(password);
        $('#newrole').val(role);


        const userId= parseInt($(this).parent().parent().attr('id'));



        $('#updateUser').click(function () {


            const userObj ={
                username: $('#modalUsername').val(),
                firstName: $('#modalFirstName').val(),
                lastName:$('#modalLastName').val(),
                password:$('#modalPassword').val(),
                role:$('#newrole').find('option:selected').text()
            }
            console.log(userObj);

            console.log("updating user ID : " + userId);

                userService.updateUser(userId, userObj).then(function(){
                console.log("user update request submitted !");
                findAllUsers();

                $('#myModal').modal('toggle');


            })
        });

    }

})();
