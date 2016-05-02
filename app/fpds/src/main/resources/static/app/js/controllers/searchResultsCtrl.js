module.exports = function($scope,$http,$routeParams,SearchFactory){

	console.log("Search Page");
	$scope.bigCurrentPage = 1;
	$scope.maxSize = 7;
	$scope.loading = 0;

	$scope.searchValue = $routeParams.vendor;
	console.log($scope.searchValue);

	$scope.company = function(value){
		console.log(value);
		return SearchFactory.getVendor(value).then(function(res){
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

	$scope.search = function(){
		$scope.searchValue = $scope.query;
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

};