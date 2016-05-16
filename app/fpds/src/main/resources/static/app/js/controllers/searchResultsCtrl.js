module.exports = function($scope,$http,$location,$routeParams,$anchorScroll,SearchFactory){

	console.log("Search Page");
	$scope.bigCurrentPage = 1;
	$scope.maxSize = 7;
	$scope.loading = 0;

	$scope.searchValue = $routeParams.vendor;
	console.log($scope.searchValue);

	$scope.company = function(value){
		console.log(value);
		return SearchFactory.getAutocomplete(value).then(function(res){
			return res.data.map(function(item){
				return item;
			});
		});
	};

	SearchFactory.getSearchDetails($scope.searchValue,0,10)
		.success(function(item){
			$scope.vendorResult = item.transactions;
			$scope.totalResults = item.total;
			$scope.bigTotalItems = item.total;
			$scope.loading = 1;
			//console.log("Size " + $scope.vendorResult.length);
			//console.log("Total " + $scope.totalResults);
 	 	})
 	 	.error(function(data,status,header,config){
 	 		console.log(status);
 	 	});	
 	SearchFactory.getResults($scope.searchValue)
 		.success(function(result){
 			$scope.widget = result;
 			$scope.widget.sum_obligated = Math.round($scope.widget.sum_obligated*100)/100;
 			console.log($scope.widget);
 			start(result.amt_by_quarter.quarters,result.amt_by_quarter.amts);
 		})
 		.error(function(data,status,config,header){
 			console.log(status);
 		});

	$scope.search = function(){
		$scope.searchValue = $scope.query;
		$scope.loading = 0;
		SearchFactory.getSearchDetails($scope.searchValue,0,10)
			.success(function(data){
				$scope.vendorResult = data.transactions;
				$scope.totalResults = data.total;
				$scope.bigTotalItems = data.total;
				$scope.bigCurrentPage = 1;
				
				//console.log("Size " + $scope.vendorResult.length);
				//console.log("Total " + $scope.totalResults);
			})
			.error(function(data,status,header,config){
				console.log(status);
			});
		SearchFactory.getResults($scope.searchValue)
 		.success(function(result){
 			$scope.widget = result;
 			start(result.amt_by_quarter.quarters,result.amt_by_quarter.amts);
 			$scope.loading = 1;
 		})
 		.error(function(data,status,config,header){
 			console.log(status);
 		});	
	};

	$scope.gototop = function(){
		$location.hash('top3');
		$anchorScroll();
	};

	$scope.pageChanged = function(){
		console.log('Page changed to: ' + $scope.bigCurrentPage);
		var num = ($scope.bigCurrentPage - 1) * 10;
		$scope.loading = 0;
		//console.log()
		SearchFactory.getSearchDetails($scope.searchValue,num,10)
			.success(function(res){
				$scope.vendorResult = res.transactions;
				$scope.totalResults = res.total;
				$scope.loading = 1;
				//console.log("Size " + $scope.vendorResult.length);
				//console.log("Total " + $scope.totalResults);
			})
			.error(function(data,status,header,config){
				console.log(status);
			});
	};

	$scope.details = function(value){
		//searchDetails/{{ contract.unique_transaction_id }}/{{ bigCurrentPage }} /{{ searchValue }}
		var earl = '/searchDetails/' + value + "/" + $scope.bigCurrentPage + "/" + $scope.searchValue;
    	$location.path(earl);
    	
	};

	$scope.viewContract = function(value){
		var url = '/viewContracts/'+ value;
		$location.path(url);
	};

	$scope.viewIDV = function(value){
		var rl = '/viewIdv/' + value;
		$location.path(rl);
	};
	$scope.load = false;
	$scope.analysis = function(){
		$scope.load = !$scope.load;
	};
	start = function(quarts,amt){
		var quart = [];
		var amtval = [];
		for (var x = quarts.length; x>0; x--){
			//console.log(quarts[x-1]);
			quart.push(quarts[x-1]);
			amtval.push(amt[x-1]);
		}
		console.log(quarts.length);
		Highcharts.chart('graph', {

	      title: {
	        text: 'Obligated Amount across Quarters'
	      },

	      xAxis: {
	      	title: {
                text: 'Quarters'
            },
	        categories: quart
	      },
	      yAxis:{
	      	title: {
                text: 'Dollars Obligated'
            }
	      },
	      series: [{
	        data: amtval,
	        name: "Dollars Obligated"
	      }]
	    });
	};
	

};