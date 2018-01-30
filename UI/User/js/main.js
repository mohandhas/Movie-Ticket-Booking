var app = angular.module("myApp", ["ngRoute", "angular-growl", "ngMap"]);
angular.module("myApp")
    .config(function ($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl: "theatre.html",
                controller: "theatreController"
            })
            .when("/register", {
                templateUrl: "register.html",
            })
            .when("/movies/:theatreId", {
                templateUrl: "movies.html",
                controller: "moviesController"
            })
            .when("/layout", {
                templateUrl: "layoutNew.html",
                controller: "newLayoutController",
                resolve: {
                    "check": function ($location, layoutService) {
                        if (!layoutService.showTime && !layoutService.showDate && !layoutService.theatreMovieId) {
                            $location.path("/");
                        }
                    }
                }
            })
            .when("/login", {
                templateUrl: "login.html",
                controller: "loginController",

            })
            .when("/checkout", {
                templateUrl: "checkout.html",
                resolve: {
                    "check": function ($location, $window, layoutService) {

                        if ($window.localStorage.email == undefined) {
                            $location.path("/guestLogin");
                        } else if (layoutService.bookedTickets.length == 0) {
                            $location.path("/");
                        }
                    }
                }
            })
            .when("/guestLogin", {
                templateUrl: "guestLogin.html",
                resolve: {
                    "check": function ($location, layoutService) {
                        // 
                        if (layoutService.bookedTickets.length == 0) {
                            $location.path("/");
                        }
                    }
                }
            })
            .when("/tickets", {
                templateUrl: "ticket.html",
                resolve: {
                    "check": function ($location, layoutService) {
                        if (layoutService.bookedTickets.length == 0) {
                            $location.path("/");
                        }
                    }
                }
            })
            .when("/logout", {
                resolve: {
                    "check": function ($window, $location) {
                        $window.localStorage.clear();
                        $location.path("/login");
                    }
                }
            })
            .when("/profile", {
                templateUrl: "profile.html",
                controller: "profileController",
                resolve: {
                    "check": function ($location, $window) {
                        if ($window.localStorage.loggedIn != "true") {
                            $location.path("/");
                        }
                    }
                }
            })
            .otherwise({
                templateUrl: "error404.html"
            })
    });

app.service("controllerService", function () {
    this.dateSelect = new Date();
});

app.service("layoutService", function () {
    this.showTime = "";
    this.theatreMovieId = "";
    this.showDate = "";
    this.bookedTickets = [];
    this.theatres = [];
    this.theatreId = "";
    this.movieName = "";
});





app.controller("loginController", function ($scope, $location, $http, $window, layoutService, growl, $timeout) {

    $scope.email = "";
    $scope.password = "";
    $scope.authenticate = function () {

        if (/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test($scope.email) == false) {
            growl.error('Email is incorrect Please check it', {
                title: 'Error',
                ttl: 3000
            });
        } else {
            $http({
                method: 'POST',
                url: "/MovieTicketBooking/login",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: {
                    "email": $scope.email,
                    "password": $scope.password
                }
            }).then(function (response) {

                if (response.status == 200) {
                    $window.localStorage.loggedIn = true;
                    $window.localStorage.email = $scope.email;
                    $window.localStorage.name = response.data.name;
                    $window.localStorage.phone = response.data.phone;
                    $window.localStorage.genres = response.data.genres;

                    $timeout(function () {
                        growl.success('You Have Successfully Logged In', {
                            title: 'Logged In',
                            ttl: 2000
                        });
                    }, 500);

                    if (layoutService.bookedTickets.length > 0) {
                        $location.path("/checkout");
                    } else {
                        $location.path("/");
                    }
                } else {
                    growl.error('Wrong Email or Password', {
                        title: 'Error',
                        ttl: 2000
                    });

                }
            }, function () {
                growl.error('Wrong Email or Password', {
                    title: 'Error',
                    ttl: 4000
                });
            });
        }
    }
});




app.controller("profileController", function ($scope, $window) {
    $scope.email = $window.localStorage.email;
    $scope.name = $window.localStorage.name;
    $scope.phone = $window.localStorage.phone;
    $scope.genres = $window.localStorage.genres;
})


app.controller("ticketController", function ($scope, layoutService) {
    $scope.theatre = "";
    var len = layoutService.theatres.length;
    for (var i = 0; i < len; i++) {
        if (layoutService.theatres[i].id == layoutService.theatreId) {
            $scope.theatre = layoutService.theatres[i].name;
            break;
        }
    }
    $scope.seats = layoutService.bookedTickets.toString();
    $scope.price = 150 * layoutService.bookedTickets.length + " ₹";
    $scope.showTime = layoutService.showTime;
    $scope.showDate = layoutService.showDate;
    $scope.movieName = layoutService.movieName;

    layoutService.bookedTickets = "";
    layoutService.showTime = "";
    layoutService.theatreMovieId = "";
    layoutService.showDate = "";
    layoutService.theatres = [];
    layoutService.theatreId = "";
    layoutService.movieName = "";

});


app.controller("userCheckoutController", function ($scope, $window, $location, $http, growl, layoutService) {
    $scope.card = "";
    $scope.cvv = "";
    $scope.checkOut = function () {
        if (/^\d{16,19}$/.test($scope.card) == false) {
            growl.warning('Credit/Debit card details are incorrect', {
                title: 'Error',
                ttl: 3000
            });
        } else if (/^\d{3}$/.test($scope.cvv) == false) {
            growl.warning('cvv details are incorrect', {
                title: 'cvv incorrect',
                ttl: 3000
            });
        } else {
            for (var i = 0; i < layoutService.bookedTickets.length; i++) {
                var data = {
                    "theatreMovieId": layoutService.theatreMovieId,
                    "email": $window.localStorage.email,
                    "date": layoutService.showDate,
                    "showTime": layoutService.showTime,
                    "seatNumber": layoutService.bookedTickets[i]
                };
                $http.post("/MovieTicketBooking/addTicket", data)
                    .then(function () {

                        $location.path("/tickets");
                    })
            }
        }
    }
});



app.controller("registerController", function ($http, $scope, $location, growl, $timeout) {
    $scope.name = "";
    $scope.email = "";
    $scope.number = "";
    $scope.password = "";
    $scope.repassword = "";
    var selectedGenre = [];
    var checkBoxes = document.querySelector("#genre").querySelectorAll("input");
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            selectedGenre.push(checkBoxes[i].value);
        }
    }

    $scope.register = function () {
        if (/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test($scope.email) == false) {
            growl.error('Email is incorrect Please check it', {
                title: 'Error',
                ttl: 3000
            });
        } else if (/^\d{8,10}$/.test($scope.number) == false) {
            growl.warning("Please enter valid phone number", {
                title: "Invalid Phone Number",
                ttl: 3000
            });
        } else if ($scope.password.localeCompare($scope.repassword) == 1 || $scope.password.localeCompare($scope.repassword) == -1) {
            growl.warning("Password and Re-Password Do Not Match", {
                title: "Password Mis-Match",
                ttl: 3000
            });
        } else {

            var data = {
                "name": $scope.name,
                "email": $scope.email,
                "phone": $scope.number,
                "password": $scope.password,
                "favoriteGenre": selectedGenre
            };
            $http.post("/MovieTicketBooking/addUser", data)
                .then(function () {
                    $timeout(function () {
                        growl.success('You Have Successfully Registered', {
                            title: 'Registered',
                            ttl: 2000
                        });
                    }, 500);
                    $location.path("/login");
                }, function () {

                    growl.error('There was an error while registering', {
                        title: 'Registration Error',
                        ttl: 2000
                    });

                })

        }
    }

});



app.controller("guestLoginController", function ($scope, $http, $location, layoutService, growl) {
    $scope.email = "";
    $scope.credit = "";
    $scope.cvv = "";
    $scope.checkOut = function () {
        
        if (/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test($scope.email) == false) {
            console.log("okkokok");
            growl.error('Email is incorrect Please check it', {
                title: 'Error',
                ttl: 3000
            });
        } else if (/^\d{16,19}$/.test($scope.credit) == false) {
            growl.warning('Credit/Debit card details are incorrect', {
                title: 'Error',
                ttl: 3000
            });
        } else if (/^\d{3}$/.test($scope.cvv) == false) {
            growl.warning('cvv details are incorrect', {
                title: 'cvv incorrect',
                ttl: 3000
            });
        } else {
            for (var i = 0; i < layoutService.bookedTickets.length; i++) {
                var data = {
                    "theatreMovieId": layoutService.theatreMovieId,
                    "email": $scope.email,
                    "date": layoutService.showDate,
                    "showTime": layoutService.showTime,
                    "seatNumber": layoutService.bookedTickets[i]
                };
                $http.post("/MovieTicketBooking/addTicket", data)
                    .then(function () {

                        $location.path("/tickets");
                    }, function () {
                        growl.error("couldnt book the tickets", {
                            title: "Tickets not booked",
                            ttl: 300
                        });
                    })
            }
        }
    }
});





app.controller("moviesController", function ($scope, $http, $route, $routeParams, $location, controllerService, layoutService,growl) {


    layoutService.theatreId = $routeParams.theatreId;
    $scope.movies = [];
    $scope.genres = ["Action", "Adventure", "Comedy", "Crime", "Drama", "Historical", "Horror", "Mystery", "Romance", "Fiction", "Social", "Thriller"];
    var checked = [false, false, false, false, false, false, false, false, false, false, false, false];
    $scope.show = function (index) {
        if (checked.indexOf(true) == -1) {
            return true;
        }
        var temp = false;
        for (var i = 0; i < $scope.movies[index].genre.length; i++) {
            temp = temp || checked[$scope.movies[index].genre[i] - 1]
        }
        return temp;
    }
    $scope.change = function (element) {
        checked[element.$index] = !checked[element.$index];
    }

    $('#modal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var movieId = button.data('movieid');
        var modal = document.querySelector("#modal");
        modal.querySelector("fieldset").setAttribute("id", movieId);
        // modal.querySelector("#submit").setAttribute("ng-click","submitRating("+movieId+")");
    })

    $scope.submitRating = function () {
        var rating = 0;
        var fieldSet = document.querySelector("fieldset");


        var movieId = fieldSet.getAttribute("id");
        var stars = fieldSet.querySelectorAll("input");
        for (var i = 0; i < stars.length; i++) {
            if (stars[i].checked) {
                rating = stars[i].value;
                break;
            }
        }
        if (rating > 0 && rating <= 5) {
            $http.post("/MovieTicketBooking/ratings", {
                    'rating': rating,
                    'movieId': movieId
                })
                .then(function (response) {
                    if (response.status == 200) {
                        $('#modal').modal("hide");
                        growl.success("Successfully rated the movie",{
                            title:"rated successfully",
                            ttl:3000
                        });
                        $route.reload();
                    } else {
                        growl.error("problem in rating the movie",{
                            title:"Not rated",
                            ttl:3000
                        });
                    }
                });
        } else {
            growl.warning("You didn't give any rating",{
                title: "Warning",
                ttl:3000
            });
        }
    }


    $scope.selectDate = controllerService.dateSelect;

    var reqDate = $scope.selectDate.toISOString().split('T')[0];
    layoutService.showDate = reqDate;
    $http.post("/MovieTicketBooking/userGetMovieInTheatre", {
            theatreId: $routeParams.theatreId,
            showDate: reqDate
        })
        .then(function (response) {


            $scope.movies = response.data;
            for (var i = 0; i < $scope.movies.length; i++) {
                $scope.movies[i].showTime.sort();
            }
        });

    $scope.showLayout = function (time, id, name) {

        layoutService.showTime = time;
        layoutService.theatreMovieId = id;
        layoutService.movieName = name;
        $location.path('/layout');
    }
});





app.controller("navController", function ($scope, $route, layoutService, $window) {
    $scope.loggedIn = $window.localStorage.loggedIn;
    $scope.name = $window.localStorage.name;
    $scope.checkLoggedIn = function () {
        // console.log($scope.loggedIn != "true",$scope.loggedIn);
        return $window.localStorage.loggedIn != "true";
    }
    $scope.getName = function () {
        return $window.localStorage.name;
    }
});



app.controller("theatreController", function ($scope, $http, controllerService, $route, layoutService, growl, growlMessages) {


    $scope.dates = [new Date()];
    $scope.months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    for (var i = 0; i < 3; i++) {
        var temp = new Date();
        temp.setTime($scope.dates[$scope.dates.length - 1].getTime() + (24 * 60 * 60 * 1000));
        $scope.dates.push(temp);
    }

    $scope.dateSelect = controllerService.dateSelect;
    $scope.dateSelect = $scope.dates[0];

    $scope.setDate = function (date) {
        growlMessages.destroyAllMessages();
        if ($scope.dateSelect != date) {
            growl.info('Date is changed to ' + date.toISOString().substring(0, 10), {
                title: 'Information',
                ttl: 1000
            });
        }
        $scope.dateSelect = date;
        controllerService.dateSelect = date;



    };

    $scope.checkActive = function (date) {
        if ($scope.dateSelect.getDate() == date) {
            return "btn-success";
        }
        return;
    };

    $scope.getTheatres = function () {
        $http.get("/MovieTicketBooking/userListTheatres")
            .then(function (response) {

                $scope.theatres = response.data;
                layoutService.theatres = response.data;
            });
    };

    $scope.lat = null;
    $scope.long = null;

    $scope.getLocation = function () {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition, showError);
        } else {
            growl.error("Geolocation is not supported by this browser.",{
                title:"Error",
                ttl:3000
            });
        }
    }

    function showPosition(position) {
        $scope.lat = position.coords.latitude;
        $scope.long = position.coords.longitude;
    }

    function showError(error) {
        switch (error.code) {
            case error.PERMISSION_DENIED:
                growl.error("User denied the request for Geolocation.",{
                    ttl:3000
                });
                break;
            case error.POSITION_UNAVAILABLE:
                growl.error("Location information is unavailable.",{
                    ttl:3000
                })
                break;
            case error.TIMEOUT:
                growl.error("The request to get user location timed out.",{
                    ttl:3000
                })
                break;
            case error.UNKNOWN_ERROR:
                growl.error("An unknown error occurred.",{
                    ttl:3000
                })
                break;
        }
    }


    $scope.theatres = []
    //Function to get distance using latitude and longitude
    $scope.getDistance = function (lat1, lon1, lat2, lon2) {
        var radlat1 = Math.PI * lat1 / 180;
        var radlat2 = Math.PI * lat2 / 180;
        var theta = lon1 - lon2;
        var radtheta = Math.PI * theta / 180;
        var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
        dist = Math.acos(dist);
        dist = dist * 180 / Math.PI;
        dist = dist * 111.18957696;
        return dist
    }

    //Function to sort theatres by distance
    $scope.sortyByDistance = function () {

        var ul = document.querySelector("#theatres")
        var new_ul = ul.cloneNode(false);

        // Add all lis to an array
        var lis = [];
        for (var i = ul.childNodes.length; i--;) {
            if (ul.childNodes[i].nodeName === 'DIV')
                lis.push(ul.childNodes[i]);
        }

        // Sort the list in ascending order
        lis.sort(function (a, b) {

            return $scope.getDistance(a.getAttribute('data-lat'), a.getAttribute('data-long'), $scope.lat, $scope.long) - $scope.getDistance(b.getAttribute('data-lat'), b.getAttribute('data-long'), $scope.lat, $scope.long);
        });

        // Add them into the ul in order
        for (i = 0; i < lis.length; i++)
            new_ul.appendChild(lis[i]);
        ul.parentNode.replaceChild(new_ul, ul);
    }
});














app.controller("newLayoutController", function ($scope, $location, $http, layoutService, growl) {


    var data = {
        "theatreMovieId": layoutService.theatreMovieId,
        "showDate": layoutService.showDate,
        "showTime": layoutService.showTime
    }

    function bookedTickets() {

        $http.post("/MovieTicketBooking/bookedTickets", data)
            .then(function (response) {
                for (var i = 0; i < response.data.length; i++) {
                    var row = response.data[i][0].charCodeAt(0) - 65;
                    var col = response.data[i].substring(1) - 1;
                    $scope.seats[row][col].booked = true;
                }

            })
    }

    $scope.quantities = [{
        id: 1,
        val: 1
    }, {
        id: 2,
        val: 2
    }, {
        id: 3,
        val: 3
    }, {
        id: 4,
        val: 4
    }];

    $scope.selectedCount = $scope.quantities[0];
    // 
    function createSeats(startLetter, rows, cols) {
        var rowArray = [],
            columnArray = [];
        for (var i = 0, k = startLetter; i < rows; i++, k++) {
            for (var j = 1; j <= cols; j++) {
                columnArray.push({
                    val: j,
                    letter: String.fromCharCode(k),
                    check: false,
                    booked: false
                });
            }
            rowArray.push(columnArray);
            columnArray = [];
        }
        return rowArray;
    }

    $scope.seats = createSeats(65, 15, 30);
    bookedTickets();

    $scope.removeAllCheck = function () {
        for (var i = 0; i < $scope.seats.length; i++) {
            for (var j = 0; j < $scope.seats[i].length; j++) {
                $scope.seats[i][j].check = false;
            }
        }
    }

    function totalChecked() {
        var total = []
        for (var i = 0; i < $scope.seats.length; i++) {
            for (var j = 0; j < $scope.seats[i].length; j++) {
                if ($scope.seats[i][j].check) {
                    total.push($scope.seats[i][j].letter + $scope.seats[i][j].val);
                }
            }
        }
        return total;
    }


    $scope.select = function (seat) {
        if (totalChecked().length < $scope.selectedCount.val && !seat.booked) {
            seat.check = !seat.check;
        } else {
            seat.check = false;
        }
    }


    $scope.checkSeats = function () {
        var checkedTickets = totalChecked();
        if (checkedTickets.length > 0) {
            $http.post("/MovieTicketBooking/bookedTickets", data)
                .then(function (response) {

                    for (var i = 0; i < response.data.length; i++) {
                        var row = response.data[i][0].charCodeAt(0) - 65;
                        var col = response.data[i].substring(1);
                        if ($scope.seats[row][col].check) {

                            growl.error("Selected Seats are already booked. Please select other seats", {
                                title: 'ALREADY BOOKED',
                                ttl: 2000
                            });
                            bookedTickets();
                            $scope.removeAllCheck();
                            return;
                        }
                    }
                    layoutService.bookedTickets = checkedTickets;
                    $location.path("/checkout");
                });
        } else {


            growl.warning("No seats selected", {
                title: 'SELECT SEATS',
                ttl: 2000
            });
        }
    }




});



app.config(['growlProvider', function(growlProvider) {
    growlProvider.globalDisableCountDown(true);
  }]);