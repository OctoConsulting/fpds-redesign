module.exports = function($scope,$http,$location,$routeParams,SearchFactory){
	$scope.contractId = $routeParams.contractId;
	$scope.expanded = false;
	$scope.displays = false;

	var noOfContracts;
	$scope.flag = [];
	for(var i=0; i < noOfContracts ;i++){
		$scope.flag[i] = false;
	}

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

	$scope.expand = function(){
		$scope.expanded = !$scope.expanded;
		$scope.displays = !$scope.displays;
		if($scope.displays === true){
			for(var i=0; i < noOfContracts; i++){
				$scope.flag[i] = true;
			}	
		}
		else{
			for(var x=0; x < noOfContracts; x++){
				$scope.flag[x] = false;
			}
		}
		
	};

	$scope.tranExpand = function(val){
		console.log(val);
		$scope.flag[val] = !$scope.flag[val];
	};

	SearchFactory.getViewContracts($scope.contractId)
	.success(function(data){
		$scope.contracts = data;
		$scope.vendor = data[1].vendorname;
		console.log("Here is " + $scope.vendor);
		noOfContracts = $scope.contracts.length;
	})
	.error(function(header,config,status,data){
		console.log(status);
	});

	

};