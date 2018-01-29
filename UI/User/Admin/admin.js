var app = angular.module('module1', ['ngRoute']);

//control for Login
app.controller('checkLogin', function ($scope, $window, $location, $rootScope, $http) {
    $window.sessionStorage.removeItem("userName");
    $scope.userName = null;
    $scope.userPassword = "";
    $window.sessionStorage.setItem("userName", "");
    $scope.authenticate = function () {
        $rootScope.loggedIn = false;

        $http({
            method: 'POST',
            url: "/MovieTicketBooking/adminLogin",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (obj) {
                var str = [], p;
                for (p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            },
            data: {
                'id': $scope.userName,
                'password': $scope.userPassword
            }
        }).then(function (response) {
            if (response.status == 200) {
                $rootScope.loggedIn = true;
                $window.sessionStorage.setItem("userName", $scope.userName);
                $window.location.href = 'home.html';
            } else {
                $window.alert("wrong id or password");
            }
        }, function () {
            $window.alert("Wrong id or password");
        });
    }

});



//control for adding movie in database
app.controller('addMovie', function ($scope, $window, $http, addmov) {
    $scope.movie;
    $scope.duration;
    $scope.genre = [];
    $scope.addmovies = function () {

        var list = document.querySelectorAll("input[name^='radios[']:checked");
        if ($scope.movie == null || $scope.movie == "")
            $window.alert("Enter Movie name First");
        else if ($scope.duration == null || $scope.duration == "")
            $window.alert("Add Duration");
        else if ($scope.duartaion < 1 || $scope.duration > 500)
            $window.alert("Duration Between 1-500");
        else if (list.length == 0)
            $window.alert("Select Gendre first");
        else {
            for (var i = 0; i < list.length; i++)
                $scope.genre.push(list[i].value);

            var data = {
                'name': $scope.movie,
                'duration': $scope.duration,
                'genre': $scope.genre
            }
            addmov.addmovi(data).then(function () {

                $scope.movie = "";
                $scope.duration = "";
                $('input:checkbox').removeAttr('checked');


            });
        }



    }
});


//Service for adding movie in database
app.service('addmov', ['$http', '$window', '$route', function ($http, $window, $route) {
    this.addmovi = function (data) {
        $http.post('/MovieTicketBooking/addMovie', data).success(function () {
            $window.alert('Successfull');
        }).error(function () {
            $window.alert('Data Already Exist');
            $route.reload();

        })

    };

}]);
//index Controller
app.controller('homeController', function ($rootScope, $window, $scope, $location) {

    $rootScope.session = $window.sessionStorage.getItem("userName");
    if ($rootScope.session === "" || $rootScope.session === null)
        $window.location.href = '/Admin/index.html';
    else
        $location.url("/dashboard");
    $scope.logOutAdmin = function () {

        $window.sessionStorage.removeItem("userName");
        $window.location.href = '/Admin/index.html';

    }
});

//Dashboard Controller
app.controller('dashboardController', function ($rootScope, $window, $scope, $http, dashboardService, movThe) {

    $rootScope.dateAdd = 24 * 60 * 60 * 1000;
    $scope.theatreSelect = null;
    $scope.datefrom = null;
    $scope.TheData = movThe.getThe().then(function (data) {
        $scope.theatreDetails = data.data;
    });
    $scope.values = null;
    $scope.dataChange = function () {
        if ($scope.datefrom != null && $scope.theatreSelect != null) {
            $scope.newdatefrom = new Date(new Date($scope.datefrom.getTime() + $rootScope.dateAdd));
            $scope.newdatefrom = $scope.newdatefrom.toISOString().substr(0, 10);
            var data = {
                'theatreId': $scope.theatreSelect.id,
                'showDate': $scope.newdatefrom
            }
            dashboardService.getData(data).then(function (val) {

                var temp = [];
                for (var i = 0; i < val.data.length; i++) {
                    for (var j = 0; j < val.data[i].showTime.length; j++) {
                        var obj = { "duration": val.data[i].duration, "movieId": val.data[i].movieId, "movieName": val.data[i].movieName, "showTime": val.data[i].showTime[j], 'rating': val.data[i].rating, 'count': val.data[i].ratingCount };
                        temp.push(obj);
                    }
                }

                $scope.values = temp;
            });

        }
    }
    $scope.showAvailability = function (movieId, showTime) {
        var showData = {
            'theatreMovieId': 'T' + $scope.theatreSelect.id + 'M' + movieId,
            'showDate': $scope.newdatefrom,
            'showTime': showTime
        }
        dashboardService.countTicket(showData).then(function (Data) {
            $scope.Available = Data.data - 50;
            $scope.Booked = Data.data;

            // $scope.available=Data.data.avaial

        });
    }
});


//Dashboard Service 
app.service('dashboardService', ['$http', function ($http) {
    this.getData = function (data) {

        return (
            $http.post('/MovieTicketBooking/getMovieInTheatre', data).success(function (response) {
                return (response);
            })
            .error(function (response, status) {

                return (status);
            })
        );

    }
    this.countTicket = function (data) {
        return (
            $http.post('/MovieTicketBooking/getTicketCount', data).success(function (response) {
                return (response);
            })
            .error(function (response, status) {
                return (status);
            })
        );
    }
}]);


//control for adding Theatre in database
app.controller('addTheatre', function ($scope, $window, $http, addThe) {
    $scope.theatre;
    $scope.latitude;
    $scope.longitude;
    $scope.screen;
    $scope.addTheatres = function () {
        if ($scope.theatre == null || $scope.theatre == "")
            $window.alert("Add Theatre name first");
        else if ($scope.latitude == null || $scope.latitude == "")
            $window.alert("Add latitude value first");
        else if ($scope.latitude < 0.0000000001 || $scope.latitude > 100)
            $window.alert("Latitude value must be between 0-100");
        else if ($scope.longitude == null || $scope.longitude == "")
            $window.alert("Add longitude value first");
        else if ($scope.longitude < 0.0000000001 || $scope.longitude > 100)
            $window.alert("longitude value must be between 0-100");
        else if ($scope.screen == null || $scope.screen == "")
            $window.alert("Add Screen Value");
        else if ($scope.screen < 0 || $scope.screen > 10)
            $window.alert("Screen value must between 1-10");
        else {



            var data = {
                'name': $scope.theatre,
                'latitude': $scope.latitude,
                'longitude': $scope.longitude,
                'numberOfScreen': $scope.screen
            }
            addThe.addThea(data)
            $scope.theatre = "";
            $scope.latitude = "";
            $scope.longitude = "";
            $scope.screen = "";
        }

    }
});


//Service for adding Theatre in database
app.service('addThe', ['$http', '$window', '$route', function ($http, $window, $route) {
    this.addThea = function (data) {

        $http.post('/MovieTicketBooking/addTheatre', data).success(function () {
            $window.alert('Succseefull');
        }).error(function () {
            $window.alert('Already Exist');
            $route.reload();
        });
    }

}]);



//control for adding Addons in database
app.controller('addAddOns', function ($scope, $http, addOns) {
    $scope.addAddOns = function () {
        var data = {
            'itemName': $scope.product_name,
            'cost': $scope.product_price,

        };
        addOns.addAddValue(data)
        $scope.product_name = "";
        $scope.product_price = "";

    };
});


//Service for adding AddOns in database
app.service('addOns', ['$http', '$window', '$route', function ($http, $window, $route) {
    this.addAddValue = function (data) {
        $http.post('/MovieTicketBooking/addAddons', data).success(function () {
            $window.alert('Succseefull');
        }).error(function () {
            $window.alert('Succseefull');
            $route.reload();
        });
    }

}]);

//control for MovieTheater in database
app.controller('movieTheatre', function ($rootScope, $route, $window, $scope, $http, movThe) {
    //Fetching movie Detail

    $scope.movData = movThe.getMov().then(function (data) {
        $scope.movieDetails = data.data;
    });
    //Fetching all Theatre Detail 
    $scope.TheData = movThe.getThe().then(function (data) {
        $scope.theatreDetails = data.data;
    });
    // adding in screen
    $scope.addScreen = function (val) {
        for (var i = 0; i < val; i++)
            $scope.valScreen.push(i + 1);
    }
    //Featching all movie detail 
    $scope.valScreen = [];
    $scope.datefrom;
    $scope.timefrom;
    $scope.dateto;
    $scope.addMovieTheatre = function () {
        $rootScope.dateAdd = 24 * 60 * 60 * 1000;
        if ($scope.theatreSelect == null || $scope.theatreSelect == "")
            $window.alert("Select Theatre First");
        else if ($scope.movieSelect.name == null || $scope.movieSelect.name == "")
            $window.alert("Select Movie First");
        else if ($scope.screenSelect == null || $scope.screenSelect == "")
            $window.alert("Select Screen First");
        else if ($scope.datefrom.getTime() == null || $scope.datefrom.getTime() == "")
            $window.alert("Select Date From First");
        else if ($scope.dateto.getTime() == null || $scope.dateto.getTime() == "")
            $window.alert("Select Date to First");
        else if ($scope.timefrom == null || $scope.timefrom == "")
            $window.alert("Select Time From First");
        else {
            //formating all date and time in theatre
            $scope.newdatefrom = new Date(new Date($scope.datefrom.getTime() + $rootScope.dateAdd));
            $scope.newdatefrom = $scope.newdatefrom.toISOString().substr(0, 10);
            $scope.newdateto = new Date(new Date($scope.dateto.getTime() + $rootScope.dateAdd));
            $scope.newdateto = $scope.newdateto.toISOString().substr(0, 10);
            $scope.newtimefrom = $scope.timefrom.toString().substr(15, 16);
            $scope.newtimefrom = $scope.newtimefrom.substr(1, 8);

            //make a object for data inserting
            var data = {
                'theatreId': $scope.theatreSelect.id,
                'movieId': $scope.movieSelect.id,
                'screen': $scope.screenSelect,
                'startDate': $scope.newdatefrom,
                'endDate': $scope.newdateto,
                'startTime': $scope.newtimefrom
            }
            movThe.addMovThea(data).then(function (response) {
                $scope.datefrom = null;
                $scope.timefrom = null;
                $scope.dateto = null;

            });

        }

    }

});


//Service for MovieTheater in database
app.service('movThe', ['$http', '$window', function ($http, $window) {
    //fetch detail of all movie
    this.getMov = function () {
        return (

            $http.get("/MovieTicketBooking/listMovies").success(function (response) {

                return (response.data);
            }).error(function (response) {
                return (response);
            })
        );

    }
    //fetch detail of all Theatre
    this.getThe = function () {
        return (
            $http.get("/MovieTicketBooking/listTheatres").success(function (response) {

                return (response.data);
            }).error(function (response) {
                return (response);
            })
        );
    }
    this.addMovThea = function (data) {
        return (
            $http.post('/MovieTicketBooking/addMovieInTheatre', data).success(function (response) {

                $window.alert('Successfull')
                return response;
            }).error(function () {
                $window.alert("Time Mismatching");
                return response;
            })
        );
    }

}]);
//For All Movie 
app.controller('getAllMovie', function ($scope, $http, $route, fetchMovie) {
    $scope.id;
    $scope.name;
    $scope.duration;
    $scope.isHidden = true;
    $scope.dataMov = fetchMovie.getAll().then(function (data) {
        $scope.details = data.data;

    })
    $scope.delet = function (id) {
        fetchMovie.deleteMov(id).then(function (data) {
            $scope.details = $scope.details.filter(function (e) {
                return e.id != id;
            })
        })


    }
    $scope.updateFunc = function (id, name, duration) {
        $scope.id = id;
        $scope.name = name;
        $scope.duration = duration;
        $scope.isHidden = false;


    }
    $scope.doUpdate = function () {
        var dat = {
            "id": $scope.id,
            "name": $scope.name,
            "duration": $scope.duration
        };
        fetchMovie.doUpdateMovie(dat);
        detail = $scope.details.find(function (e) {
            return e.id == dat.id;
        });
        detail.name = dat.name;
        detail.duration = dat.duration;
        $scope.isHidden = true;
        $scope.id = "";
        $scope.name = "";
        $scope.duration = "";
    }
});

app.service('fetchMovie', ['$http', function ($http) {
    this.getAll = function () {
        return (

            $http.get("/MovieTicketBooking/listMovies").success(function (response) {

                return (response.data);
            }).error(function (response) {
                return (response);
            })
        );
    }
    this.deleteMov = function (id) {
        return (
            $http.delete("/MovieTicketBooking/deleteMovie/" + id).then(function (response) {

                return response;
            }, function (response) {
                alert("Can't Delete");
                return response;
            }));

    }
    this.doUpdateMovie = function (data) {
        $http.put("/MovieTicketBooking/editMovie", data).then(function () {
            alert("Movie Updated");
        }, function () {
            alert("Cant Update");
        })

    }
}]);

app.config(function ($routeProvider, $locationProvider) {

    $routeProvider

        .when('/dashboard', {
            templateUrl: '/Admin/DashBoard.html',
            controller: 'dashboardController',

        })

        .when('/AddMovie', {
            templateUrl: '/Admin/AddMovie.html',
            controller: 'addMovie',

        })
        .when('/AddTheatre', {
            templateUrl: '/Admin/AddTheatre.html',
            controller: 'addTheatre',

        })
        .when('/AddOns', {
            templateUrl: '/Admin/addOns.html',
            controller: 'addAddOns',

        })
        .when('/AllMovies', {
            templateUrl: '/Admin/allMovie.html',
            controller: 'getAllMovie',

        })
        .when('/Movie_Theatre', {
            templateUrl: '/Admin/MovieTheatre.html',
            controller: 'movieTheatre',

        }).otherwise({ redirectTo: '/Admin/' });
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
})