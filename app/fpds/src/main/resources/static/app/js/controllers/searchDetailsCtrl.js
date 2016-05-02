module.exports = function($scope,$http,$routeParams,$location,SearchFactory){
	console.log("Search Details Page");
	$scope.test = $routeParams.tranid;
	$scope.page = $routeParams.pageNo;
	$scope.searchpara = $routeParams.query;
	$scope.loading = 0;

	console.log($scope.searchpara); 

	$scope.company = function(value){
		console.log(value);
		return SearchFactory.getVendor(value).then(function(res){
			return res.data.map(function(item){
				return item;
			});
		});
	};

	$scope.search = function(){
		var earl = '/search/' + $scope.query;
    	$location.path(earl);
	};
	var start = ($scope.page - 1) * 10;

	SearchFactory.getSearchDetails($scope.searchpara,start,10)
		.success(function(item){
			findTransaction(item.transactions);
		})
		.error(function(data,status,header,config){
			console.log(status);
		});

	findTransaction = function(recs){
		console.log(recs.length);
		for(var i=0; i<recs.length; i++){
			console.log(recs[i].unique_transaction_id);
			if(recs[i].unique_transaction_id === $scope.test){
				$scope.datas = recs[i];
				console.log("Found " + $scope.datas.productorservicecode);
				$scope.loading = 1;
				break;
			}
		}
	};	
};