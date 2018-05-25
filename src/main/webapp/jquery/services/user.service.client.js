function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.login = login ;
    this.emailResetPasswordLink = emailResetPasswordLink;
    this.resetPassword = resetPassword;
    this.isEmailAvailable = isEmailAvailable;


    this.url =
        '/api/user';
    this.loginURL =
        '/api/login';
    this.forgotPasswordURL =
        '/api/user/forgotPassword';
    this.resetPasswordURL =
        '/api/user/resetPassword';
    this.isEmailAvailableURL=
        '/api/isEmailAvailable'
    var self = this;

    function login(username, password) {

        var loginResponse = fetch(self.loginURL, {
            method : 'post',
            body: JSON.stringify({
                username:username,
                password:password
            }),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response) {
            if(response.status==200){
                return response.json();
            }else {
                return null;
            }

        });
        return loginResponse;
    }


    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response){
            if(response.bodyUsed) {
                return response.json();
            } else {
                return null;
            }
        });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }

    function findUserByUsername(username) {
        return fetch(self.url + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function emailResetPasswordLink(email){
        return fetch(self.forgotPasswordURL,{
            method: 'post',
            body: JSON.stringify({email :email}),
            headers: {
                'content-type': 'application/json'
            }
        })

    }

    function resetPassword(reset_token,new_password){
        return fetch(self.resetPasswordURL, {
            method: 'post',
            body: JSON.stringify({
                reset_token: reset_token,
                password:new_password
            }),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function isEmailAvailable(email)
    {
        return fetch(self.isEmailAvailableURL, {
            method: 'post',
            body: JSON.stringify({
                email : email
            }),
            headers: {
                'content-type': 'application/json'
            }
        });

    }

}
