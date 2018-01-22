# Movie-Ticket-Booking-Accolite

After cloning the project to your local system, extract the nginx.rar and var.rar to your C drive.

Download the tomcat server from https://tomcat.apache.org/.

Navigate to C:/nginx folder and replace the ngix.config file with the one in the project directory.

In the config file make changes to the folder path.

 location / {
            expires -1;
            #rewrite ^(.*)$ $1 break;
            root C:/Users/Hyderabad-Intern/Documents/Movie-Ticket-Booking-Accolite/UI/User/; # <-This should be the path to the User directory of the project 
        }

       

 location /MovieTicketBooking {
            proxy_set_header Host localhost;
            #resolver 8.8.8.8 ipv6=off;
            proxy_pass http://127.0.0.1:8080; # <- this should be the path for Tomcat Server(change the port number as per your tomcat configuration)
        }
        
Now open MySql Workbench
1) Create a database named onlinemovieticket using the command  Create database onlinemovieticket
2) Now select server tab from the menu bar and select data import.
3) Select the Import from Dump project folder option and select the project folder in which movie.sql file is present and press start import.
4) Now you are down with the DB part.

Now open the project in eclipse ee.
Steps
1) Click on file tab and then click on import
2)In the search field, type maven and from the list of options select existing maven projects
3)Now browse and select the project root folder and finish the import.
4)Right click on the project folder and then select BUILD PATH -> CONFIGURE BUILD PATH
5)From the popup box, select add library and then select server runtime and select apache tomcat server.
6)Right click on the project folder,and select Run As -> Run As Server.

Now navigate to C:\nginx folder and start the nginx server


Now you can access the website from the browser using the url localhost


To access the admin panel, use the url localhost/admin/





