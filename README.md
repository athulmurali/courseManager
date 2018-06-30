# courseManager

<h3>Deployed login link : <h3>
https://neu-course-manager.herokuapp.com/jquery/components/user-login/user-login.html

<h3>Deployed Admin's screen link </h3>
https://neu-course-manager.herokuapp.com/jquery/components/user-admin/user-admin.template.client.html

<h3>Deployed React app </h3>
https://react-course-manager.herokuapp.com/


<hr>

##### Releases

React app 

Assignment 3: https://github.com/athulmurali/react-course-manager/releases/tag/3.2


Java Springboot server : 

https://github.com/athulmurali/courseManager/releases/tag/3.2


<hr>

Note : 

Heroku server is unable to access the values in the properties file, hence forgot password will never be successfull in heroku site. 



Alright, how do I run it on localHost:
copy paste the following in the root of github repo

vim script.sh

#!/bin/bash

export DBHOSTNAME="us-cdbr-iron-east-05.cleardb.net"

export DBSCHEMA="heroku_xxxxxxxxxxx"

export DBUSERNAME="xxxxxxxxxxxxx"

export DBPASSWORD="aaaaaaaaaaaaa"

export SERVERURL="http://localhost:8080"

mvn clean install

java -jar target/neu-course-manager-0.0.1-SNAPSHOT.jar

and save the above in name : testScript.sh

to run : 

bash testScript.sh
