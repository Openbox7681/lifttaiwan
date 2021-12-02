var myApp = angular.module('myApp', []).controller('getAppController',
    getAppController);

angular.element(document).ready(function() {
    angular.element(window).keydown(function() {
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
});

function getAppController($scope, $http, $window) {
    var items = [];
    for (var i = 0; i < 5; i++) {
        items.push({
            Date: "　",
            Title: "",
            Id: ""
        });
    }
    var ana_items=[];
    /*
    for (var i = 0; i < 12; i++) {
    		ana_items.push({
            Date: "　",
            Title: "",
            Id: ""
        });
    }
    */

    for (var i = 0; i < 5; i++) {
        ana_items.push({
        Date: "　",
        Title: "",
        Id: ""
    });
}
    $scope.news = items;
    $scope.activity = items;
    $scope.ana = ana_items;
    $scope.malwares = items;
    //$scope.weakness = items;
    $scope.secbuzzer = items;
    $scope.inf = items;

    $scope.queryNews = function() {    	
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true,
            Title : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/news', request, csrf_config).then(function(response) {
            $scope.news = response.data.datatable;
            $scope.newsTotal = response.data.total;
            if ($scope.newsTotal < 5) {
                for (var i = 0; i < 5 - $scope.newsTotal; i++) {
                    $scope.news.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingNews").fadeOut("slow");
        })
    }
    
    $scope.queryMalwares = function() {    	
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true,
            Title : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/malwares', request, csrf_config).then(function(response) {
            $scope.malwares = response.data.datatable;
            $scope.malwaresTotal = response.data.total;
            if ($scope.newsTotal < 5) {
                for (var i = 0; i < 5 - $scope.newsTotal; i++) {
                    $scope.news.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingMalwares").fadeOut("slow");
        })
    }

    $scope.queryActivity = function() {    	
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true,
            Title : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/activity', request, csrf_config).then(function(response) {
            $scope.activity = response.data.datatable;
            $scope.activityTotal = response.data.total;
            if ($scope.activityTotal < 5) {
                for (var i = 0; i < 5 - $scope.activityTotal; i++) {
                    $scope.activity.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingActivity").fadeOut("slow");
        })
    }

    $scope.queryAna = function() {    	
        var request = {
            start: 0,
            //maxRows: 12,
            maxRows: 5,
            sort: "incidentReportedTime",
            dir: true,
            IncidentTitle : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/ana', request, csrf_config).then(function(response) {
            $scope.ana = response.data.datatable;
            $scope.anaTotal = response.data.total;
            /*
            if ($scope.anaTotal < 12) {
                for (var i = 0; i < 12 - $scope.anaTotal; i++) {
                    $scope.ana.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
            */
            if ($scope.anaTotal < 5) {
                for (var i = 0; i < 5 - $scope.anaTotal; i++) {
                    $scope.ana.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingAna").fadeOut("slow");
        })
    }

//    $scope.queryWeakness = function() {
//        var request = {
//            start: 0,
//            maxRows: 5,
//            sort: "incidentDiscoveryTime",
//            dir: true
//        };
//        $http.post('./api/p00/query/weakness', request, csrf_config).then(function(response) {
//            $scope.weakness = response.data.datatable;
//            $scope.weaknessTotal = response.data.total;
//            if ($scope.weaknessTotal < 5) {
//                for (var i = 0; i < 5 - $scope.weaknessTotal; i++) {
//                    $scope.weakness.push({
//                        Date: "　",
//                        Title: "",
//                        Id: ""
//                    });
//                }
//            }
//        }).finally(function() {
//            $("#loadingWeakness").fadeOut("slow");
//        })
//    }

    $scope.querySecBuzzer = function() {    	 
        var request = {
        		keyword : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/secbuzzer', request, csrf_config).then(function(response) {
            $scope.secbuzzer = response.data.datatable;
            $scope.secbuzzerTotal = response.data.total;
            if ($scope.secbuzzerTotal < 5) {
                for (var i = 0; i < 5 - $scope.secbuzzerTotal; i++) {
                    $scope.secbuzzer.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingSecBuzzer").fadeOut("slow");
        })
    }
    
    
    $scope.queryInf = function() {    	
        var request = {
            start: 0,
            maxRows: 5,
            sort: "postDateTime",
            dir: true,
            Keyword : $scope.QueryKeyword
        };
        $http.post('./api/p00/query/inf', request, csrf_config).then(function(response) {
            $scope.inf = response.data.datatable;
            $scope.infTotal = response.data.total;
            if ($scope.infTotal < 5) {
                for (var i = 0; i < 5 - $scope.infTotal; i++) {
                    $scope.inf.push({
                        Date: "　",
                        Title: "",
                        Id: ""
                    });
                }
            }
        }).finally(function() {
            $("#loadingInf").fadeOut("slow");
        })
    }

    $scope.queryLinks = function() {
        var request = {
            start: 0,
            maxRows: 4,
            sort: "StartDateTime",
            dir: false
        };
        $http.post('./api/p00/query/links', request, csrf_config).then(function(response) {
            $scope.links = response.data.datatable;
            $scope.linksTotal = response.data.total;
        }).finally(function() {
            $("#loadingLinks").fadeOut("slow");
        })
    }

    $scope.queryData = function() {
    		$("#loadingNews").fadeIn();
   	 	$("#loadingActivity").fadeIn();
   	 	$("#loadingAna").fadeIn();
   	 	$("#loadingSecBuzzer").fadeIn();
   	 	$("#loadingMalwares").fadeIn();

    	 	$scope.queryNews();
    	    $scope.queryActivity();
    	    $scope.queryAna();    	   
    	    $scope.querySecBuzzer();
    	    $scope.queryInf();
    	    $scope.queryMalwares();
    }
    
    $scope.queryData();
    $scope.queryLinks();



    $scope.queryInfoDashboard = function() {
        var request = {};
        $http.post('./api/p00/query/info/dashboard', request, csrf_config).then(function(response) {
            $scope.allitems_info = response.data.datatable;
            var data = [];
            data.push($scope.getCount($scope.allitems_info, "2"));
            data.push($scope.getCount($scope.allitems_info, "NHISOC"));
            data.push($scope.getCount($scope.allitems_info, "OTH"));
            data.push($scope.getCount($scope.allitems_info, "HISAC"));
            var count = 0;
            for (i = 0; i < data.length; i++)
                count += data[i];
            if(count < 10)
            	count = " " + count + " ";
            var config1 = {
                type: 'doughnut',
                data: {
                    labels: [
                        "N-ISAC",
                        "NHISOC",
                        "其他",
                        "自行新增"
                    ],
                    datasets: [{
                        data: data,
                        backgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66"
                        ],
                        hoverBackgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66"
                        ]
                    }]
                },
                options: {
                	title: {
                		display: true,
                		text: '情資來源',
                		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
                		fontSize: 19,
                		fontColor: '#369'
                	},
                	legend: {
                		display: false,
                		position: 'bottom',
                		fullWidth: true,
                		reverse: false
                	},
                    pieceLabel: {
                        render: 'value',
                        fontSize: 16,
                        fontStyle: 'bold',
                        fontColor: '#666',
                        fontFamily: 'Arial'
                    },
                    elements: {
                        center: {
                            text: count,
                            color: '#FF6384',
                            fontStyle: 'Arial',
                            sidePadding: 50
                        }
                    }
                }
            };
            var ctx1 = document.getElementById("myChart1").getContext("2d");
            var myChart1 = new Chart(ctx1, config1);
            document.getElementById('myLegend1').innerHTML = myChart1.generateLegend();
        }).catch(function() {

        }).finally(function() { });
    };

    $scope.queryPublicDashboard2week = function() {
        var request = {};
        $http.post('./api/p00/query/public/dashboard2week', request, csrf_config).then(function(response) {
            $scope.allitems_message = response.data.datatable;
            var data = [];
            data.push($scope.getCount($scope.allitems_message, "News"));
            data.push($scope.getCount($scope.allitems_message, "Activity"));
            data.push($scope.getCount($scope.allitems_message, "Ana"));
            var count = 0;
            for (i = 0; i < data.length; i++)
                count += data[i];
            if(count < 10)
            	count = " " + count + " ";
            var config1 = {
                type: 'doughnut',
                data: {
                    labels: [
                        "最新消息",
                        "活動訊息",
                        "資安訊息情報"
                    ],
                    datasets: [{
                        data: data,
                        backgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56"
                        ],
                        hoverBackgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56"
                        ]
                    }]
                },
                options: {
                	title: {
                		display: true,
                		text: '近兩週',
                		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
                		fontSize: 15,
                		fontColor: '#369',
                		position: 'bottom'
                	},
                	legend: {
                		display: false,
                		position: 'bottom',
                		fullWidth: true,
                		reverse: false
                	},
                    pieceLabel: {
                        render: 'value',
                        fontSize: 16,
                        fontStyle: 'bold',
                        fontColor: '#666',
                        fontFamily: 'Arial'
                    },
                    elements: {
                        center: {
                            text: count,
                            color: '#FF6384',
                            fontStyle: 'Arial',
                            sidePadding: 50
                        }
                    }
                }
            };
            var ctx1 = document.getElementById("myChart1").getContext("2d");
            var myChart1 = new Chart(ctx1, config1);
            document.getElementById('myLegend1').innerHTML = myChart1.generateLegend();
        }).catch(function() {
// bootbox.alert({
// message: globalReadDataFail,
// buttons: {
// ok: {
// label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// className: 'btn-danger',
// }
// },
// callback: function() {}
// });
        }).finally(function() {});

    };
    $scope.queryPublicDashboard2week();
    
    $scope.queryPublicDashboard = function() {
        var request = {};
        $http.post('./api/p00/query/public/dashboard', request, csrf_config).then(function(response) {
            $scope.allitems_message = response.data.datatable;
            var data = [];
            data.push($scope.getCount($scope.allitems_message, "News"));
            data.push($scope.getCount($scope.allitems_message, "Activity"));
            data.push($scope.getCount($scope.allitems_message, "Ana"));
            var count = 0;
            for (i = 0; i < data.length; i++)
                count += data[i];
            if(count < 10)
            	count = " " + count + " ";
            var config2 = {
                type: 'doughnut',
                data: {
                    labels: [
                        "最新消息",
                        "活動訊息",
                        "資安訊息情報"
                    ],
                    datasets: [{
                        data: data,
                        backgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56"
                        ],
                        hoverBackgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56"
                        ]
                    }]
                },
                options: {
                	title: {
                		display: true,
                		text: '總計',
                		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
                		fontSize: 15,
                		fontColor: '#369',
                		position: 'bottom'
                	},
                	legend: {
                		display: false,
                		position: 'bottom',
                		fullWidth: true,
                		reverse: false
                	},
                    pieceLabel: {
                        render: 'value',
                        fontSize: 16,
                        fontStyle: 'bold',
                        fontColor: '#666',
                        fontFamily: 'Arial'
                    },
                    elements: {
                        center: {
                            text: count,
                            color: '#FF6384',
                            fontStyle: 'Arial',
                            sidePadding: 50
                        }
                    }
                }
            };
            var ctx2 = document.getElementById("myChart2").getContext("2d");
            var myChart2 = new Chart(ctx2, config2);
            document.getElementById('myLegend2').innerHTML = myChart2.generateLegend();
        }).catch(function() {
// bootbox.alert({
// message: globalReadDataFail,
// buttons: {
// ok: {
// label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// className: 'btn-danger',
// }
// },
// callback: function() {}
// });
        }).finally(function() {});

    };
    $scope.queryPublicDashboard();
    
    $scope.queryMessageDashboard2Week = function() {
        var request = {};
        $http.post('./api/p00/query/message/dashboard2week', request, csrf_config).then(function(response) {
            $scope.allitems_message = response.data.datatable;
            var data = [];
            data.push($scope.getCount($scope.allitems_message, "ANA"));
            data.push($scope.getCount($scope.allitems_message, "DEF"));
            data.push($scope.getCount($scope.allitems_message, "INT"));
            data.push($scope.getCount($scope.allitems_message, "EWA"));
            data.push($scope.getCount($scope.allitems_message, "FBI"));
            data.push($scope.getCount($scope.allitems_message, "OTH"));
            var count = 0;
            for (i = 0; i < data.length; i++)
                count += data[i];
            if(count < 10)
            	count = " " + count + " ";
            var config3 = {
                type: 'doughnut',
                data: {
                    labels: [
                        "ANA",
                        "DEF",
                        "INT",
                        "EWA",
                        "FBI",
                        "OTH"
                    ],
                    datasets: [{
                        data: data,
                        backgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66",
                            "#ff6633",
                            "#660000"
                        ],
                        hoverBackgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66",
                            "#ff6633",
                            "#660000"
                        ]
                    }]
                },
                options: {
                	title: {
                		display: true,
                		text: '近兩週',
                		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
                		fontSize: 15,
                		fontColor: '#369',
                		position: 'bottom'
                	},
                	legend: {
                		display: false,
                		position: 'bottom',
                		fullWidth: true,
                		reverse: false
                	},
                    pieceLabel: {
                        render: 'value',
                        fontSize: 16,
                        fontStyle: 'bold',
                        fontColor: '#666',
                        fontFamily: 'Arial'
                    },
                    elements: {
                        center: {
                            text: count,
                            color: '#FF6384',
                            fontStyle: 'Arial',
                            sidePadding: 50
                        }
                    }
                }
            };
            var ctx3 = document.getElementById("myChart3").getContext("2d");
            var myChart3 = new Chart(ctx3, config3);
            document.getElementById('myLegend3').innerHTML = myChart3.generateLegend();
        }).catch(function() {
// bootbox.alert({
// message: globalReadDataFail,
// buttons: {
// ok: {
// label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// className: 'btn-danger',
// }
// },
// callback: function() {}
// });
        }).finally(function() {});

    };
    $scope.queryMessageDashboard2Week();

    $scope.queryMessageDashboard = function() {
        var request = {};
        $http.post('./api/p00/query/message/dashboard', request, csrf_config).then(function(response) {
            $scope.allitems_message = response.data.datatable;
            var data = [];
            data.push($scope.getCount($scope.allitems_message, "ANA"));
            data.push($scope.getCount($scope.allitems_message, "DEF"));
            data.push($scope.getCount($scope.allitems_message, "INT"));
            data.push($scope.getCount($scope.allitems_message, "EWA"));
            data.push($scope.getCount($scope.allitems_message, "FBI"));
            data.push($scope.getCount($scope.allitems_message, "OTH"));
            var count = 0;
            for (i = 0; i < data.length; i++)
                count += data[i];
            if(count < 10)
            	count = " " + count + " ";
            var config4 = {
                type: 'doughnut',
                data: {
                    labels: [
                        "ANA",
                        "DEF",
                        "INT",
                        "EWA",
                        "FBI",
                        "OTH"
                    ],
                    datasets: [{
                        data: data,
                        backgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66",
                            "#ff6633",
                            "#660000"
                        ],
                        hoverBackgroundColor: [
                            "#E2ACEF",
                            "#36A2EB",
                            "#FFCE56",
                            "#00cc66",
                            "#ff6633",
                            "#660000"
                        ]
                    }]
                },
                options: {
                	title: {
                		display: true,
                		text: '總計',
                		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
                		fontSize: 15,
                		fontColor: '#369',
                		position: 'bottom'
                	},
                	legend: {
                		display: false,
                		position: 'bottom',
                		fullWidth: true,
                		reverse: false
                	},
                    pieceLabel: {
                        render: 'value',
                        fontSize: 16,
                        fontStyle: 'bold',
                        fontColor: '#666',
                        fontFamily: 'Arial'
                    },
                    elements: {
                        center: {
                            text: count,
                            color: '#FF6384',
                            fontStyle: 'Arial',
                            sidePadding: 50
                        }
                    }
                }
            };
            var ctx4 = document.getElementById("myChart4").getContext("2d");
            var myChart4 = new Chart(ctx4, config4);
            document.getElementById('myLegend4').innerHTML = myChart4.generateLegend();
        }).catch(function() {
// bootbox.alert({
// message: globalReadDataFail,
// buttons: {
// ok: {
// label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// className: 'btn-danger',
// }
// },
// callback: function() {}
// });
        }).finally(function() {});

    };
    $scope.queryMessageDashboard();

//     $scope.queryNotificationDashboard = function() {
//         var request = {};
//         $http.post('./api/p00/query/notification/dashboard', request, csrf_config).then(function(response) {
//             //console.log("response=" + JSON.stringify(response.data));
//             $scope.allitems_notification = response.data.datatable;
//             var data = [];
//             data.push($scope.getCount($scope.allitems_notification, "1"));
//             data.push($scope.getCount($scope.allitems_notification, "2"));
//             data.push($scope.getCount($scope.allitems_notification, "3"));
//             data.push($scope.getCount($scope.allitems_notification, "4"));
//             data.push($scope.getCount($scope.allitems_notification, "5"));
//             var count = 0;
//             for (i = 0; i < data.length; i++)
//                 count += data[i];
//             if(count < 10)
//             	count = " " + count + " ";
//             var config4 = {
//                 type: 'doughnut',
//                 data: {
//                     labels: [
//                         "網頁攻擊",
//                         "非法入侵",
//                         "阻斷服務",
//                         "設備問題",
//                         "其他"
//                     ],
//                     datasets: [{
//                         data: data,
//                         backgroundColor: [
//                             "#E2ACEF",
//                             "#36A2EB",
//                             "#FFCE56",
//                             "#00cc66",
//                             "#ff6633"
//                         ],
//                         hoverBackgroundColor: [
//                             "#E2ACEF",
//                             "#36A2EB",
//                             "#FFCE56",
//                             "#00cc66",
//                             "#ff6633"
//                         ]
//                     }]
//                 },
//                 options: {
//                 	title: {
//                 		display: true,
//                 		text: '通報',
//                 		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
//                 		fontSize: 19,
//                 		fontColor: '#369'
//                 	},
//                 	legend: {
//                 		display: false,
//                 		position: 'bottom',
//                 		fullWidth: true,
//                 		reverse: false
//                 	},
//                     pieceLabel: {
//                         render: 'value',
//                         fontSize: 16,
//                         fontStyle: 'bold',
//                         fontColor: '#666',
//                         fontFamily: 'Arial'
//                     },
//                     elements: {
//                         center: {
//                             text: count,
//                             color: '#FF6384',
//                             fontStyle: 'Arial',
//                             sidePadding: 50
//                         }
//                     }
//                 }
//             };
//             var ctx4 = document.getElementById("myChart4").getContext("2d");
//             var myChart4 = new Chart(ctx4, config4);
//             document.getElementById('myLegend4').innerHTML = myChart4.generateLegend();
//         }).catch(function() {
// // bootbox.alert({
// // message: globalReadDataFail,
// // buttons: {
// // ok: {
// // label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// // className: 'btn-danger',
// // }
// // },
// // callback: function() {}
// // });
//         }).finally(function() {});

//     };
//     //$scope.queryNotificationDashboard();
    
    
//     $scope.queryOrgCount = function() {
//         var request = {IsEnable: true};
//         $http.post('./api/p00/query/orgcount/dashboard', request, csrf_config).then(function(response) {
//             $scope.allitems_org = response.data.datatable;
//             var data = [];
//             data.push($scope.getCount($scope.allitems_org, "CI會員"));
//             data.push($scope.getCount($scope.allitems_org, "非CI會員"));          
//             var count = 0;
//             for (i = 0; i < data.length; i++)
//                 count += data[i];
//             if(count < 10)
//             	count = " " + count + " ";
//             var config5 = {
//                 type: 'doughnut',
//                 data: {
//                     labels: [
//                         "CI會員",
//                         "非CI會員"                      
//                     ],
//                     datasets: [{
//                         data: data,
//                         backgroundColor: [
//                             "#E2ACEF",
//                             "#36A2EB"                           
//                         ],
//                         hoverBackgroundColor: [
//                             "#E2ACEF",
//                             "#36A2EB"                          
//                         ]
//                     }]
//                 },
//                 options: {
//                 	title: {
//                 		display: true,
//                 		text: '總計',
//                 		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
//                 		fontSize: 15,
//                 		fontColor: '#369',
//                 		position: 'bottom'
//                 	},
//                 	legend: {
//                 		display: false,
//                 		position: 'bottom',
//                 		fullWidth: true,
//                 		reverse: false
//                 	},
//                     pieceLabel: {
//                         render: 'value',
//                         fontSize: 16,
//                         fontStyle: 'bold',
//                         fontColor: '#666',
//                         fontFamily: 'Arial'
//                     },
//                     elements: {
//                         center: {
//                             text: count,
//                             color: '#FF6384',
//                             fontStyle: 'Arial',
//                             sidePadding: 50
//                         }
//                     }
//                 }
//             };
//             var ctx5 = document.getElementById("myChart5").getContext("2d");
//             var myChart5 = new Chart(ctx5, config5);
//             document.getElementById('myLegend5').innerHTML = myChart5.generateLegend();
//         }).catch(function() {
// // bootbox.alert({
// // message: globalReadDataFail,
// // buttons: {
// // ok: {
// // label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// // className: 'btn-danger',
// // }
// // },
// // callback: function() {}
// // });
//         }).finally(function() {});

//     };
//     $scope.queryOrgCount();
    
//     $scope.queryManagerCount = function() {
//         var request = {};
//         $http.post('./api/p00/query/managercount/dashboard', request, csrf_config).then(function(response) {
//             $scope.allitems_manager = response.data.datatable;
//             var data = [];
//             data.push($scope.getCount($scope.allitems_manager, "審核中"));
//             data.push($scope.getCount($scope.allitems_manager, "已停用"));          
//             data.push($scope.getCount($scope.allitems_manager, "待啟用"));          
//             data.push($scope.getCount($scope.allitems_manager, "使用中"));          
//             var count = 0;
//             for (i = 0; i < data.length; i++)
//                 count += data[i];
//             if(count < 10)
//             	count = " " + count + " ";
//             var config6 = {
//                 type: 'doughnut',
//                 data: {
//                     labels: [
//                         "審核中",
//                         "已停用",           
//                         "待啟用",           
//                         "使用中"       
//                     ],
//                     datasets: [{
//                         data: data,
//                         backgroundColor: [
//                         		"#E2ACEF",
//                             "#36A2EB",
//                             "#FFCE56",
//                             "#00cc66"                         
//                         ],
//                         hoverBackgroundColor: [
//                         		"#E2ACEF",
//                             "#36A2EB",
//                             "#FFCE56",
//                             "#00cc66"                        
//                         ]
//                     }]
//                 },
//                 options: {
//                 	title: {
//                 		display: true,
//                 		text: '總計',
//                 		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
//                 		fontSize: 15,
//                 		fontColor: '#369',
//                 		position: 'bottom'
//                 	},
//                 	legend: {
//                 		display: false,
//                 		position: 'bottom',
//                 		fullWidth: true,
//                 		reverse: false
//                 	},
//                     pieceLabel: {
//                         render: 'value',
//                         fontSize: 16,
//                         fontStyle: 'bold',
//                         fontColor: '#666',
//                         fontFamily: 'Arial'
//                     },
//                     elements: {
//                         center: {
//                             text: count,
//                             color: '#FF6384',
//                             fontStyle: 'Arial',
//                             sidePadding: 50
//                         }
//                     }
//                 }
//             };
//             var ctx6 = document.getElementById("myChart6").getContext("2d");
//             var myChart6 = new Chart(ctx6, config6);
//             document.getElementById('myLegend6').innerHTML = myChart6.generateLegend();
//         }).catch(function() {
// // bootbox.alert({
// // message: globalReadDataFail,
// // buttons: {
// // ok: {
// // label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// // className: 'btn-danger',
// // }
// // },
// // callback: function() {}
// // });
//         }).finally(function() {});

//     };
//     $scope.queryManagerCount();
    
//     $scope.queryContactCount = function() {
//         var request = {};
//         $http.post('./api/p00/query/contactcount/dashboard', request, csrf_config).then(function(response) {
//             $scope.allitems_contact = response.data.datatable;
//             var data = [];
//             data.push($scope.getCount($scope.allitems_contact, "審核中"));
//             data.push($scope.getCount($scope.allitems_contact, "已停用"));          
//             data.push($scope.getCount($scope.allitems_contact, "待啟用"));          
//             data.push($scope.getCount($scope.allitems_contact, "使用中"));          
//             var count = 0;
//             for (i = 0; i < data.length; i++)
//                 count += data[i];
//             if(count < 10)
//             	count = " " + count + " ";
//             var config7 = {
//                 type: 'doughnut',
//                 data: {
//                     labels: [
//                         "審核中",
//                         "已停用",           
//                         "待啟用",           
//                         "使用中"       
//                     ],
//                     datasets: [{
//                         data: data,
//                         backgroundColor: [
//                         		"#E2ACEF",
//                             "#36A2EB",
//                             "#FFCE56",
//                             "#00cc66"                         
//                         ],
//                         hoverBackgroundColor: [
//                         		"#E2ACEF",
//                             "#36A2EB",
//                             "#FFCE56",
//                             "#00cc66"                        
//                         ]
//                     }]
//                 },
//                 options: {
//                 	title: {
//                 		display: true,
//                 		text: '總計',
//                 		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
//                 		fontSize: 15,
//                 		fontColor: '#369',
//                 		position: 'bottom'
//                 	},
//                 	legend: {
//                 		display: false,
//                 		position: 'bottom',
//                 		fullWidth: true,
//                 		reverse: false
//                 	},
//                     pieceLabel: {
//                         render: 'value',
//                         fontSize: 16,
//                         fontStyle: 'bold',
//                         fontColor: '#666',
//                         fontFamily: 'Arial'
//                     },
//                     elements: {
//                         center: {
//                             text: count,
//                             color: '#FF6384',
//                             fontStyle: 'Arial',
//                             sidePadding: 50
//                         }
//                     }
//                 }
//             };
//             var ctx7 = document.getElementById("myChart7").getContext("2d");
//             var myChart7 = new Chart(ctx7, config7);
//             document.getElementById('myLegend7').innerHTML = myChart7.generateLegend();
//         }).catch(function() {
// // bootbox.alert({
// // message: globalReadDataFail,
// // buttons: {
// // ok: {
// // label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// // className: 'btn-danger',
// // }
// // },
// // callback: function() {}
// // });
//         }).finally(function() {});

//     };
//     $scope.queryContactCount();
    
    
    // $scope.getSigninCountToday = function() {
    // 		var request = {
    // 				FuncName:"Login",
    // 				ActionName:"Login",
    // 				Status:"Success",
    // 				Sdate:moment().subtract('days',1).format('YYYY-MM-DD 23:59:59'),
    // 				Edate:moment().format('YYYY-MM-DD 23:59:59')
    // 				};
    //     $http.post('./api/p00/query/signincount/dashboard', request, csrf_config).then(function(response) {
    //     		$scope.SigninCountToday = response.data.count;
    //     });    		
    // }
    // $scope.getSigninCountToday()
    
    // $scope.getSigninCount2week = function() {
    // 	var request = {
    // 			FuncName:"Login",
	// 		ActionName:"Login",
	// 		Status:"Success",
	// 		Sdate:moment().subtract('days',14).format('YYYY-MM-DD 23:59:59'),
	// 		Edate:moment().format('YYYY-MM-DD 23:59:59')
    // 	};
    //     $http.post('./api/p00/query/signincount/dashboard', request, csrf_config).then(function(response) {
    //     		$scope.SigninCount2week = response.data.count;
    //     });      		
    // }
    // $scope.getSigninCount2week()
    
//      $scope.queryInformationDashboard2Week = function() {
//         var request = {};
//         $http.post('./api/p00/query/information/dashboard2week', request, csrf_config).then(function(response) {
//             $scope.allitems_information = response.data.datatable;
//             var data = [];
//             data.push($scope.getCount($scope.allitems_information, "健保署SOC"));
//             data.push($scope.getCount($scope.allitems_information, "衛生福利部SOC"));
//             data.push($scope.getCount($scope.allitems_information, "N-ISAC"));
//             data.push($scope.getCount($scope.allitems_information, "數聯資安"));
//             data.push($scope.getCount($scope.allitems_information, "其他"));
//             data.push($scope.getCount($scope.allitems_information, "H-ISAC"));
//             data.push($scope.getCount($scope.allitems_information, "SecBuzzer"));
//             var count = 0;
//             for (i = 0; i < data.length; i++)
//                 count += data[i];
//             if(count < 10)
//             	count = " " + count + " ";
//             var config8 = {
//                 type: 'doughnut',
//                 data: {
//                     labels: [
//                         "健保署SOC",
//                         "衛生福利部SOC",
//                         "N-ISAC",
//                         "數聯資安",
//                         "其他",
//                         "H-ISAC",
//                         "SecBuzzer"
//                     ],
//                     datasets: [{
//                         data: data,
//                         backgroundColor: [
//                             "#E2ACEF",
//                             "#36A2EB",
//                             "#FFCE56",
//                             "#00cc66",
//                             "#ff6633",
//                             "#660000",
//                             "#57f0ed"
//                         ],
//                         hoverBackgroundColor: [
//                             "#E2ACEF",
//                             "#36A2EB",
//                             "#FFCE56",
//                             "#00cc66",
//                             "#ff6633",
//                             "#660000",
//                             "#57f0ed"
//                         ]
//                     }]
//                 },
//                 options: {
//                 	title: {
//                 		display: true,
//                 		text: '近兩週',
//                 		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
//                 		fontSize: 15,
//                 		fontColor: '#369',
//                 		position: 'bottom'
//                 	},
//                 	legend: {
//                 		display: false,
//                 		position: 'bottom',
//                 		fullWidth: true,
//                 		reverse: false
//                 	},
//                     pieceLabel: {
//                         render: 'value',
//                         fontSize: 16,
//                         fontStyle: 'bold',
//                         fontColor: '#666',
//                         fontFamily: 'Arial'
//                     },
//                     elements: {
//                         center: {
//                             text: count,
//                             color: '#FF6384',
//                             fontStyle: 'Arial',
//                             sidePadding: 50
//                         }
//                     }
//                 }
//             };
//             var ctx8 = document.getElementById("myChart8").getContext("2d");
//             var myChart8 = new Chart(ctx8, config8);
//             document.getElementById('myLegend8').innerHTML = myChart8.generateLegend();
//         }).catch(function() {
// // bootbox.alert({
// // message: globalReadDataFail,
// // buttons: {
// // ok: {
// // label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// // className: 'btn-danger',
// // }
// // },
// // callback: function() {}
// // });
//         }).finally(function() {});

//     };
//     $scope.queryInformationDashboard2Week();

//     $scope.queryInformationDashboard = function() {
//         var request = {};
//         $http.post('./api/p00/query/information/dashboard', request, csrf_config).then(function(response) {
//         	 $scope.allitems_information = response.data.datatable;
//              var data = [];
//              data.push($scope.getCount($scope.allitems_information, "健保署SOC"));
//              data.push($scope.getCount($scope.allitems_information, "衛生福利部SOC"));
//              data.push($scope.getCount($scope.allitems_information, "N-ISAC"));
//              data.push($scope.getCount($scope.allitems_information, "數聯資安"));
//              data.push($scope.getCount($scope.allitems_information, "其他"));
//              data.push($scope.getCount($scope.allitems_information, "H-ISAC"));
//              data.push($scope.getCount($scope.allitems_information, "SecBuzzer"));
//              var count = 0;
//              for (i = 0; i < data.length; i++)
//                  count += data[i];
//              if(count < 10)
//              	count = " " + count + " ";
//              var config9 = {
//                  type: 'doughnut',
//                  data: {
//                      labels: [
//                          "健保署SOC",
//                          "衛生福利部SOC",
//                          "N-ISAC",
//                          "數聯資安",
//                          "其他",
//                          "H-ISAC",
//                          "SecBuzzer"
//                      ],
//                      datasets: [{
//                          data: data,
//                          backgroundColor: [
//                              "#E2ACEF",
//                              "#36A2EB",
//                              "#FFCE56",
//                              "#00cc66",
//                              "#ff6633",
//                              "#660000",
//                              "#57f0ed"
//                          ],
//                          hoverBackgroundColor: [
//                              "#E2ACEF",
//                              "#36A2EB",
//                              "#FFCE56",
//                              "#00cc66",
//                              "#ff6633",
//                              "#660000",
//                              "#57f0ed"
//                          ]
//                      }]
//                  },
//                  options: {
//                  	title: {
//                  		display: true,
//                  		text: '總計',
//                  		fontFamily: "'Noto Sans TC', 'Source Sans Pro', Calibri, Candara, Arial, sans-serif",
//                  		fontSize: 15,
//                  		fontColor: '#369',
//                  		position: 'bottom'
//                  	},
//                  	legend: {
//                  		display: false,
//                  		position: 'bottom',
//                  		fullWidth: true,
//                  		reverse: false
//                  	},
//                      pieceLabel: {
//                          render: 'value',
//                          fontSize: 16,
//                          fontStyle: 'bold',
//                          fontColor: '#666',
//                          fontFamily: 'Arial'
//                      },
//                      elements: {
//                          center: {
//                              text: count,
//                              color: '#FF6384',
//                              fontStyle: 'Arial',
//                              sidePadding: 50
//                          }
//                      }
//                  }
//              };
//              var ctx9 = document.getElementById("myChart9").getContext("2d");
//              var myChart9 = new Chart(ctx9, config9);
//              document.getElementById('myLegend9').innerHTML = myChart9.generateLegend();
//          }).catch(function() {
// // bootbox.alert({
// // message: globalReadDataFail,
// // buttons: {
// // ok: {
// // label: '<i class="fas fa-fw fa-times"></i>' + btnClose,
// // className: 'btn-danger',
// // }
// // },
// // callback: function() {}
// // });
//         }).finally(function() {});

//     };
//     $scope.queryInformationDashboard();
    
    $scope.getCount = function(allitems, name) {
        var count = 0;
        angular.forEach(allitems, function(value, key) {
            if (value.Name == name) {
                count = value.Count;
            }
        });
        return count;
    }
    
    $(document).ready(function() {
        Chart.pluginService.register({
            beforeDraw: function(chart) {
                if (chart.config.options.elements.center) {
                    var ctx = chart.chart.ctx;
                    var centerConfig = chart.config.options.elements.center;
                    var fontStyle = centerConfig.fontStyle || 'Arial';
                    var txt = centerConfig.text;
                    var color = centerConfig.color || '#000';
                    var sidePadding = centerConfig.sidePadding || 20;
                    var sidePaddingCalculated = (sidePadding / 100) * (chart.innerRadius * 2)
                    ctx.font = "30px " + fontStyle;
                    var stringWidth = ctx.measureText(txt).width;
                    var elementWidth = (chart.innerRadius * 2) - sidePaddingCalculated;
                    var widthRatio = elementWidth / stringWidth;
                    var newFontSize = Math.floor(30 * widthRatio);
                    var elementHeight = (chart.innerRadius * 2);
                    var fontSizeToUse = Math.min(newFontSize, elementHeight);
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';
                    var centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
                    var centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);
                    ctx.font = fontSizeToUse + "px " + fontStyle;
                    ctx.fillStyle = color;
                    ctx.fillText(txt, centerX, centerY);
                }
            }
        });
    })
};
